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

@WebServlet("/Tasks/Add")
public class AddTaskServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private static final int MAX_TASK_TITLE_LENGTH = 128;
	private static final int MAX_TASK_DESCRIPTION_LENGTH = 512;
	
	private TaskService taskService;
	
	@Override
	public void init(ServletConfig config) {
		ServletContext servletContext = config.getServletContext();
		SqlServer database = new SqlServer(servletContext);
		
		taskService = new TaskService(database);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		
		if (title == null || title.isBlank() || title.length() > MAX_TASK_TITLE_LENGTH) {
			response.sendRedirect("../addTask.jsp");
			return;
		}
		
		String description = request.getParameter("description");
		
		if (description != null) {
			if (description.length() > MAX_TASK_DESCRIPTION_LENGTH) {
				response.sendRedirect("../addTask.jsp");
				return;
			}
			
			if (description.isBlank())
				description = null;
		}
		
		taskService.createTask(title, description);
		response.sendRedirect("../index.jsp");
	}
}
