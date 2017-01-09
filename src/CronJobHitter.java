import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CronJobHitter
 */
@WebServlet("/CronJobHitter")
public class CronJobHitter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CronJobHitter() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String mode = request.getParameter("mode");
		System.out.println(mode);
		if (mode != null) {
			if (mode.equals("updateCronJobTimeToRun")) {
				System.out.println("in cjh get updatetime");
				try {
					CronJob.unscheduleCronJob();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} else {
			{
				response.getWriter().append("Served at: ")
						.append(request.getContextPath());
				String[] args = {};
				try {
					CronJob.main(args);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
