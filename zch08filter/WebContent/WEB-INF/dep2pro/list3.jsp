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
#pro,#noPro{
width:700px;
height: 200px;
border:1px solid #337ab7;
border-radius: 3px;

}
#btn{
margin:20px auto;
width:130px;

}
#add{
margin-right: 50px;
}

.pro{
background: #337ab7;
height: 40px;
line-height: 40px;
float: left;
margin-left: 5px;
color: #fff;
padding: 0 20px;
margin-top: 10px;
border-radius: 3px;
}
.select{
background: #d9534f;
}
</style>
<script type="text/javascript">



$().ready(function () {
	$(".pro").click(function(){
		$(this).toggleClass("select");
	})

	$("#add").click(function(){
		if($("#noPro").find(".select").length>0){
			var str="";
			$(".select").each(function(index,element){
				var proId=$(this).data("id");
				str+=proId+",";
			})
			var proIdList=str.substring(0,str.length-1);
			$.ajax({
				url:"d2p",
				type:"post",
				data:{type:"add2",depId:${dep.id},proIdList:proIdList},
				dataType:"text",
				success:function(data){
					if(data=="true"){
						var pro=$("#noPro").find(".select");
						pro.removeClass("select");
						$("#pro").append(pro);
					}
					
				}
			})
			
	}else{

		alert("请选择数据");
	}
	
	
	
})
	
$("#delete").click(function(){	
			if($("#pro").find(".select").length>0){
				var str="";
				$(".select").each(function(index,element){
					var proId=$(this).data("id");
					str+=proId+",";
				})
				var proIdList=str.substring(0,str.length-1);
				$.ajax({
					url:"d2p",
					type:"post",
					data:{type:"delete2",depId:${dep.id},proIdList:proIdList},
					dataType:"text",
					success:function(data){
					if(data=="true"){
						var pro=$("#pro").find(".select");
						pro.removeClass("select");
						$("#noPro").append(pro);
						
					}
						
					}
				})
				
			}else{
		
				alert("请选择数据");
			}
})





})
</script>


</head>
<body>



<div id="main">

<h2>${dep.name }</h2>
<div id="pro">
<c:forEach items="${list }" var="pro">
<div class="pro" data-id="${pro.id }">${pro.name }</div>


</c:forEach>


</div>
<div id="btn">
<button id="add" type="button"class="btn btn-primary"> ↑</button>
<button id="delete" type="button"class="btn btn-primary"> ↓</button>

</div>

<div id="noPro">
<c:forEach items="${noList }" var="pro">
<div class="pro" data-id="${pro.id }">${pro.name }</div>


</c:forEach>



</div>
</div>

</body>
</html>