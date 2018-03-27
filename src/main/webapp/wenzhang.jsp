<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>显示文章</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="显示文章">
	<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="css/divcss/aticle.css">
	
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
		window.onload=function load(){
			var pn=$("input[name='pn']").val();
			var px=$("#pxNum").val();
			show(pn,px);
			ScrollImgLeft();
			
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
		//页码显示		
		function countnum(count,px){
			
			
			var currentPage=$("input[name='pn']").val();
			var pn=$("#pxNum").val();
			var tmp=Math.ceil(count/px);
			
			var start = parseInt(currentPage)<5?1:(parseInt(currentPage)-4);
			var end = (parseInt(currentPage)+4>tmp)?tmp:(parseInt(currentPage)+4);
			$(".pagination").append("<li>"+"<a href='javascript:void(0);' onclick=bb('-')>"+'&laquo;'+"</a>"+"</li>")
	   		for(var i=start-1;i<end;i++){
	   			$(".pagination").append("<li>"+"<a href='javascript:void(0);' onclick='bb("+i+")'>"+(i+1)+"</a>"+"</li>")
	   		}
	   		$(".pagination").append("<li>"+"<a href='javascript:void(0);'onclick=bb('+')>"+'&raquo;'+"</a>"+"</li>")
		}
		
		
		var bb=function(s){
			$("#wait_img").css("display","block");
			if(s=="+"){
	 	 			$("input[name='pn']").val(($("input[name='pn']").val()*1+1));
	 	 			}else if(s=="-"){
	 	 				$("input[name='pn']").val(($("input[name='pn']").val()*1-1));
	 	 	 			}else{
	 	 	 			$("input[name='pn']").val(s);
	 	 	 				}
			$("tbody").empty();
				selectAticle();
		}
		
		
		//查看文章详情
		var showdetail=function(id){
			window.location.href = "wenzhangdetail.jsp?id="+id;
		}
		//显示文章
		var showPublic=function(json){
			if(json.aticles.length==0){
				confirm("你还没有发表过文章，努力吧。。。。");
				$(".pagination").css({"display":"none"});
			}else{
				$(".pagination").css({"display":"block"});
			}
			for(var n=0;n<json.aticles.length;n++){
	   			var context=json.aticles[n].context;
	   			var title=json.aticles[n].title;
	   			if(title.length>20){
	   				title=title.substr(0, 20)+'...';
	   			}
	   			if(context.length>100){
	   				context=context.substr(0, 100)+'...';
	   				if(context.indexOf('src') >= 0||context.indexOf('href') >= 0){
	   					context='内容包括图片或链接地址  点击【查看详情】';
	   					return context;
	   				}
	   			}
	   			
	   			if(json.aticles[n].context)
	   			var status=json.aticles[n].state;
	   			if(status==0){
	   				status="可用";
	   			}
	   			/* col-*-1 */
	   			var targetObj =$("tbody").append(
	   					"<tr id='"+json.aticles[n].id+"'>"+
				            "<td class='man col-*-1' >"+"<font>"+json.aticles[n].man+"</font>"+"</td>"+
				           "<td class='title col-*-2 tooltip-test' data-placement='right' title='"+title+"' data-toggle='tooltip'  style='text-overflow: ellipsis;overflow: hidden;'nowrap>"+title+"</td>"+
				           "<td  class='context col-*-4 tooltip-test' data-placement='right' title='"+context+"' data-toggle='tooltip' style='text-overflow: ellipsis;overflow: hidden;'nowrap>"+context+"</td>"+
				            "<td class='date col-*-1 tooltip-test'  data-placement='right' data-toggle='tooltip' title='"+json.aticles[n].date+"' style='text-overflow: ellipsis;overflow: hidden;'nowrap>"+json.aticles[n].date+"</td>"+
				           /*  "<td>"+json.aticles[n].label+"</td>"+ */
				            "<td class='state col-*-1'   >"+status+"</td>"+
				            "<td class='col-*-2' >"+ "<button type=\"button\" class=\"btn btn-warning\" onclick='showdetail("+json.aticles[n].id+")'>"+"查看详情"+"</button>"+"</td>"+
				            "<td class='col-*-1'style='text-overflow: ellipsis;overflow: hidden;'nowrap>"+"<button type=\"button\" class=\"btn btn-danger\"  style='margin-left:6px;' onmousedown=deleteaticel("+json.aticles[n].id+")>"+"删除"+"</button>"+"<td>"+
		         	"</tr>");
	   			//onmouseover="this.className='showcontent'" onmouseout="this.className='nonecontent'"
		         
		        context=null;
	   			if(("${sessionScope.logined.name}"!=json.aticles[n].man)){
	   				$("#"+json.aticles[n].id+" button:eq(1)").attr("disabled","disabled");
	   			}
	   			if("${sessionScope.logined.power}"==1){
	   				$("#"+json.aticles[n].id+" button:eq(1)").removeAttr("disabled");
	   			}
	   		};
	   		$("#wait_img").css("display","none");
		}
		
		var show=function(pn,px){
			$("#wait_img").css("display","block");
			var json=null;
			$.get("showaticles?pn="+pn+"&px="+px+"&name="+$("input[name='lgname']").val(),
					function(data){
				   		json=eval(data);
				   		$("#selectLabel").empty();
				   		for(var j=0;j<json.labels.length;j++){
			         		$("#selectLabel").append(
			         				"<option value='"+json.labels[j].id+"'>"+json.labels[j].name+"</option>"
			         		);
			         	}
				   		$("tbody > input").remove();
				   		showPublic(json);
				   		$(".pagination").empty();
				   		return countnum(json.count,10);
				  });
		}
		var deleteaticel=function(id){
			$.post("deleteAticle?id="+id),
			selectAticle();
		}
		
		var tmpman="";
		var tmpman="";
		var tmptitle="";
		var tmpcontext="";
		var tmplabel="";
		var tmpnum="";
		var tmppx="";
		var selectAticle=function(){
			$("#wait_img").css("display","block");
			var pn=0;
			if((tmpman==$("input[name='man']").val())&&(tmptitle==$("input[name='title']").val())&&(tmpcontext==$("input[name='context']").val())&&(tmplabel==$("#selectLabel").val())&&(tmpnum==$("#selectdate").val())&&(tmppx==$("#pxNum").val())){
				pn+=$("input[name='pn']").val();
			}else{
				$("input[name='pn']").val(0);
			}
			 tmpman=$("input[name='man']").val();
			 tmptitle=$("input[name='title']").val();
			 tmpcontext=$("input[name='context']").val();
			 tmplabel=$("#selectLabel").val();
			 tmpnum=$("#selectdate").val();
			 tmppx=$("#pxNum").val();
			$.post("selectAticle?man="+$("input[name='man']").val()+"&title="+$("input[name='title']").val()+"&context="+$("input[name='context']").val()+"&label="+$("#selectLabel").val()+"&num="+$("#selectdate").val()+"&pn="+pn+"&px="+$("#pxNum").val(),
					function(data){
						var json=null;
				   		json=eval(data);
				   		$("tbody").empty();
				   		showPublic(json);
				   		$(".pagination").empty();
				   		//console.info("selected"+json.count);
				   		return countnum(json.count,tmppx);
				 },"json");
		}
	</script>
	<style type="text/css">
		body {
    height:100%;
    width:100%;
    background:repeat-y;
    background-image:url("images/gabby-orcutt-78650.jpg");
    background-color:black;
    background-size:100%;
    color:#000;
    font-weight:bolder;
    padding:0;
    margin:0;
    background-color:#fff
}
.changPath{
	margin-top: 15px;
}

tbody tr td{
   word-wrap:break-word; word-break:normal; 
}
	</style>
  </head>
<script type="text/javascript"> 
function ScrollImgLeft(){ 
	var speed=60; 
	var scroll_begin = document.getElementById("scroll_begin"); 
	var scroll_end = document.getElementById("scroll_end"); 
	var scroll_div = document.getElementById("scroll_div"); 
	scroll_end.innerHTML=scroll_begin.innerHTML; 
	function Marquee(){ 
		if(scroll_end.offsetWidth-scroll_div.scrollLeft<=0) 
			scroll_div.scrollLeft-=scroll_begin.offsetWidth; 
		else 
			scroll_div.scrollLeft++; 
	} 
	var MyMar=setInterval(Marquee,speed); 
	scroll_div.onmouseover=function() {clearInterval(MyMar);} 
	scroll_div.onmouseout=function() {MyMar=setInterval(Marquee,speed);} 
} 
</script> 
  
  <body>
  <div class="container">
  	<div class="col-sm-12 col-md-12">
  				
			  <div id="gongao"> 
					<div style="width:90%;height:30px;margin:0 auto;white-space: nowrap;overflow:hidden;" id="scroll_div" class="scroll_div"> 
						<div id="scroll_begin"> 
							<span>欢迎你  ${sessionScope.logined.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 该站资源正在整理中   注意!!：网盘地址部分有误   例如：（* 文件名称：xxxxxxx下载地址：https://pan.baidu.com/s/1bFxmm6# 网盘密码：百度网盘密码：3u4q?????）     <font color="red">地址为：https://pan.baidu.com/s/1bFxmm6（删除最后一个 ‘#’号）  密码为：3u4q&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </font></span> 
						</div> 
						<div id="scroll_end"></div> 
					</div> 
			  </div> 
			 <div class="changPath">
			 	 <a href="loginoutServices">
			  		<button type="button" class="btn btn-danger logined"  style='margin-left:6px;' >注销</button>
			 	 </a>
		 	 </div>
		 	  <div class="changPath">
		 	 <a href="chat">
		  		<button type="button" class="btn btn-primary logined pull-right"  style='margin-left:6px;' >在线交流</button>
		     </a>
		     </div>
		      <div class="changPath">
			 <h3 class="nologin"><a href="login.jsp">登录</a></h3>
			 </div>
			 <input type="hidden" value="${sessionScope.logined.name}" name="lgname"/>
			 <button class="logined btn btn-warning pull-right"><a href="fatie.jsp" style="color:#fff;">发表文章</a></button>
			 <input type="hidden" name="pn" value="0"/> 
			 <input type="hidden" name="px" value="10"/> 
			 <input type="hidden" class="isselect" value=""/>
		 	 <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" data-whatever=" ${sessionScope.logined.name}">点击查找</button> 
			 
			 <!-- modal -->
				 <div class="modal fade" id="myModal" role="dialog" aria-label="myModalLabel" aria-hidden="true">  
  				  <div class="modal-dialog modal-sm">  
  				  	<div class="modal-content"> 
  				  	 <div class="modal-header">  
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close">  
		                    <span aria-hidden="true">×</span>  
		                </button>  
		                <h4 class="modal-title">查找內容</h4>  
		           	 </div>  
  				  	 <div class="modal-body">
							   <div class="form-group col-*-12" >
							      <input type="text" class="form-control" id="name" name="man"
							         placeholder="请输入查询的用户" >
							   </div>
							   <div class="form-group col-*-12">
							      <input type="text" class="form-control" id="name" name="title"
							         placeholder="请输入用户的标题">
							   </div>
							   <div class="form-group col-*-12" >
							      <input type="text" class="form-control" id="name" name="context"
							         placeholder="用户的文章的内容">
							   </div>
							   <div class="form-group col-*-12" >
							       <select class="form-control input-lg"  id="selectdate">
							         <option value="">选择文章时间</option>
							         <option value="0">一周之内</option>
							         <option value="1">一个月之内</option>
							         <option value="2">半年之内</option>
							         <option value="3">一年以上</option>
							      </select>
							   </div>
							   <div class="form-group col-*-12" >
							       <select class="form-control input-lg" id="selectLabel">
							         <option value="0">选择标签</option>
							      </select>
							   </div>
							    <div class="form-group col-*-12" >
							       <select class="form-control input-lg" id="pxNum">
							         <option value="10">每页条数【10】</option>
							         <option value="20">每页条数【20】</option>
							         <option value="50">每页条数【50】</option>
							         <option value="200">每页条数【200】</option>
							      </select>
							   </div>
					   </div>
					   <div class="modal-footer">  
			   				<button type="submit"  class="btn btn-primary" onclick="selectAticle()" style="margin-left: 45px;" data-dismiss="modal">查找内容</button>
			   		  </div>
			   	  </div>
			   	</div>
			  </div>
			  <!-- modal end -->
		  <div class="show">
		  </div>
		  <div class="col-sm-12 col-md-12">
			  <table class="table table-striped" style="table-layout:fixed;white-space: normal;word-break:break-all; width: 100%;">
			   <caption>显示用户文章</caption>
			   <thead>
			      <tr>
			         <th>用户</th>
			         <th>标题</th>
			         <th>内容</th>
			         <th>时间</th>
			         <th>状态</th>
			         <th>操作</th>
			       	<tr/>
			   </thead>
			   <tbody style="white-space: nowrap;">
			  	<button class="btn btn-primary changPath" onclick=bb('+')>下一页</button>
			   </tbody>
			</table>
			  <ul class="pagination">
			  </ul><br>
		 </div>
		 <div id="wait_img">
			  <div class="canvas canvas6">
			    <div class="spinner6 p1"></div>
			    <div class="spinner6 p2"></div>
			    <div class="spinner6 p3"></div>
			    <div class="spinner6 p4"></div>
			    <p>加载中</p>
	 	</div>
	 	</div>
	 </div>
	</div>
  </body>
  <script type="text/javascript">
  $("body").click(function(){
	  $(".smartnlp-robot-clink").css("display","hidden");
  });
  if(IsPC()==false){
		jQuery.getScript("http://cloud.chatbot.cn/cloud/robot/webjs/5a20b6e40c000052f21e592f",function(){
			console.info("当前环境为pc端");
		});
	}
  
  /* $("#myModal").on("show.bs.modal",function(e){  
      var button=$(e.relatedTarget);  
      var recipient=button.data("whatever");  
      var modal=$(this);  
      modal.find(".modal-title").text("hello"+recipient);  
      modal.find(".modal-body input[name='username']").val(recipient);  
  });  */ 
  </script>
</html>
