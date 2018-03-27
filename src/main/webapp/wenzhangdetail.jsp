<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<title>文章详情内容</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">


		<link rel="stylesheet" href="css/weui.min.css">
		<link rel="stylesheet" href="css/jquery-weui.css">
		<link rel="stylesheet" href="css/demos.css">
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<style type="text/css">
			body {
    background-image:url("images/bg_default2.jpg");
    azimuth:deg;
    background-size:100%;
    background-repeat:repeat-y;
    background-attachment:fixed;
    font-size:18px;
    background-color:#fff
}
.musicSound {
    position:fixed;
    top:25px;
}
#article {
    border-radius:2%;
    margin-top:25px;
    margin:0 auto;
    border-radius:35px;
    overflow:hidden;
    boder-radius:12px;
    box-shadow:5px#ffdd80;
    border:3px#664c00 solid;
    background-image:url('images/u=2742809312,623178162&fm=27&gp=0.jpg')
}
.title {
    padding-top:25px
}
.msg_phone{
	position: absolute;
	top: 0;
}
		</style>
<script type="text/javascript">
	var id=${param.id};
	window.onload=function(){
		init(id,0);
		 $(function(){
				if("${sessionScope.logined}".length==0){
					$(".nologin").css({"display":"block"});
					$(".logined").css("display","none");

				}else{
					$(".nologin").css({"display":"none"});
					 $(".logined").css("display","block");
				}
			});
	}
	window.onkeydown=function(){
		keyPress();
	}
	function init(id,userAgent){
		//&num="+1
		$.get("selectAticle?id="+id+"&pn="+0+"&px="+1,
				function(data){
			   		json=eval(data);
			   		if(userAgent==0){//0:电脑 1：手机端
				   		$("#showaticle").empty();
				   		$("#showaticle").empty();
				   		document.body.scrollTop = 0;
			   		}
			   		$("#showaticle").append("<h3 class='title' style='font-weight: bold; text-align: center;'><font>"+json.aticles[0].title+"</font></h3>");
			   		$("#showaticle").append("<div class='context' style='text-decoration: underline;'><font>"+json.aticles[0].context+"</font></div>");
			  });
	}
	var keyPress=function(){
		var code=event.keyCode;
		if(code==37){
			init(id--,0);
		}
		if(code==39||code==32){
			init(id++,0);
		}
	} 
	</script>
<!-- 	<script>
			var loading = false;
			$(document.body).infinite().on("infinite", function() {
				if(loading) return;
				loading = true;
				setTimeout(function() {
					init(id++,1);
					loading = false;
				}, 1000);
			});
		</script>
 -->	</head>

	<body>
	<div class="col-md-3 col-xs-1">
	<a href="chat" class="logined">在线交流</a>
	<div class="top"></div>
				<div class="musicSound">
					<div>
						<embed wmode="transparent"
							src="http://chabudai.sakura.ne.jp/blogparts/honehoneclock/honehone_clock_wh.swf"
							quality="high" bgcolor="#ffffff" width="295" height="100"
							name="honehoneclock" align="middle" allowscriptaccess="always"
							type="application/x-shockwave-flash"
							pluginspage="http://www.macromedia.com/go/getflashplayer">
					</div>
					<div>
						<iframe frameborder="no" border="0" marginwidth="0"
							marginheight="0" width=100% height=450
							src="http://music.163.com/outchain/player?type=0&id=55463146&auto=1&height=430"></iframe>
					</div> 
					<div>
						<a href="wenzhang.jsp"><button type="submit"
								class="btn btn-warning" style="margin-left: 25px;">还回文章首页</button></a>
					</div>
					
				</div>
			</div>
	<div class="container col-*-8">
			<div class="col-*-8" id="article">
					<div class="col-*-8 demos-content-padded" id="showaticle">
						<!-- <div class="weui-infinite-scroll">
							<div class="infinite-preloader"></div>
							正在加载
						</div> -->
					</div>
					<div class="col-*-4">
						<div class="pull-left computer_msg" style="color: gray;" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;阅读技巧：键盘  ←   →  或[空格] 进入阅读翻页   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>【Ctrl+D】 收藏本篇文章</div>
						<span class="msg_phone pull-right" style="color:red;">tip：【往上滑动翻页 加载过慢 耐心等候】</span>
					</div>
			</div>
	</div>
	<div class="bottom">
		<div></div>
	</div>
	<script src="js/jquery-2.1.4.js"></script>
	<script src="js/jquery-weui.js"></script>
	<script type="text/javascript" src="js/main.js"></script>

		<script>
			var loading = false;
			function slide(){
				$(document.body).infinite().on("infinite", function() {
					if(loading) return;
					loading = true;
					setTimeout(function() {
						init(id++,1);
						loading = false;
					}, 1000);
				});
			}
		</script>
	<script type="text/javascript">
			if(IsPC()){
				$(".musicSound,.computer_msg").remove();
		      	$(".container").css({"margin":"25px","width":"100%"});
		      	slide();
			}else{
				$(".msg_phone").remove();
			}
  		</script>
	</body>

</html>