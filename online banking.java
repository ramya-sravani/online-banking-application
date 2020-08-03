package transaction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class online_banking
 */
public class online_banking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public online_banking() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");  
        PrintWriter pw = response.getWriter(); 
        Connection conn=null;
        String url="jdbc:mysql://localhost:3306/";
        String dbName="banking";
        try{  
            String transaction_type = request.getParameter("transaction_type");  
             int amount = Integer.parseInt(request.getParameter("amount"));  
             
             Class.forName("com.mysql.cj.jdbc.Driver");  
             conn = DriverManager.getConnection(url+dbName,"root", "root");
             PreparedStatement pst = conn.prepareStatement("insert into transaction (transaction_type,amount) values(?,?)");//try2 is the name of the table  
             pst.setString(1,transaction_type);
             pst.setInt(2,amount);
             
             
             int i = pst.executeUpdate();
             String msg=" ";
             if(i!=0){ 
           	  
           	  response.sendRedirect("transaction1.jsp");
                 
             }  
             else{  
           	  response.sendRedirect("index.jsp");
               msg="transaction failed";
               pw.println("<font size='6' color=blue>" + msg + "</font>");
              }  
             pst.close();
           }  
           catch (Exception e){  
             pw.println(e);  
           }  
             
             
	}

}
© 2020 GitHub, Inc.
