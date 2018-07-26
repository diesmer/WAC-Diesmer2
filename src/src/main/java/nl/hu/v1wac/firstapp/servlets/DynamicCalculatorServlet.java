package nl.hu.v1wac.firstapp.servlets;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(urlPatterns = "/DynamicServletCalculator.do")
public class DynamicCalculatorServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nummer1 = req.getParameter("number1");
		String nummer2 = req.getParameter("number2");

		int nummer11 = Integer.parseInt(nummer1);
		int nummer22 = Integer.parseInt(nummer2);

		int som = 0;

		if (req.getParameter("knop").equals("min")) {
			som = nummer11 - nummer22;
		}

		if (req.getParameter("knop").equals("plus")) {
			som = nummer11 + nummer22;
		}

		if (req.getParameter("knop").equals("keer")) {
			som = nummer11 * nummer22;
		}

		if (req.getParameter("knop").equals("deel")) {
			som = nummer11 / nummer22;
		}

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println(" <title>Dynamic Example</title>");
		out.println(" <body>");
		out.println(" <h2>De uitkomst is:</h2>");
		out.println(" <h2>Het antwoord is " + som + "!</h2>");
		out.println(" </body>");
		out.println("</html>");
	}
}
