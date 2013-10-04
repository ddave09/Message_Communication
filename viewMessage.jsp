<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*,java.util.*" errorPage="" %>
<!-- messages are List of type Message -->
<%! List messages;
	String user;
	String pass;%>
<% messages = (List)session.getAttribute("messages");
	user    = (String)session.getAttribute("user");
	pass	= (String)session.getAttribute("pass");%>
<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/captain" user="<%=user%>"  password="<%=pass%>"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Communication Wall-ViewMessage</title>
<!-- select_div functions is responsible of changes color of division when selected. It also refreshes a "refdiv" division to set read value 1 of selected 
message and also sets full message in popUpDiv -->
<link rel="stylesheet" type="text/css" href="css/captain_css.css" /> 
<script type="text/javascript">
var old_id;
var new_id=0;
var count=1;
var put_msg;
function select_div(field){
new_id = field.id;
document.getElementById('mid').value = new_id;
document.getElementById('mid_').value = new_id;
document.getElementById('sender_id').value = document.getElementById(new_id).children[1].id;
put_msg = document.getElementById(new_id).children[0].id;
document.getElementById('popUpDiv').innerHTML = put_msg;
$('#refdiv').load('srs?mid_='+new_id); 
if(count==1){
old_id = new_id;
document.getElementById(new_id).style.backgroundColor = "#f7f7f7"; 
count++;
}
else if(new_id!=old_id){
document.getElementById(new_id).style.backgroundColor = "#f7f7f7";
document.getElementById(old_id).style.backgroundColor = "cyan";
old_id = new_id;
}

}

function deleteMessage(){
	if(new_id)
	{
	document.hidden_fields.submit();
	}
	else{
	alert("No message is selected");
	}
	
}

function deleteAllMessages(){
	document.hidden_fields_all.submit();
}
	/* in full application take user id from session variable and strore it in hidden field 
		and take value here from hidden field */

function reply_action(){
	if(new_id)
	{
	document.hidden_fields_reply.submit();
	}
	else{
	alert("No message is selected");
	}
}

function compose_action(){
	document.hidden_fields_compose.submit();
}
	
</script>



<script src="jquery/csspopup.js"></script>

</head>

<body style="background-image:url(resource/images/login/levelspro-bg.jpg);">
<!-- hidden fields belong here-->
<form name="hidden_fields" id="hidden_fields" method="post" action="/testing/dms">
	<input type="hidden" id="mid" name="mid">
</form>
<form name="hidden_fields_set" id="hidden_fields_set" method="post" action="/testing/srs">
	<input type="hidden" id="mid_" name="mid_">
</form>
<form name="hidden_fields_all" id="hidden_fields_all" method="post" action="/testing/dams">
</form>
<form name="hidden_fields_reply" id="hidden_fields_reply" method="post" action="/testing/replyms">
	<input type="hidden" id="sender_id" name="sender_id">
</form>
<form name="hidden_fields_compose" id="hidden_fields_compose" method="post" action="/testing/cms">
</form>
<!-- -->

 <div id="refdiv"></div>
 <div id="blanket" style="display:none;"> </div>		
					<div id="popUpDiv" style="display:none;oveflow:auto;" onclick="pop_up();" > 
					</div>	

