package web.application.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import web.application.database.Database;
import web.application.model.Task;

public class TaskService {

	private final Database database;
	
	public TaskService(Database database) {
		this.database = database;
	}
	
	private Task readTask(ResultSet resultSet) throws SQLException {
		int taskId = resultSet.getInt("Id");
		String taskTitle = resultSet.getString("Title");
		String taskDescription = resultSet.getString("Description");
		boolean taskIsDone = resultSet.getBoolean("IsDone");
		Timestamp taskCreatedAt = resultSet.getTimestamp("CreatedAt");
		Timestamp taskUpdatedAt = resultSet.getTimestamp("UpdatedAt");
		
		Task task = new Task();
		task.setId(taskId);
		task.setTitle(taskTitle);
		task.setDescription(taskDescription);
		task.setDone(taskIsDone);
		task.setCreatedAt(taskCreatedAt);
		task.setUpdatedAt(taskUpdatedAt);
		
		return task;
	}
	
	public List<Task> getTasks() {
		List<Task> tasks = new ArrayList<Task>();
		
		database.useConnection(connection -> {
			try {				
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM Tasks");
				
				while (resultSet.next())
					tasks.add(readTask(resultSet));
			} catch (SQLException exception) {
				throw new RuntimeException(exception);
			}
		});
		
		return tasks;
	}
	
	public Optional<Task> getTask(int taskId) {
		try (Connection connection = database.createConnection()) {
			String sql = "SELECT * FROM Tasks WHERE Id = ?";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, taskId);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (!resultSet.next())
				return Optional.empty();
			
			return Optional.ofNullable(readTask(resultSet));
		} catch (SQLException exception) {
			throw new RuntimeException(exception);
		}
	}
	
	public void createTask(String title, String description) {
		database.useConnection(connection -> {
			try {
				String sql = "INSERT INTO Tasks (Title, Description) VALUES (?, ?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				
				preparedStatement.setString(1, title);
				preparedStatement.setString(2, description);
				
				preparedStatement.executeUpdate();
			} catch (SQLException exception) {
				throw new RuntimeException(exception);
			}
		});
	}
	
	public void editTask(int taskId, String title, String description) {
		database.useConnection(connection -> {
			try {
				String sql = "UPDATE Tasks SET Title = ?, Description = ? WHERE Id = ?";
				
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, title);
				preparedStatement.setString(2, description);
				preparedStatement.setInt(3, taskId);
				
				preparedStatement.executeUpdate();
			} catch (SQLException exception) {
				throw new RuntimeException(exception);
			}
		});
	}
	
	public void changeTaskStatus(int taskId) {
		database.useConnection(connection -> {
			try {
				String sql = "UPDATE Tasks SET IsDone = ~IsDone WHERE Id = ?";
				
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, taskId);
				
				preparedStatement.executeUpdate();
			} catch (SQLException exception) {
				throw new RuntimeException(exception);
			}
		});
	}
	
	public void deleteTask(int taskId) {
		database.useConnection(connection -> {
			try {
				String sql = "DELETE FROM Tasks WHERE Id = ?";
				
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, taskId);
				
				preparedStatement.executeUpdate();
			} catch (SQLException exception) {
				throw new RuntimeException(exception);
			}
		});
	}
}
