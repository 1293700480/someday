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

#sco .select {
	background: #337ab7;
}

#sco td {
	width: 200px;
}

#sco input {
	width: 100px;
}

#sco select {
	width: 100px;
	height: 25px;
}
</style>
<script type="text/javascript">




$().ready(function () {
	var selectId=-1;
	var ids=[];
	
	
	
	$("#showadd").click(function(){
	location.href="sco?type=showadd"	
		
	})

	
	$("#sco input").blur(function(){
		var scoValue=$(this).val();
		var empId=$(this).data("empid");
		var proId=$(this).data("proid");
		var id=$(this).data("id");
		var input=$(this);
		$.ajax({
			url:"sco",
			type:"post",
			data:{
			type:"input",
			id:id,
			scoValue:scoValue,
			empId:empId,
			proId:proId
			
			
			},
			dataType:"json",
			success:function(data){
			
		input.parent().next().html(data.grade);
				input.data("id",data.id);
			}
		})
		
		
	})
	
	
//$(".value").dblclick(function(){
//	$(this).unbind("dblclick");
//	$(this).unbind("click");
	
//	var value= $(this).text();
//	$(this).html("<input type='text' id='fname' class='fname' onblur='upperCase()' name='value' value='"+value+"' />")
//})

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
//	function upperCase()
//	{
//	var x=document.getElementById("fname").value;
//	var scoId=$(this).parent().data("id");
//	$.ajax({
//		url:"sco",
//		type:"post",
//		data:{type:"update",scoId:scoId,scoValue:x},
//		dataType:"text",
	//	success:function(data){
		//if(data=="true"){
			//
//		$(".value").html(x);
	//	}
			
//		}
//	})
//	}

</script>


</head>
<body>



	<div id="main">

		<form action="sco" method="post" class="form-horizontal" role="form">

			<div class="form-group">
				<div class="col-sm-3">
					<input type="text" class="form-control" name="name"
						placeholder="名字" value=${c.emp.name }>
				</div>
				
				<div class="col-sm-3">
					<select name="depName" class="form-control">
						<option value="">部门</option>
						<c:forEach items="${depList}" var="dep">
							<option value="${dep.name }"
								<c:if test="${dep.name == c.emp.dep.name}">selected</c:if>>${dep.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-sm-3">
					<select name="proName" class="form-control">
						<option value="">项目</option>
						<c:forEach items="${ proList}" var="pro">
							<option value="${pro.name }"
								<c:if test="${pro.name == c.pro.name}">selected</c:if>>${pro.name }</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">

					<button type="submit" class="btn btn-default">搜索</button>

				</div>
			</div>



		</form>



		<table id="sco"
			class="table table-bordered  table-striped table-hover">
			<thead>
				<tr>
					<th>姓名</th>
					<th>部门</th>
					<th>项目</th>
					<th>分数</th>
					<th>成绩</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${scoList}" var="sco">
					<tr>
						<td>${sco.emp.name}</td>
						<td>${sco.emp.dep.name}</td>
						<td>${sco.pro.name}</td>
						<td><input data-id="${sco.id}" data-empid="${sco.emp.id }" data-proid="${sco.pro.id }" type="text" value="${sco.value}"/></td>
						<td>${sco.grade}</td>
					</tr>

				</c:forEach>
			</tbody>

		</table>

		<ul class="pagination" id="ul">
			<li id="first"><a
				href="sco?ye=1&name=${c.emp.name}&depName=${c.emp.dep.name }&proName=${c.pro.name }">首页</a></li>
			<li id="pre"><a
				href="sco?ye=${p.ye-1}&name=${c.emp.name}&depName=${c.emp.dep.name }&proName=${c.pro.name }">上一页</a></li>
			<c:forEach begin="${p.beginYe }" end="${p.endYe }" varStatus="status">
				<li <c:if test="${p.ye==status.index }"> class="active"</c:if>><a
					href="sco?ye=${status.index }&name=${c.emp.name}&depName=${c.emp.dep.name }&proName=${c.pro.name }">${status.index }</a></li>
			</c:forEach>
			<li id="next"><a
				href="sco?ye=${p.ye+1}&name=${c.emp.name}&depName=${c.emp.dep.name }&proName=${c.pro.name }">下一页</a></li>
			<li><a
				href="sco?ye=${p.maxYe}&name=${c.emp.name}&depName=${c.emp.dep.name }&proName=${c.pro.name }">尾页</a></li>
		</ul>
</body>
</html>