<div class="container">
  <div class="header"><a href="#"><img src="resource/images/login/lp-logo.png" alt="Insert Logo Here" name="Insert_logo" width="196" height="38" id="Insert_logo" display:block;" /></a> 
    <!-- end .header --></div>
  <div class="content">
		<div id="content_header">
		<p>Messages </p>
		<hr>
		</div>
		<div id="content_options">
		<button type="button" name="Compose" value="Compose" class="css3button b_effect" onclick="compose_action();">Compose</button>
		<button type="button" name="Reply" value="Reply" class="css3button b_effect" onclick="reply_action();">Reply</button>
		<button type="button" name="Delete" value="Delete" class="css3button b_effect" onclick="deleteMessage();">Delete</button>
		<button type="button" name="DeleteAll" value="DeleteAll" class="css3button b_effect" onclick="deleteAllMessages();">Delete All</button>
		<button type="button" name="Home" value="Home" class="css3button b_effect">Home</button>
					
		</div>
		<hr>
		
		
		<div id="content_body">
			 
			<div class="message_management">

				<div style='width: 107px;float: left;margin:2px 0 2px 0; text-align:center;' class="message_management_header">  <!-- filter:alpha(opacity=60);opacity:.6; -->
				SENDER	
				</div>
				
					<div style='width: 100px;float: left;margin:2px 0 2px 0;margin:2px 0 2px 5px;text-align:center;' class="message_management_header">  <!-- filter:alpha(opacity=60);opacity:.6; -->
					DATE
					</div>
				<div style='width: 65px;float: left;margin:2px 0 2px 0;margin:2px 0 2px 5px;text-align:center;' class="message_management_header">  <!-- filter:alpha(opacity=60);opacity:.6; -->
				TIME
				</div>
				
					<div style='width: 250px;float: left;margin:2px 0 2px 0;margin:2px 0 2px 5px;text-align:center;' class="message_management_header">  <!-- filter:alpha(opacity=60);opacity:.6; -->
					MESSAGE
					</div>
				</div>
			<!-- ${messages} is of type Message returned from ViewMessageServlet -->
			<c:forEach items="${messages}" var="message">
			
			
				<div class="message_management" id="${message.id}" onclick="select_div(this);">
				<!-- please do not change order of children divisions as each child is used by index in javascript functions -->
					<!-- var rd shows whether message is already read or not -->
						<c:set var="rd"  value="${message.read}"/>
					<!-- var message has value of messagetext -->
						<c:set var="msg" value="${message.messagetext}"/>
						<div id="${msg}"> 
						</div>
						<div id="${message.frm}">
						</div>
						<!-- test for whether to show show message in bold or not -->
						<c:if test="${rd == 'false'}">
						<div style='width: 107px;float: left;margin:2px 0 2px 0; text-align:center;'>  
						<b>
							<sql:query dataSource="${snapshot}" var="result">
							SELECT name from employee where emp_id=${message.frm};
							</sql:query>
							<c:forEach var="row" items="${result.rows}">
							${row.name}
							</c:forEach>
								</div>
				
							<div style='width: 100px;float: left;margin:2px 0 2px 0;margin:2px 0 2px 5px;text-align:center;'> 
							<fmt:formatDate type="date" value="${message.sdt}" />
							</div>
							
							<div style='width: 65px;float: left;margin:2px 0 2px 0;margin:2px 0 2px 5px;text-align:center;'>  
							<fmt:formatDate type="time" value="${message.sdt}"/>
							</div>
				
							<div style='width: 250px;float: left;margin:2px 0 2px 0;margin:2px 0 2px 5px;text-align:center;' onclick="pop_up();">  
							
							<c:out value="${fn:substring(msg, 0, 30)}" />   <!-- this line restricts number of characters to 30 to be get displayed. -->  
							</div> 
						</b>
						</c:if>
						<!-- test for whether to show show message in bold or not -->
						<c:if test="${rd == 'true'}">
						<div style='width: 107px;float: left;margin:2px 0 2px 0; text-align:center;'>  
						
							<sql:query dataSource="${snapshot}" var="result">
							SELECT name from employee where emp_id=${message.frm};
							</sql:query>
							<c:forEach var="row" items="${result.rows}">
							${row.name}
							</c:forEach>
								</div>
				
							<div style='width: 100px;float: left;margin:2px 0 2px 0;margin:2px 0 2px 5px;text-align:center;'>  
							<fmt:formatDate type="date" value="${message.sdt}" />
							</div>
							
							<div style='width: 65px;float: left;margin:2px 0 2px 0;margin:2px 0 2px 5px;text-align:center;'>  
							<fmt:formatDate type="time" value="${message.sdt}"/>
							</div>
				
							<div style='width: 250px;float: left;margin:2px 0 2px 0;margin:2px 0 2px 5px;text-align:center;' onclick="pop_up();">  
							<c:out value="${fn:substring(msg, 0, 30)}" />     <!-- this line restricts number of characters to 30 to be get displayed. -->
							</div> 
						
						</c:if>
							

				
					
				</div>
				
				
			
			</c:forEach>
				
	</div>
		
    <!-- end .content --></div>
  <div class="footer">
    <a href="#"><img src="resource/images/login/is-logo.png" alt="Insert Logo Here" name="Insert_logo" width="118" height="26" id="Insert_logo" display:block;" /></a> 
   
	<!-- end .footer --></div>
	 <div id="login-copyright">
	Copyright 2012. All Rights Reserved.
	</div>
  <!-- end .container --></div>
  
  
  <!-- this cssfile can be found in the jScrollPane package -->
	<link rel="stylesheet" type="text/css" href="jquery/jquery.jscrollpane.css" />	
		
	<!-- latest jQuery direct from google's CDN -->
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<!-- the jScrollPane script -->
	<script type="text/javascript" src="jquery/jquery.jscrollpane.min.js"></script>
	
	<!--instantiate after some browser sniffing to rule out webkit browsers-->
	<script type="text/javascript">
	
	  $(document).ready(function () {
	      if (!$.browser.webkit) {
	          $('#content_body').jScrollPane();
	      }
	  });
	
	</script>
	
	<script type="text/javascript">
	
	  $(document).ready(function () {
	    
	          $('#content_body').jScrollPane();
	      
	  });
	</script>
  
	<script>
		function pop_up(){
		popup('popUpDiv')
		}
	</script>
	
	
  
  
  
</body>
</html>
