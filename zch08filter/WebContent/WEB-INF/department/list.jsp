<%@page import="util.Pagination"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="js/jquery-3.3.1.min.js" type="text/javascript"
	charset="utf-8"></script>
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />

<style type="text/css">
#main {
	width: 600px;
	margin: 20px auto;
}

#dep .select {
	background: #337ab7;
}

#dep td {
	width: 200px;
}

#dep input {
	width: 100px;
}

#dep select {
	width: 100px;
	height: 25px;
}
</style>
<script type="text/javascript">



$().ready(function () {
	var selectId=-1;
	var ids=[];
	
	
	
	$("#showadd").click(function(){
	location.href="dep?type=showadd"	
		
	})
	$("#showupdate").click(function(){
		if(selectId>-1){
	location.href="dep?type=showupdate&id="	+selectId;
	
		}else{
			alert("请选择数据");
			
		}
	})
	$("#delete").click(function(){
		if(selectId>-1){
	location.href="dep?type=delete&id="	+selectId;
	
		}else{
			alert("请选择数据");
			
		}
	})
	
		$("#manageProject").click(function(){
		if(selectId>-1){
	location.href="d2p?depId="	+selectId;
	
		}else{
			alert("请选择数据");
			
		}
	})
		$("#manageProject2").click(function(){
		if(selectId>-1){
	location.href="d2p?type=m2&depId="	+selectId;
	
		}else{
			alert("请选择数据");
			
		}
	})
		$("#manageProject3").click(function(){
		if(selectId>-1){
	location.href="d2p?type=m3&depId="	+selectId;
	
		}else{
			alert("请选择数据");
			
		}
	})
	
	

	$("tr").click(function(){
		
		$(this).toggleClass("select");
	//一行一列的值	selectId=$(this).children().eq(0).text();
		selectId=$(this).data("id");
	})
	
if(${p.ye}<=1){
	$("#pre").addClass("disabled");
	
$("#pre").find("a").attr("onclick","return false");
}
	if(${p.ye}>=${p.maxYe}){
		$("#next").addClass("disabled");
	
	$("#next").find("a").attr("onclick","return false");
	}


})
</script>


</head>
<body>



	<div id="main">

		<form action="dep" method="post" class="form-horizontal" role="form">

			<div class="form-group">
				<div class="col-sm-3">
					<input type="text" class="form-control" name="name"
						placeholder="请输入名称" value=${c.name }>
				</div>
				<div class="col-sm-3">
					<input type="text" class="form-control" name="empCount"
						placeholder="请输入人数" value=${c.empCount!=-1?c.empCount:''}>
				</div>

				<div class="form-group">

					<button type="submit" class="btn btn-default">搜索</button>

				</div>
			</div>



		</form>



		<table id="dep"
			class="table table-bordered  table-striped table-hover">
			<thead>
				<tr>
					<th>id</th>
					<th>名称</th>
					<th>人数</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="dep">
					<tr data-id="${dep.id}">
						<td>${dep.id}</td>
						<td>${dep.name}</td>
						<td><a href="emp?depId=${dep.id}">${dep.empCount}</a></td>

					</tr>

				</c:forEach>
			</tbody>

		</table>

		<ul class="pagination" id="ul">

			<li id="pre"><a
				href="dep?ye=${p.ye-1}&name=${c.name}&empCount=${c.empCount!=-1?c.empCount:''}">上一页</a></li>
			<c:forEach begin="${p.beginYe }" end="${p.endYe }" varStatus="status">
				<li <c:if test="${p.ye==status.index }"> class="active"</c:if>><a
					href="dep?ye=${status.index }&name=${c.name}&empCount=${c.empCount!=-1?c.empCount:''}">${status.index }</a></li>
			</c:forEach>
			<li id="next"><a
				href="dep?ye=${p.ye+1}&name=${c.name}&empCount=${c.empCount!=-1?c.empCount:''}">下一页</a></li>
		</ul>

		<div>
			<button id="showadd" type="button" class="btn btn-primary">
				添加</button>
			<button id="showupdate" type="button" class="btn btn-primary">
				修改</button>
			<button id="delete" type="button" class="btn btn-primary">
				删除</button>
<button id="manageProject" type="button" class="btn btn-primary">
				管理项目</button>
<button id="manageProject2" type="button" class="btn btn-primary">
				管理项目2</button>
<button id="manageProject3" type="button" class="btn btn-primary">
				管理项目3</button>
		</div>
</body>
</html>