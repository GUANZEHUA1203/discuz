<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>发表文章</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
	
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	
	
	
	<script type="text/javascript" src="utf8-jsp/ueditor.config.js"></script>
	<script type="text/javascript" src="utf8-jsp/ueditor.all.min.js"></script>
	<script type="text/javascript" src="utf8-jsp/lang/zh-cn/zh-cn.js"></script>
	
	
    <style type="text/css">
	body{
	height: 100%;
	width: 100%;
	background:no-repeat;
	background-color:black;
	background-image: url("images/everton-vila-140207.jpg");
	background-size: 100%}
	.main{
			width: 750px;
			margin: 0 auto;
		}
		.form-group>input,.form-group>select{
		margin-top:18px;
		width: 380px;}
		#edior{
		margin-top: 45px;}
	</style>
	<script type="text/javascript">
		window.onload=function(){
			show();
			var msg="${param.msg}";
			if(msg!=null&&msg!=''&&msg.length>0){
				confirm("【提示】"+msg);
			}
		}
		var show=function(){
			$.get("findAlllabels",
					  function(data){
						var json=eval(data);
					    for(var j=0;j<json.labels.length;j++){
			         		$("#selectLabel").append(
			         				"<option value='"+json.labels[j].id+"'>"+json.labels[j].name+"</option>"
			         		);
			         	}
					  });
		}
	</script>
  </head>
  
  <body>
  	<div class="main">
  		<form role="form" action="fabiao" name="upfile" method="post" style="margin-top:75px;">
  				<input type="hidden" name="man" value="${sessionScope.logined.name}">
  				<div class="form-group">
  					<div>
	  					<input type="text" class="form-control" placeholder=" 文	章 	标	 题" name="title">
	  					<select class="form-control input-lg" id="selectLabel" name="label" style="margin-top: 15px;width:250px;">
				         <option value="0">选择分类标签</option>
				     	</select>
			     	</div>
			      <div id="edior">
 						<script id="sendInvitEditor" type="text/plain" style="width:800px; height:400px;"  name="context"></script>
 						<script type="text/javascript">
 					    var ue = UE.getEditor('sendInvitEditor');
 					   	ue.destroy();
						</script>
				</div>
 				</div>
 		 	<button type="submit" class="btn btn-warning">提交</button>
		</form>
		<a href="wenzhang.jsp" style="font-weight: bolder;color: #fff;">查看所有</a>
	</div>
  </body>
</html>
