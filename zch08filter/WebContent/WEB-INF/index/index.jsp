<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="js/jquery-3.3.1.min.js" type="text/javascript"
	charset="utf-8"></script>
<script type="text/javascript">
var websocket = null;

//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
	websocket = new WebSocket("ws://192.168.0.123:8080/zch08filter/websocket");
} else {
	alert('没有建立websocket连接')
}

//连接发生错误的回调方法
websocket.onerror = function() {
	//setMessage("错误");
};

//连接成功建立的回调方法
websocket.onopen = function(event) {
	//setMessage("建立连接");
}

//接收到消息的回调方法
websocket.onmessage = function(event) {
	$("#count").html(event.data);
}

//连接关闭的回调方法
websocket.onclose = function() {
	
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口。
window.onbeforeunload = function() {
	websocket.close();
}



//关闭连接
function closeWebSocket() {
	websocket.close();
}

//发送消息
//function send() {
//	var message = $("#text").val();
//	websocket.send(message);
//}





	$().ready(function() {
		$(".yi").click(function() {
			$(this).next().slideToggle(500);
		})
	})
</script>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

#main {
	overflow: hidden;
}

#left {
	width: 400px;
	height: 600px;
	float: left;
	padding-left: 20px;
}

#right {
	width: 800px;
	height: 600px;
	float: left;
}

a {
	color: #fff;
	text-decoration: none;
}

#top, #bottom {
	clear: both;
	height: 80px;
	height: 80px;
	background: #337ab7;
	position: relative;
}

.yi {
	width: 160px;
	height: 40px;
	background: #337ab7;
	color: #fff;
	margin-top: 10px;
	border-radius: 3px;
	text-align: center;
	line-height: 40px;
}

.er {
	list-style: none;
	width: 160px;
}

.er li {
	margin-top: 5px;
	background: #337aa7;
	text-align: center;
	color: #fff;
	height: 30px;
	font-size: 14px;
	line-height: 30px;
}

.er a {
	color: #000;
}
#count{
width: 20px;
height: 20px;
color: #fff;
font-weight:bold;
background: red;
position: absolute;
right: 10px;
bottom: 10px; 

}


</style>
</head>
<body>
	<div id="container">
		<div id="top"> <div id="count"> ${num } </div></div>
		<div id="main">
			<div id="left">
				<div class="yi">员工管理</div>
				<ul class="er">
					<li><a href="emp" target="right">员工管理</a></li>
					<li><a href="emp?type=showadd" target="right">员工添加</a></li>
				</ul>

				<div class="yi">部门管理</div>
				<ul class="er">
					<li><a href="dep" target="right">部门管理</a></li>
					<li><a href="dep?type=showadd" target="right">部门添加</a></li>
				</ul>

				<div class="yi">项目管理</div>
				<ul class="er">
					<li><a href="pro" target="right">项目管理</a></li>
					<li><a href="pro?type=showadd" target="right">项目添加</a></li>
				</ul>

				<div class="yi">绩效管理</div>
				<ul class="er">
					<li><a href="sco" target="right">绩效管理</a></li>
					<li><a href="sco?type=showadd" target="right">绩效添加</a></li>
				</ul>

				<div></div>

			</div>
			<iframe id="right" name="right" scrolling="no" frameborder="0"
				src="emp"></iframe>
		</div>
		<div id="bottom"></div>

	</div>
</body>
</html>