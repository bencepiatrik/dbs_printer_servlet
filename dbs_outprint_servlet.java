package sk.ukf;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class basic
 */
@WebServlet("/basic")
public class basic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public basic() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost/piatrik_sh_db";
		String login = "root";
		String pwd = "";
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//styles
		out.println("<style>");
		out.println("#center {"
				+ "		position: absolute;"
				+ "		top: 20%;"
				+ "    	left: 50%;"
				+ "    	margin-right: -50%;"
				+ "		transform: translate(-50%, -50%);"
				+ "}");
		out.println("table {"
				+ "		font-family: Arial, Helvetica, sans-serif;"
				+ "		border-collapse: collapse;"
				+ "		width: 100%;"
				+ "}");
		out.println("table th {"
				+ "		background-color: #46c904;"
				+ "		color: white;"
				+ "}");
		out.println("table td, table th {"
				+ "		border: 1px solid black;"
				+ "		padding: 5px 20px;"
				+ "}");
		out.println("table tr:nth-child(even) {"
				+ "		background-color: #d1d1d1;"
				+ "}");
		out.println("</style>");

		int rowCount = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, login, pwd);
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM album";
			ResultSet rs = stmt.executeQuery(sql);
			
			out.println("<p id=\"center\"><table>");
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			out.println("<tr>");
			for (int i = 0; i < columnCount; i++) {
				out.println("<th>" + md.getColumnLabel(i + 1) + "</th>");
			}
			out.println("</tr>");
			while (rs.next()) {
				rowCount++;
				out.println("<tr>");
				for (int i = 0; i < columnCount; i++) {
					out.println("<td>" + rs.getString(i + 1) + "</td>");
				}
			 out.println("</tr>");
			 }
			 out.println("</table>");
			 return;
		} catch (Exception ex) {
				out.print(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
