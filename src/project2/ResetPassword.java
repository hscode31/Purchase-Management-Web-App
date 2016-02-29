package project2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet(name="ResetPassword",urlPatterns={"/ResetPassword"})
public class ResetPassword extends HttpServlet 
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
		
		try 
        {   
			System.out.println("My name is hiamnshu singhal");
			HttpSession session=request.getSession();
            Connection conr=Connectify.getConnection();
            PreparedStatement psr=conr.prepareStatement("select upassword from sample.user where uid=? and upassword=?");
            psr.setString(1,(String)session.getAttribute("appid"));
            psr.setString(2,(String)request.getParameter("old_pwd"));
            ResultSet rsr=psr.executeQuery();
			int flag = 0;
			String pw1 = null,pw2 = null;
			pw1 = (String)request.getParameter("new_pwd");
			pw2 = request.getParameter("re_new_pwd");
			
			PreparedStatement ps1= null;
			
			
			if(rsr.next())
			{
				if(pw1.equals(pw2))
				{
					ps1 = conr.prepareStatement("update sample.user set upassword=? where uid=?");
					ps1.setString(1,pw1);
					ps1.setString(2,(String)session.getAttribute("appid"));
					ps1.executeUpdate();
					
					System.out.println("yaha par bhi chal gya");
					RequestDispatcher rd = request.getRequestDispatcher("successfull.html");
					rd.forward(request, response);
				}
				
				else
				{
					RequestDispatcher rd = request.getRequestDispatcher("unsuccessfull.html");
					rd.forward(request, response);
				}
			}
			
            else
            {
                RequestDispatcher rd = request.getRequestDispatcher("unsuccessfull.html");
                rd.forward(request, response);
			}
		}
        catch(Exception e)
        {
            System.out.println("error:"+e);
        }
	} 	
	 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }		
}		
			
			
			
			
			