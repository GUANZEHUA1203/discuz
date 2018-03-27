<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>崽儿图片</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
	<link rel="stylesheet" type="text/css" href="css/style.css">
  </head>
  <body>
  <a href="chat">在线交流</a>
  <div id="waterfall"></div>
  <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
  <script type="text/javascript" src="js/jquery.waterfall.js"></script>
  <script type="text/javascript">
	$(function(){
	var wf_page = 0;
	$('#waterfall').waterfall({
		// 自定义跨域请求
		ajaxFunc: function(success, error){
			$.ajax({
				type: 'GET',
				url: 'getimg',
				cache: false,
				data: {'page': ++wf_page},
				dataType:'jsonp',
				timeout: 60000,
				success: success,
				error: error
			});
		},
		createHtml: function(data){
			console.info(data);
			return '<div class="wf_item_inner">' +
					  '<a href="#" class="thumb" target="_blank">' +
						'<img class="thumb_img"  src="http://103.45.101.109:4300/'+data.preview+'" />' +
					  '</a>' +
					  '<p class="desc" style="margin-top:1px;">'+'</p>' +
					  '<a style="display:block;color:#060;" href="http://103.45.101.109:4300/'+data.preview+'" target="_blank">查看原图</a>' +
				  '</div>';
		}
	});
});
</script>
  </body>
</html>
