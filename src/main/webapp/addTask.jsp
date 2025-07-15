<%@ page pageEncoding="UTF-8"%>

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
			<h1>Новая задача</h1>
			<hr>
			<form action="Tasks/Add" method="post">
				<div class="mb-3">
					<label class="form-label" for="title">Название задачи:</label>
					<input class="form-control" name="title" maxlength="128" required />
				</div>
				<div class="mb-3">
					<label class="form-label" for="description">Описание задачи:</label>
					<textarea class="form-control" name="description" rows="3" maxlength="512"></textarea>
				</div>
				<div class="mb-3">
					<button class="btn btn-primary" type="submit">
						<i class="bi bi-plus-circle me-1"></i>
						Добавить задачу
					</button>
				</div>
			</form>
		</div>
		<script src="resources/libraries/bootstrap/bootstrap.bundle.min.js"></script>
	</body>
</html>