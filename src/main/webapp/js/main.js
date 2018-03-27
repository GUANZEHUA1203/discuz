function IsPC() {
			
  		    var userAgentInfo = navigator.userAgent;
  		    var Agents = ["Android", "iPhone",
  		                "SymbianOS", "Windows Phone",
  		                "iPad", "iPod"];
	        if (userAgentInfo.indexOf("Android") > 0 || userAgentInfo.indexOf("iPhone") >0) {
	        	return true;
	        }else{
	        	return false;
	        }
  		}
//判断是否登录
function islogined(){
    $(function(){
		if("${sessionScope.logined}".length==0){
			$(".nologin").css({"display":"block"});
			$(".logined").css("display","none");

		}else{
			$(".nologin").css({"display":"none"});
			 $(".logined").css("display","block");
		}
	})
}
