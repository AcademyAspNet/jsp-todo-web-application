<%@ page pageEncoding="UTF-8" import="web.application.database.*, web.application.model.*, web.application.service.*, web.application.helper.*, java.util.*" %>

<%
	ServletContext context = config.getServletContext();
	
	Database database = new SqlServer(context);
	TaskService taskService = new TaskService(database);
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
			<h1>Список задач</h1>
			<hr>
			<a class="btn btn-primary" href="addTask.jsp">
				<i class="bi bi-plus-circle me-1"></i>
				Добавить задачу
			</a>
			<hr>
			<div class="tasks">
				<% for (Task task : taskService.getTasks()) { %>
					<div class="card mb-3">
						<div class="card-body">
							<p class="fs-5 fw-bold m-0 mb-2">
								<% if (task.isDone()) { %>
									<i class="bi bi-record-circle-fill text-success"></i>
								<% } else { %>
									<i class="bi bi-circle-fill text-danger"></i>
								<% } %>
								<%= task.getTitle() %>
							</p>
							<% String description = task.getDescription(); %>
							<% if (description != null) { %>
								<span><%= description %></span>
							<% } else { %>
								<span class="text-secondary fst-italic">Описание задачи отсутствует...</span>
							<% } %>
						</div>
						<div class="card-footer d-flex align-items-center">
							<div class="created-at w-50">
								<i class="bi bi-clock"></i>
								<span><%= FormatHelper.formatDateTime(task.getCreatedAt()) %></span>
							</div>
							<div class="container-fluid p-0 d-flex flex-row-reverse">
								<a class="btn btn-danger ms-2" href="Tasks/Delete?id=<%= task.getId() %>">
									<i class="bi bi-trash3 me-1"></i>
									Удалить
								</a>
								<a class="btn btn-primary ms-2" href="editTask.jsp?id=<%= task.getId() %>">
									<i class="bi bi-pencil me-1"></i>
									Редактировать
								</a>
								<% if (task.isDone()) { %>
									<a class="btn btn-danger" href="Tasks/ChangeStatus?id=<%= task.getId() %>">
										<i class="bi bi-x-lg me-1"></i>
										Снять метку
									</a>
								<% } else { %>
									<a class="btn btn-success" href="Tasks/ChangeStatus?id=<%= task.getId() %>">
										<i class="bi bi-check2 me-1"></i>
										Отметить
									</a>
								<% } %>
							</div>
						</div>
					</div>
				<% } %>
			</div>
		</div>
		<script src="resources/libraries/bootstrap/bootstrap.bundle.min.js"></script>
	</body>
</html>