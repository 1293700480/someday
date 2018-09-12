<%@page import="util.Pagination"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
					
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="f" %>	


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

	
	$("#add").click(function(){
		var proId=$("#selectPro").val(); 
	//location.href="d2p?type=add&depId=${dep.id}&proId="	+proId; 
	var i=0;
	$.ajax({
		url:"d2p",
		type:"post",
		data:{ type:"add3",depId:${dep.id},proId:proId},
	dataType:"text",
	success:function(data){
		if(data=="true"){
			var proName="";
			$("#selectPro").children().each(function(index,element){
			if($(this).val()==proId){
				proName=$(this).text();
				i=index;
			}	
			})
			var tr="<tr data-id="+proId+"><td>"+proId+"</td><td>"+proName+"</td></tr>"
			$("#pro").append(tr)
			$("#selectPro").children().eq(i).remove();
			if($("#selectPro").children().length==0){
				
				$("#add").unbind("click");
				$("#add").addClass("disabled");
			}
		}
	}
	})
	
	})
	
	//jq判断
	
	
	
//	 jsp
//	<c:if test="${f:length(noList)==0}">
//	$("#add").unbind("click");
//	$("#add").addClass("disabled");
//	</c:if>
	
	$("#delete").click(function(){
		if(selectId>-1){
			var i=0;
			$.ajax({
				url:"d2p",
				type:"post",
				data:{ type:"delete3",depId:${dep.id},proId:selectId}, 
			dataType:"text",
			success:function(data){
				if(data=="true"){
					var proName="";
					$("tr").each(function(index,element){
					if($(this).data("id")==selectId){
						proName=$(this).children().eq(1).text();
						i=index;
					}	
					})
					var option="<option value='"+selectId+"'>"+proName+"</option>"
					$("#selectPro").append(option)
					$("tr").eq(i).remove();
				}
			}
			})

		}else{
			alert("请选择数据");
			
		}
	})
	$(document).on("click","tr",function(){
		
		$(this).toggleClass("select");
	//一行一列的值	selectId=$(this).children().eq(0).text();
		selectId=$(this).data("id");
	})


})
</script>


</head>
<body>



<div id="main">

<h2>${dep.name }</h2>


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
	
	<div>
	<div class="col-sm-4" >
	<select class="form-control" id="selectPro">
	<c:forEach items="${noList }" var="pro">
	<option value="${pro.id }">	${pro.name}</option >
	
	</c:forEach>
	
	</select>
	</div>
	<c:if test="">
	</c:if>
	
	
<button id="add" type="button"class="btn btn-primary"> 添加</button>

<button id="delete" type="button"class="btn btn-primary"> 删除</button>


			
</div>

</body>
</html>