package web.application.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import web.application.database.Database;
import web.application.model.Task;

public class TaskService {

	private final Database database;
	
	public TaskService(Database database) {
		this.database = database;
	}
	
	public List<Task> getTasks() {
		List<Task> tasks = new ArrayList<Task>();
		
		database.useConnection(connection -> {
			try {				
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM Tasks");
				
				while (resultSet.next()) {
					int taskId = resultSet.getInt("Id");
					String taskTitle = resultSet.getString("Title");
					String taskDescription = resultSet.getString("Description");
					Timestamp taskCreatedAt = resultSet.getTimestamp("CreatedAt");
					
					Task task = new Task();
					task.setId(taskId);
					task.setTitle(taskTitle);
					task.setDescription(taskDescription);
					task.setCreatedAt(taskCreatedAt);
					
					tasks.add(task);
				}
			} catch (SQLException exception) {
				throw new RuntimeException(exception);
			}
		});
		
		return tasks;
	}
}
