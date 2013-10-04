<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*,java.util.*" errorPage="" %>
<!-- List iname is List of type Employee -->
<!-- String columnLengthOfMessageText is maximum character allowed in single message -->
<!-- not_sent_flag indicates message not sent -->
<!-- sent_flag indicates message sent -->
<%! List iname;
	Integer columnLengthOfMessageText; 
	String user;
	String pass;
	Integer not_sent_flag=0;
	Integer sent_flag=0;%>
<% iname = (List)session.getAttribute("iname"); 
	user    = (String)session.getAttribute("user");
	pass	= (String)session.getAttribute("pass");
	not_sent_flag = (Integer)request.getAttribute("not_sent_flag");
	sent_flag = (Integer)request.getAttribute("sent_flag");%>
	
<!-- JSTL tag sql to use sql in jsp page -->
<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost/captain" user="<%=user%>"  password="<%=pass%>"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>Communication Wall-composeMessage</title>
<!-- css files for design of compose Message page and for dropdown list in composeMessage page -->
<link rel="stylesheet" type="text/css" href="css/captain_css.css" /> 
<link rel="stylesheet" type="text/css" href="jquery/dropdown.css" />
<!-- -->

<!-- required Javascript files for scroll bar and dropdown effects -->
<script type="text/javascript" src="jquery/modernizr.custom.79639.js"></script>
<script type="text/javascript" src="jquery/jquery-1.5.js"></script>
<script type="text/javascript">
	<!-- check_sent_or_not() checks whether message sent by user is sent successfully or not -->
	function check_sent_or_not(){
		var not_sent_flag = <%=not_sent_flag%>;
		var sent_flag = <%=sent_flag%>;
		if(not_sent_flag){
			alert('Message not sent as you have reched your daily maximum limit');
		}
		if(sent_flag){
			alert('Message sent Successfully');
		}
	
	}
	<!-- limitText restricts number of characters in textarea -->
	function limitText(text_elem){
		var messageLength = document.getElementById('cLength').value;
		if (text_elem.value.length>messageLength) {
		text_elem.value = text_elem.value.substring(0,messageLength);
		}
		else{
		document.getElementById('n_of_chars').innerHTML = text_elem.value.length;
		}
	}
	
	function setEmpId(list_field){
		document.getElementById('eid').value = list_field.id;
	}
	
	function goToViewMessage(){
		document.gotoviewmessage.submit();
	}
		
	function sendMessage(){
		document.hidden_m.submit();
	}
</script>

</head>

<body onload="check_sent_or_not();">
<!-- hidden fields belong here-->
<form name="gotoviewmessage" id="gotoviewmessage" method="post" action="/testing/vms">
</form>

<!-- -->
<div class="container">
  <div class="header"><a href="#"><img src="resource/images/login/lp-logo.png" alt="Insert Logo Here" name="Insert_logo" width="196" height="38" id="Insert_logo" display:block;" /></a> 
    <!-- end .header --></div>
  <div class="content">
		<div id="content_header">
		<p>Compose Message </p>
		<hr style="margin:20px 0 0 0">
		
		</div>
		<div id="content_options">
		<button style="margin:5px 0 5px 15px" type="button" name="send" value="send" class="css3button b_effect" onclick="sendMessage();">Send</button>
		<button type="button" name="Back" value="Back" class="css3button b_effect" onclick="goToViewMessage();">Back</button>
		<button type="button" name="Home" value="Home" class="css3button b_effect">Home</button>
		</div>
		<hr>
		
		<div id="content_body" style="overflow:hidden;">
	
		<section style="margin: 0 0 0 5px">
				<div class="wrapper-demo">
					<div id="dd" class="wrapper-dropdown-3" tabindex="1">
						<span>Recipients</span>
						<ul class="dropdown">
							<!-- manipulating employee names to whom use is allowed to message -->
							<c:forEach items="${iname}" var="in">
							<li id="${in.id}" onclick="setEmpId(this);"><a href="#"><i class="icon-envelope icon-large"></i>${in.name}</a></li>
							
							</c:forEach>
						</ul>
					</div>
				​</div>
			</section>
		<div style="float:right;margin:0 30px 0 0;">
			<div style="float:left;" id="n_of_chars">0</div>
				<div style="float:right;">/
					<!-- sql query to fetch messagetext column size to restrict maximum character in message -->
					<sql:query dataSource="${snapshot}" var="result"> 
						SELECT CHARACTER_MAXIMUM_LENGTH FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = 'message' AND table_schema = 'captain' AND column_name = 'messagetext'; 
					</sql:query> 
					<c:forEach var="length" items="${result.rows}">
						${length.CHARACTER_MAXIMUM_LENGTH}
						<input type="hidden" id ="cLength" name="cLength" value="${length.CHARACTER_MAXIMUM_LENGTH}"/>
					</c:forEach>
				</div>
		</div>
		
		<form name="hidden_m" id="hidden_m" method="post" action="/testing/sms">
			<input type="hidden" id="eid" name="eid">
			<textarea id="text_area" name="message"onKeyDown="limitText(this);"onKeyUp="limitText(this);"></textarea>
		</form>
		</div>
		
    <!-- end .content --></div>
  <div class="footer">
    <a href="#"><img src="resource/images/login/is-logo.png" alt="Insert Logo Here" name="Insert_logo" width="118" height="26" id="Insert_logo" display:block;" /></a> 
   
	<!-- end .footer --></div>
	 <div style="font-size:9px;color:#999999;text-align:center;margin:5px 0; padding:0 33%; width:100%;">
	Copyright 2012. All Rights Reserved.
	</div>
  <!-- end .container --></div>
  
  
  <!-- this cssfile can be found in the jScrollPane package -->
	<link rel="stylesheet" type="text/css" href="jquery/jquery.jscrollpane.css" />	
	<link rel="stylesheet" type="text/css" href="jquery/
		
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
  
  <!-- jQuery if needed -->
		
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<script type="text/javascript">
			
			function DropDown(el) {
				this.dd = el;
				this.placeholder = this.dd.children('span');
				this.opts = this.dd.find('ul.dropdown > li');
				this.val = '';
				this.index = -1;
				this.initEvents();
			}
			DropDown.prototype = {
				initEvents : function() {
					var obj = this;

					obj.dd.on('click', function(event){
						$(this).toggleClass('active');
						return false;
					});

					obj.opts.on('click',function(){
						var opt = $(this);
						obj.val = opt.text();
						obj.index = opt.index();
						obj.placeholder.text(obj.val);
					});
				},
				getValue : function() {
					return this.val;
				},
				getIndex : function() {
					return this.index;
				}
			}

			$(function() {

				var dd = new DropDown( $('#dd') );

				$(document).click(function() {
					$('.wrapper-dropdown-3').removeClass('active');
				});

			});

		</script>
	
		
  
  
  
</body>
</html>
