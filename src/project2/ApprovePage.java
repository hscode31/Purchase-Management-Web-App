package project2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApprovePage extends HttpServlet 
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException 
	{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try 
        {
            /*int flag=0;
            int _fid=0;
            String department = null;
			String prn = request.getParameter("prn");
            String dor = request.getParameter("dor");
            String item_name = request.getParameter("item_name");
            int quantity = request.getParameter("quantity");
            String item_description = request.getParameter("item_description");
            int cost = request.getParameter("cost");
            String doc = request.getParameter("doc");
            String vendor_name = request.getParameter("vendor_name");
            String vendor_details = request.getParameter("vendor_details");
            String approve = request.getParameter("approve");
            */
            Connection con = Connectify.getConnection();
            PreparedStatement ps = null;
            ResultSet rs = null;
			int list_var = 0;
			//Map<String,String> list = new Map<String,String>;
			String list[] = new String[20];
			HttpSession session=request.getSession();
			for(int i=0;i<20;i++)
			{
			System.out.println("starting");
			}
			if(((String)session.getAttribute("post")).equals("hod"))
			{
				ps = con.prepareStatement("select reqid,user_name from sample.req_slip where user_appr=1 and user_dept like ?");
				ps.setString(1,(String)session.getAttribute("dept_name"));
				rs = ps.executeQuery();
				int count = 0,count1=0;
				while(rs.next())
				{
					list[count] = rs.getString("user_name");
					list[count] = rs.getString("reqid");
					count++;
				}
				session.setAttribute("count",Integer.toString(count));
				session.setAttribute("list",list);
				if(count==0)
				{
					list_var = 1;
				}
			}
			
			if(((String)session.getAttribute("post")).equals("faculty"))
			{
				String s="scyfDtuCth";
				ps = con.prepareStatement("select * from sample.req_slip where reqid ="+" "+s);
				//ps.setString(1,(String)session.getAttribute("appid"));
				rs = ps.executeQuery();
				int count = 0;
				System.out.println("ghuse gya rrom mn");
				while(rs.next())
				{
					list[count++] = rs.getString("reqid");
					list[count++] = rs.getString("date_fill");
					list[count++] = rs.getString("user_name");
					list[count++] = rs.getString("user_id");
					list[count++] = rs.getString("depname");
					list[count++] = rs.getString("item_name");
					list[count++] = rs.getString("discription");
				//	list[count++] = rs.getInt("cost");
					System.out.println(list[count]);
	
				}
				session.setAttribute("count",Integer.toString(count));
				session.setAttribute("list",list);
				if(count==0)
				{
					list_var = 1;
				}
			}
			
			

			
			if(((String)session.getAttribute("post")).equals("pm"))
			{
				ps = con.prepareStatement("select reqid,user_name from sample.req_slip where user_appr=1 and hod_appr=1");
				rs = ps.executeQuery();
				int count = 0;
				while(rs.next())
				{
					list[count] = rs.getString("user_name");
					list[count] = rs.getString("req_id");
					System.out.println(list[count]);
					count++;
				}
				session.setAttribute("count",Integer.toString(count));
				session.setAttribute("list",list);
				if(count==0)
				{
					list_var = 1;
				}
			}
			
			
			if(((String)session.getAttribute("post")).equals("director"))
			{
				ps = con.prepareStatement("select * from sample.req_slip where pm_appr=1");
				rs = ps.executeQuery();
				int count = 0;
				while(rs.next())
				{
					//list[count][1] = rs.getString("user_name");
					list[count] = rs.getString("user_name");
					list[count] = rs.getString("reqid");
					count++;
				}
				session.setAttribute("count",Integer.toString(count));
				session.setAttribute("list",list);
				if(count==0)
				{
					list_var = 1;
				}
			}
			RequestDispatcher rd = request.getRequestDispatcher("activerequest.jsp");
            rd.forward(request, response);
		}
		finally 
        {            
            out.close();
        }
	} 
        


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
