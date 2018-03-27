 <%@ page isELIgnored="false" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript"  src="jquery-1.4.4.min.js"></script>
<link rel="shortcut icon" href="chatroom/favicon.png">
<link rel="icon" href="favicon.png" type="image/x-icon">
<link type="text/css" rel="stylesheet" href="chatroom/css/style.css">
<script type="text/javascript" src="chatroom/js/jquery.min.js"></script>
<script type="text/javascript">
	var  ws;
	var username="${sessionScope.logined.name}";
	var target="ws://localhost:8080/discuz/chatSocket?username="+username;
	window.onload=function(){
		if("WebSocket" in window){
			ws=new WebSocket(target);
		}else if("MozWebSocket" in window){
			ws=new MozWebSocket();
		}else{
			alert("not find browser");
			return;
		}
		ws.onmessage=function(event){
			eval("var msg="+event.data+";");
			if(undefined!=msg.content){
				$("#message_box").append(msg.content+"<br/>");
			}
			if(undefined!=msg.listname){
				$(".user_list").html("");
				$(".user_list").append("<li class='fn-clear selected'><em>所有用户</em></li>");
				$(msg.listname).each(function(){
					console.info(this);
					$(".user_list").append("<li class='fn-clear' data-id="+this+"><span><img src='chatroom/images/hetu.jpg' width='30' height='30'  alt=''/></span><em>"+this+"</em><small class='online' title='在线'></small></li>")
				})
			}
			if(undefined!=msg.talkAbout){
				console.info("msg"+msg);
				$("#message_box").append("<div class='msg_item fn-clear'>"+
		          "<div class='uface'><img src='chatroom/images/53f44283a4347.jpg' width='40' height='40'  alt=''/></div>"+
		          "<div class='item_right'>"+
		            "<div class='msg'>"+msg.talkAbout+"</div>"+
		         /*    "<div class='name_time'>猫猫 · 3分钟前</div>"+ */
		          "</div>"+
		        "</div>");
			}
		}
	}
	function subsend(msg,type,touser){
		var object=null;
		if(type==0){
			 object={
					msg:msg,
					type:1,
			}
		}else{
			 object={
					touser:touser,
					msg:msg,
					type:2
			}
		}
		var str=JSON.stringify(object);
		ws.send(str);
	}

</script>
<style type="text/css">
body{
font-size:12px;
font-family: "楷体","楷体_GB2312"; 
}
ul li{
float: left;
list-style: none;
border: 1px solid #000;}
#content{
height: 85%;
max-width: 450px;
overflow-y :yes;
overflow-y :auto;
position:relative;
border-bottom: 5px #f00 solid;
}
</style>
</head>
<body>

	
	<div class="chatbox">
  <div class="chat_top fn-clear">
    <div class="logo"><img src="chatroom/images/logo.png" width="190" height="60"  alt=""/></div>
    <div class="uinfo fn-clear">
      <div class="uface"><img src="chatroom/images/hetu.jpg" width="40" height="40"  alt=""/></div>
      <div class="uname">
        ${sessionScope.logined.name}<i class="fontico down"></i>
        <ul class="managerbox">
          <!-- <li><a href="#"><i class="fontico lock"></i>修改密码</a></li> -->
          <li><a href="loginoutServices"><i class="fontico logout"></i>退出登录</a></li>
        </ul>
      </div>
    </div>
  </div>
  <div class="chat_message fn-clear">
    <div class="chat_left">
      <div class="message_box" id="message_box">
      </div> 
      <div class="write_box">
        <textarea id="message" name="message" class="write_area" placeholder="说点啥吧..."></textarea>
        <input type="hidden" name="fromname" id="fromname" value="${sessionScope.logined.name}" />
        <input type="hidden" name="to_uid" id="to_uid" value="0">
        <div class="facebox fn-clear">
          <div class="expression"></div>
          <div class="chat_type" id="chat_type">群聊</div>
          <button name="" class="sub_but">提 交</button>
        </div>
      </div>
    </div>
    <div class="chat_right">
      <ul class="user_list" title="双击用户私聊" >
      </ul>
    </div>
  </div>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(e) {
	$('#message_box').scrollTop($("#message_box")[0].scrollHeight + 20);
	$('.uname').hover(
	    function(){
		    $('.managerbox').stop(true, true).slideDown(100);
	    },
		function(){
		    $('.managerbox').stop(true, true).slideUp(100);
		}
	);
	
	var fromname = $('#fromname').val();
	var to_uid   = 0; // 默认为0,表示发送给所有用户
	var to_uname = '';
	$('.user_list > li').live("dblclick",function(){
		to_uname = $(this).find('em').text();
		to_uid   = $(this).attr('data-id');
		if(to_uname == fromname){
		    alert('您不能和自己聊天!');
			return false;
		}
		if(to_uname == '所有用户'){
		    $("#toname").val('');
			$('#chat_type').text('群聊');
		}else{
		    $("#toname").val(to_uid);
			$('#chat_type').text('您正和 ' + to_uname + ' 聊天');
		}
		$(this).addClass('selected').siblings().removeClass('selected');
	   // $('#message').focus().attr("placeholder", "您对"+to_uname+"说：");
	});
	
	$('.sub_but').click(function(event){
	    sendMessage(event, fromname, to_uid, to_uname);
	});
	
	/*按下按钮或键盘按键*/
	$("#message").keydown(function(event){
		var e = window.event || event;
        var k = e.keyCode || e.which || e.charCode;
		//按下ctrl+enter发送消息
		if((event.ctrlKey && (k == 13 || k == 10) )){
			sendMessage(event, fromname, to_uid, to_uname);
		}
	});
});
function sendMessage(event, from_name, to_uid, to_uname){
    var msg = $("#message").val();
	var htmlData =   '<div class="msg_item fn-clear">'
                   + '   <div class="uface"><img src="chatroom/images/hetu.jpg" width="40" height="40"  alt=""/></div>'
			       + '   <div class="item_right">'
			       + '     <div class="msg own">' + msg + '</div>'
			       + '     <div class="name_time">' + from_name + ' · 30秒前</div>'
			       + '   </div>'
			       + '</div>';
	$("#message_box").append(htmlData);
	$('#message_box').scrollTop($("#message_box")[0].scrollHeight + 20);
	$("#message").val('');
	console.info("to_uid"+to_uid+"to_uname"+to_uname);
	subsend(msg,to_uid,to_uname)
}
</script>
</html>