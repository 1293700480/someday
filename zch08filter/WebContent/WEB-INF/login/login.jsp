<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<script src="js/jquery-3.3.1.min.js" type="text/javascript"
	charset="utf-8"></script>
<title>Insert title here</title>

<style>
.container {
	display: table;
	height: 100%;
}

.row {
	display: table-cell;
	vertical-align: middle;
}

.row-centered {
	text-align: center;
}

.col-centered {
	display: inline-block;
	float: none;
	text-align: left;
	margin-right: -4px;
}
</style>
<script type="text/javascript">
if(self!=top){
	top.location="user?type=showLogin";
	
}




$().ready(function(){
$("#image").click(function(){
	var a=1;
	$(this).attr("src","random?a="+Math.random())
	
})	
$("#register").click(function(){
	location.href="user?type=showRegister"	
	
})	
	
})
</script>
</head>

<body>

	<div class="container">

		<div class="row row-centered">

			<div class="well col-md-6 col-centered">

				<h2>欢迎登录</h2>

				<form action="user?type=doLogin" method="post"
					class="form-horizontal">
					<div class="input-group input-group-md">

						<span class="input-group-addon" id="sizing-addon1"><i
							class="glyphicon glyphicon-user" aria-hidden="true"></i></span> <input
							type="text" class="form-control" id="userid" name="username"
							placeholder="请输入用户名" value="${name }" />

					</div>

					<div class="input-group input-group-md">
						<span class="input-group-addon" id="sizing-addon1"><i
							class="glyphicon glyphicon-lock"></i></span> <input type="password"
							class="form-control" id="password" name="password"
							placeholder="请输入密码" />

					</div>

					<div class="input-group input-group-md">

						<span class="input-group-addon" id="sizing-addon1"><i
							class="glyphicon glyphicon-warning-sign" aria-hidden="true"></i></span>
						<div class="row"><input type="text" class="col-lg-10" id="random" name="random"
							placeholder="请输入验证码" /><img src="random" id="image" >
</div>
					</div>
					<div id="mes" style="height:20px">${mes }</div>
					<br />

					<button type="submit" class="btn btn-success btn-block">登录</button>

				</form>
<button type="button" class="btn btn-success btn-block" id="register">注册</button>
			</div>

		</div>

	</div>

</body>
</html>