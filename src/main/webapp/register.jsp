<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
	<meta http-equiv="description" content="This is my page">
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
	.main{
			width: 750px;
			margin: 350px auto;
		}
		.form-group>input{
		margin-top:18px;
		width: 380px;}
	</style>
	<script type="text/javascript">
		

	var checkUserName = function(){
		var val=$("input[name='name']").val();
		val=$.trim(val);
		if(val != ""){
			var url="checkUsername";
			var args={"name":val};
			$.post(url,args,function(date){
				if(date==1){
					$("#tip1").empty();
					$("#tip1").append("<font color='red'>该用户名已经使用，修改用户名</font>");
					$("input[name='checkNamevalues']").val(1);
				}else{
					$("#tip1").empty();
					$("#tip1").append("<font color='green'>该用户名可以使用</font>");
					$("input[name='checkNamevalues']").val(0);
				}
			})
		}
	};
	var checkPassword = function(){
		var tip1 = document.getElementById("tip2");
		var t = document.getElementById("password");
		if(t.value.length>=6){
			tip1.innerHTML = "<font color='green'>新密码输入正确</font>";
			return 0;
		}else{
			tip1.innerHTML = "<font color='red'>新密码长度必须在6位以上</font>";
			return 1;
		}
	};
	var checkPassword2 = function(){
		var tip2 = document.getElementById("tip3");
		var t = document.getElementById("password2");
		if((t.value==password.value)&&(document.getElementById("password").value.length!=0)){
			tip2.innerHTML = "<font color='green'>两次密码输入一致</font>";
			return 0;
		}else if(document.getElementById("password").value.length==0){
			tip2.innerHTML = "<font color='red'>请先输入密码，再确认</font>";
			return 1;
		}else{
			tip2.innerHTML = "<font color='red'>两次密码输入不一致</font>";
			return 1;
		}
	};
		var formSubmit = function(){
			if($("input[name='checkNamevalues']").val()+checkPassword()+checkPassword2()==0){
				$("#registerRegex").submit();
			}else{
				alert("信息错误，注册失败");
			}
	};
	
	</script>
  </head>
  
  <body>
   	<div class="main">
   	<input type="hidden" name="checkNamevalues"  value=0>
  		<form role="form" action="zhuce" method="post" id="registerRegex">
  				<div class="form-group">
  					<input type="text" class="form-control" placeholder="昵称输入" id="name" name="name" onblur="checkUserName()"> <div id="tip1"><font></font></div>
    	  			<input type="password" class="form-control" placeholder="密码输入" id="password" name="password" onblur="checkPassword()"><div id="tip2"><font></font></div>
    		  		<input type="password" class="form-control" placeholder="确认密码输入" id="password2" onblur="checkPassword2()" ><div id="tip3"><font></font></div>
 				</div>
 		 	<button type="button" class="btn btn-primary" onclick="formSubmit()" >注册用户</button>
		</form>
		<h1>${param.msg}</h1>
		已有账号，<a href="login.jsp">登录</a>
	</div>
  </body>
</html>
