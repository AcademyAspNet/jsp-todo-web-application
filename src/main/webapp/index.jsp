<%@ page pageEncoding="UTF-8" import="web.application.database.*, web.application.model.*, web.application.service.*, java.util.List" %>

<%
	ServletContext context = config.getServletContext();

	String ip = context.getInitParameter("database.ip");
	int port = Integer.parseInt(context.getInitParameter("database.port"));
	
	String databaseName = context.getInitParameter("database.name");
	
	String user = context.getInitParameter("database.user");
	String password = context.getInitParameter("database.password");
	
	Credentials credentials = new Credentials(user, password);
	
	Database database = new SqlServer(ip, port, credentials, databaseName);
	TaskService taskService = new TaskService(database);
	
	List<Task> tasks = taskService.getTasks();
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<link rel="stylesheet" href="resources/libraries/bootstrap/bootstrap.min.css" />
		<title>Планировщик задач</title>
	</head>
	<body>
		<div class="container-md">
			<h1>Список задач</h1>
			<hr>
			<ol class="list-group list-group-numbered">
				<%
					for (Task task : tasks) {
						String html = "<li class='list-group-item'>%s</li>".formatted(task.getTitle());
						out.println(html);
					}
				%>
			</ol>
		</div>
		<script src="resources/libraries/bootstrap/bootstrap.bundle.min.js"></script>
	</body>
</html>