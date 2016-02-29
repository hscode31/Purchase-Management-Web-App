package project2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
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

public class RegistrationServlet extends HttpServlet 
{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try 
        {
        	System.out.println("Chal bhi ja yaar");
            int flag=0;
            int _fid=0;
            int cost1=0;
            String dor;
            String department = null;
			String prn = (String)request.getParameter("prn");
             dor = (String)request.getParameter("dor");
            String item_name = request.getParameter("item_name");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String item_description = request.getParameter("item_description");
            int cost = Integer.parseInt(request.getParameter("cost"));
            String doc = request.getParameter("doc");
            String vendor_name = request.getParameter("vendor_name");
            String vendor_details = request.getParameter("vendor_details");
            String approve = request.getParameter("approve");
            System.out.println(prn);
            Connection con2=Connectify.getConnection();
            PreparedStatement ps=null;
			PreparedStatement psc=null;
            ResultSet rs1=null;
			ResultSet rsq=null;
			ResultSet rsc=null;
			
			HttpSession session=request.getSession();
			try{
			psc = con2.prepareStatement("select dep_budget from sample.department where dep_name= ?");
			psc.setString(1,(String)session.getAttribute("dept"));
			rsc = psc.executeQuery();
			}catch(Exception e)
			{
				System.out.println("problem in database");
			}
			System.out.println("thoda toh aage cahl");
			for(int i=0;i<10;i++)
			{
				System.out.println(prn);
			}
			while(rsc.next())
			{
				cost1=rsc.getInt("dep_budget");
			}
			/*if(prn == null)
				flag=1;
            if(dor == null)
                flag=1;
            else if(item_name == null)
                flag=1;
            else if(quantity == 0)
                flag=1;
            else if(item_description == null)
                flag=1;  
            else if(cost == 0 || cost>=cost1)
                flag=1;
            else if(doc == null)
                flag=1;
			else if(approve == null)
				flag=1;
				*/
            if(flag == 0)
            {
				if((approve.equals("YES")))
				{
					flag = 0;
				}
				else if(approve.equals("NO"))
				{
					flag = 1;
				}
            /*    if(vendor_name.length() == 0 && vendor_details == null)
				{
					flag=1;
				}
                else if((vendor_name.length() != 0 && vendor_details == null) || (vendor_name.length() == 0 && vendor_details != null))
				{
					flag=1;
				}*/
            }

            //Connection con3=Connectify.getConnection();
            System.out.println("middle tak pahuch gya"+prn+dor+(String)session.getAttribute("appid"));
            if(flag == 1)
            {
                RequestDispatcher rd = request.getRequestDispatcher("requestrejected.html");
                rd.forward(request, response); 
            }
            
            else
            {
            	System.out.println("main part mn ghuse gya");
               // ps = con2.prepareStatement("insert into request(reqid,date_fill,user_name,user_id,user_dept,user_post,user_mobno,user_email,item_name,quantity,description,cost,doc,vendor_name,vendor_details,self_appr) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                ps = con2.prepareStatement("insert into sample.req_slip(reqid,date_fill,user_name,user_id,depname,discription,cost,self_appr,director_appr,hod_appr,fin_appr) values(?,?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1,prn);
                ps.setString(2,dor);
                ps.setString(3,(String)session.getAttribute("name"));
                ps.setString(4,(String)session.getAttribute("appid"));
               ps.setString(5,(String)session.getAttribute("dept"));
              //  ps.setString(6,(String)session.getAttribute("post"));
              //  ps.setString(7,(String)session.getAttribute("mobno"));
              //  ps.setString(8,(String)session.getAttribute("email"));
              //  ps.setString(9,item_name);
              //  ps.setInt(10,quantity);
                ps.setString(6,item_description);
                ps.setInt(7,cost);
                //ps.setString(13,doc);
                //ps.setString(14,vendor_name);
               // ps.setString(15,vendor_details);
 
				int appyes=0;
				int appno=0;
                
               /* if(approve.equals("YES"))
                {
                    appyes=1;
                    ps.setInt(8,appyes);
                }
                else
                {
                	ps.setInt(8,appyes);
                }*/
				ps.setInt(8,1);
                ps.setInt(9,0);
                ps.setInt(10,0);
                ps.setInt(11,0);
				int a=ps.executeUpdate();
				if(a==0)
				{
					System.out.println("problem in update req_slip");
				}
				
				System.out.println("connect to req_slip");
				PreparedStatement ps1=null;
				PreparedStatement ps2=null;
				PreparedStatement ps3=null;
                ps1=con2.prepareStatement("select quantity from sample.inventory where item_name=?");
                ps1.setString(1,item_name);
				rsq=ps1.executeQuery();
				int quantity1=0;
				if(rsq.next())
				{
					quantity1 = rsq.getInt("quantity");
				}
				else
				{
					ps3=con2.prepareStatement("insert into sample.inventory values(?,?)");
					ps3.setString(1,item_name);
					ps3.setInt(2,0);
				}
				System.out.println("Main part complete");
				quantity1 = quantity1 + quantity;
				
                ps2=con2.prepareStatement("update sample.inventory set quantity=? where item_name=?");
				ps2.setString(2,item_name);
				ps2.setInt(1,quantity1);
                ps2.executeUpdate();
				
				
                    
                RequestDispatcher rd=request.getRequestDispatcher("requestaccepted.html");
                rd.forward(request, response);
            }
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
