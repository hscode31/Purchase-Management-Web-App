<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.io.*,java.util.*, javax.servlet.*" %>
     <%@ page import="project2.ActiveRequest" %>
     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

  <meta charset="UTF-8">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

  <title>Active Requests</title>

  <link href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>

  <link rel="stylesheet" href="css/normalize.css">
  <link rel="stylesheet" type="text/css" media="all" href="jsDatePick_ltr.min.css" />
    <link rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="jquery.1.4.2.js"></script>
<script type="text/javascript" src="jsDatePick.jquery.min.1.3.js"></script>
<script type="text/javascript">
    window.onload = function () {
        new JsDatePick({
            useMode: 2,
            target: "inputField",
            dateFormat: "%d-%M-%Y"

        });
    };
</script>
    <link rel="stylesheet" href="css/style2.css">
</head>

<body>

  <div class="form">
<img src="coverpic1.jpg" alt="Mountain" style="width:668px;height:180px;margin-left:-25px;margin-top:-26px;margin-bottom:10px;box-shadow: 0 4px 10px 4px rgba(19, 35, 47, 0.3);"></img>
      
          <h1 style="color:white;margin-left:-25px;margin-right:-25px;margin-top:-20;pxmargin-bottom:20px;background-color:#FF8800;border-radius: 4px;">The LNM Institute of Information Technology</h1>

          <nav>
  <ul style="color:white;">
    <li><a style="color:white;" href="home.jsp">Home</a></li>
    <li><a style="color:white;" href="registration.jsp" >New Request</a></li>
    <li><a style="color:white;" href="activerequest">Active Requests</a></li>
    <li><a style="color:white;" href="completedrequests.jsp">Previous Requests</a></li>
    <li><a style="color:white;" href="Logout">Sign out</a></li>

  </ul>


</nav>

<h1 style="color:white;margin-left:-25px;margin-right:-25px;margin-top:-20;pxmargin-bottom:-20px;background-color:#FF8800;border-radius: 4px;">Active Requests</h1>

  <form action="activerequest" method="post">
  <%
		//String count = (String)session.getAttribute("count");
  		//int c=Integer.parseInt("count");
		
	String list[][] = (String[][])session.getAttribute("list");
		String cout = (String)session.getAttribute("count");
		int c=Integer.parseInt(cout);
	//	String list1[]=list.split(",");
		/*if(list1.length <= 0)
		{
			System.out.println("<h1> You have no Active Request.</h1>");
		}*/
		for(int i = 0;i <c;i++)
		{
			
			//for(int j=0;j<=1;j++)
			//{
			%>
				 <tr>
			        <td>
			          <%out.print("<h3 style='text-align:left;'>" + list[i][0] + "</h3>"); %>
			        </td>
			        <td>
			          <%out.print("<a href='reqslips'><h3 style='text-align:right;'>" + list[i][1] + "</h3></a>"); %>
			        </td>
			    </tr>
				<!--out.print("<tr><td><a href><h3>" + list[i][j] + "</h3></a></td></tr>");-->
			<%//}
			out.println("</br>");
		}
		%>
 <!--out.print("<P>" + colors[i] + "</p>");-->
	</form>  
<ul id="active request">
<!-- <li><a href=""></a></li>   -->
</ul>
    
  <script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>

  <script src="js/index.js"></script>

</body>

</html>