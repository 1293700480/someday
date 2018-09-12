<%@page import="entity.Employee"%>
<%@ page import="java.util.List,entity.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="js/jquery-3.3.1.min.js" type="text/javascript"
	charset="utf-8"></script>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<script type="text/javascript">
$().ready(function(){
	$("#save").click(function(){
		var emps="";
		$(".emp").each(function(index,element){
		var id=	$(this).find("[name=id]").val();
		var name=	$(this).find("[name=name]").val();
		var sex=	$(this).find("[name=sex]:checked").val();
		var age=	$(this).find("[name=age]").val();
		emps+=id+","+name+","+sex+","+age+";";
		})
		
		emps=emps.substring(0,emps.length -1);
		window.location.href="emp?type=updatebatch2&emps="+emps;
	})
	
	
})

</script>
<style type="text/css">
#main {
	width: 900px;
	margin: 20px auto;
}
.emp{
width:400px;
float:left;
margin:10px 50px 10px 0;
border:1px dashed #ccc;
padding:20px 20px 10px 0;
}
#saveBtn{
clear:both;
text-align: center;

}
</style>
</head>
<body>
	<%
		List<Employee> list = (List<Employee>) request.getAttribute("list");
	
	%>
	<div id="main">
	<%
	for(Employee emp :list) {
	%>
		<form action="emp" method="post" class="form-horizontal emp">
			<input type="hidden" name="type" value="updatebatch1"> <input
				type="hidden" name="id" value="<%=emp.getId()%>">
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">姓名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name"
						value="<%=emp.getName()%>">
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">性别</label>
				<div class="col-sm-10">
					<input type="radio" name="sex" <%if (emp.getSex().equals("男")) {%>
						checked <%}%> value="男">男<input type="radio" name="sex"
						value="女" <%if (emp.getSex().equals("女")) {%> checked <%}%>>女
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">年龄</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="age"
						value="<%=emp.getAge()%>">
				</div>
			</div>
			
		</form>
		<% } %>
		
				<div id="saveBtn">
					<button id="save" type="button" class="btn btn-primary">保存</button>
				
			</div>
	</div>
</body>
</html>