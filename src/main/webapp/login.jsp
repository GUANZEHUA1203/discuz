<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<style type="text/css">
		body{
		height: 100%;
		width: 100%;
		background:no-repeat;
		background-color:black;
		background-image: url("images/frank-mckenna-219857.jpg");
		background-size: 100% 85%;
		color:#000;
		font-weight: bolder;
		}
	/* 	.main{
			position:relative;
			width: 750px;
			height:300px;
			margin:auto;
		}
		.form-group div{
			margin-bottom: 30px;
		}
		.form-group div>input{
			width: 380px;
		} */
		
	</style>

  </head>
  
  <body>
 	 <div class="container">
 	  <div>
  		<form role="form" action="userlogin" style="margin-top: 450px;" method="post">
 				<div class="form-group">
 					<input type="text" class="form-control" placeholder="账号输入" name="name" >
 				</div>
 				<div class="form-group col-*-6">
    	  			<input type="password" class="form-control" placeholder="密码输入" name="password"  >
 				</div>
 				
 			<div class="contatins">
 		 	<button type="submit" class="btn btn-primary" style="margin: 0 auto;">登录</button>
 		 	<p>新用户，点击<a href="register.jsp">注册</a></p>
			<a href="wenzhang.jsp">查看所有</a>
			</div>
		</form>
		</div>
  </body>
</html>
