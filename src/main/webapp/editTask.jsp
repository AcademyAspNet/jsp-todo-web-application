<%@ page pageEncoding="UTF-8" import="web.application.database.*, web.application.model.*, web.application.service.*, web.application.helper.*, java.util.*" %>

<%
	int taskId = -1;

	try {
		String rawTaskID = request.getParameter("id");
		taskId = Integer.parseInt(rawTaskID);
	} catch (NumberFormatException exception) {
		response.sendRedirect("../index.jsp");
		return;
	}

	ServletContext servletContext = getServletContext();
	SqlServer database = new SqlServer(servletContext);
	
	TaskService taskService = new TaskService(database);
	Optional<Task> findedTask = taskService.getTask(taskId);
	
	if (findedTask.isEmpty()) {
		response.sendRedirect("../index.jsp");
		return;
	}
	
	Task task = findedTask.get();
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<link rel="stylesheet" href="resources/libraries/bootstrap/bootstrap.min.css" />
		<link rel="stylesheet" href="resources/libraries/bootstrap-icons/bootstrap-icons.min.css" />
		<title>Планировщик задач</title>
	</head>
	<body>
		<div class="container-md mt-3">
			<h1>Редактирование задачи</h1>
			<hr>
			<form action="Tasks/Edit?id=<%= task.getId() %>" method="post">
				<div class="mb-3">
					<label class="form-label" for="title">Название задачи:</label>
					<input class="form-control" name="title" maxlength="128" value="<%= task.getTitle() %>" required />
				</div>
				<div class="mb-3">
					<label class="form-label" for="description">Описание задачи:</label>
					
					<%
						String description = task.getDescription();
						
						if (description == null)
							description = "";
					%>
					<textarea class="form-control" name="description" rows="3" maxlength="512"><%= description %></textarea>
				</div>
				<div class="mb-3">
					<button class="btn btn-success" type="submit">
						<i class="bi bi-floppy me-1"></i>
						Сохранить изменения
					</button>
				</div>
			</form>
		</div>
		<script src="resources/libraries/bootstrap/bootstrap.bundle.min.js"></script>
	</body>
</html>