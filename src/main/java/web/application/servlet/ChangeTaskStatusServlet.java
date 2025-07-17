package web.application.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import web.application.database.SqlServer;
import web.application.service.TaskService;

import java.io.IOException;

@WebServlet("/Tasks/ChangeStatus")
public class ChangeTaskStatusServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private TaskService taskService;
	
	@Override
	public void init(ServletConfig config) {
		ServletContext servletContext = config.getServletContext();
		SqlServer database = new SqlServer(servletContext);
		
		taskService = new TaskService(database);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rawTaskId = request.getParameter("id");
		
		if (rawTaskId == null || rawTaskId.isBlank()) {
			response.sendRedirect("../index.jsp");
			return;
		}
		
		int taskId = -1;
		
		try {
			taskId = Integer.parseInt(rawTaskId);
		} catch (NumberFormatException exception) {
			response.sendRedirect("../index.jsp");
			return;
		}
		
		taskService.changeTaskStatus(taskId);
		response.sendRedirect("../index.jsp");
	}
}
