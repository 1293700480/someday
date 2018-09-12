<%@page import="util.Pagination"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
					
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="js/jquery-3.3.1.min.js" type="text/javascript"
	charset="utf-8"></script>
<title>Insert title here</title>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />

<style type="text/css">
#main{
width: 600px;
margin: 20px auto;
}
#pro .select{
background:#337ab7;
}
#pro td{
width:200px;
}
#pro input{
width:100px;
}
#pro select{
width:100px;
height: 25px;
}
</style>
<script type="text/javascript">



$().ready(function () {
	var selectId=-1;
	var ids=[];
	
	
	
	$("#showadd").click(function(){
	location.href="pro?type=showadd"	
		
	})
	$("#showupdate").click(function(){
		if(selectId>-1){
	location.href="pro?type=showupdate&id="	+selectId;
	
		}else{
			alert("请选择数据");
			
		}
	})
	$("#delete").click(function(){
		if(selectId>-1){
	location.href="pro?type=delete&id="	+selectId;
	
		}else{
			alert("请选择数据");
			
		}
	})
	function dobatch(type){
		var length=$("#pro  .select").length;
//		var ids="";
	var ids=new Array();
			if(length>0){
				$("#pro .select").each(function(index,element){
//				ids+=$(this).data("id")+",";
				ids.push($(this).data("id"));
				})
//			ids=ids.substring(0,ids.length-1);
				
		location.href="pro?type="+type+"&ids=" + ids ;
		
			}else{
				alert("请选择数据");
				
			}
		
	}

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

<form action="pro" method="post" class="form-horizontal" role="form">

	<div class="form-group">
		<div class="col-sm-3">
			<input type="text" class="form-control" name="name" 
				   placeholder="请输入名字" value=${c.name }>
		</div>
	
		
		<div class="form-group">
		
			<button type="submit" class="btn btn-default">搜索</button>
		
	</div>
	</div>
	

	
</form>



	<table id="pro" class="table table-bordered  table-striped table-hover" >
	<thead>	<tr><th>id</th><th>名称</th></tr></thead>
		<tbody>
		<c:forEach items="${list}" var="pro">
		<tr data-id="${pro.id}">
		<td >${pro.id}</td>
			<td >${pro.name}</td>
			

		</tr>
		
		</c:forEach>
</tbody>

	</table>
	
	<ul class="pagination" id="ul">
		
			<li id="pre"><a href="pro?ye=${p.ye-1}&name=${c.name}">上一页</a></li>
		<c:forEach begin="${p.beginYe }" end="${p.endYe }" varStatus="status">
			<li <c:if test="${p.ye==status.index }"> class="active"</c:if>>
			<a href="pro?ye=${status.index }&name=${c.name}">${status.index }</a></li>
			</c:forEach>
			<li id="next"><a href="pro?ye=${p.ye+1}&name=${c.name}">下一页</a></li>
		</ul>
	
	<div>
<button id="showadd" type="button"class="btn btn-primary"> 添加</button>
<button id="showupdate" type="button"class="btn btn-primary"> 修改</button>
<button id="delete" type="button"class="btn btn-primary"> 删除</button>


			
</div>

</body>
</html>