<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="js/jquery-3.3.1.min.js" type="text/javascript"
	charset="utf-8"></script>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" />
<style type="text/css">
#main {
	width: 400px;
	margin: 20px auto;
}
#pics img{
width:100px;
height: 100px;
}
</style>
<script type="text/javascript">
$().ready(function(){
	$("#upload").click(function(){
		
		var formData=new FormData();
		
//		for(var i=0;i<$("[name=myFile]")[0].files.length;i++){
//			formData.append("myFile",$("[name=myFile]")[0].files[i]);
			
	//上传多个		
			
//		}
			formData.append("myFile",$("[name=myFile]")[0].files[0]);
		$.ajax({
			url:"emp?type=upload",
			type:"post",
			data:formData,
			cache:false,
			processData:false,
			contentType:false,
				dataType:"text",
			success:function(data){
				var str="<img src='pic/"+data+"'>"
				str+="<input type='hidden' name='picture' value='"+data+"'/>";
	//		$("#pics").html(str);	
				$("#pics").append(str);
			}
			
		})
		
		
	})
$(document).on("click","#pics img",function(){
	$(this).next().remove();
	$(this).remove();
	$.ajax({
		url:"emp?type=upload",
		type:"post",
		data:formData,
	
			dataType:
		success:function(data){
		
		}
		
	})
})
	
})


</script>

</head>
<body>
	<div id="main">
		<form action="emp?type=add2" method="post" class="form-horizontal"  >
			<input type="hidden" name="type" value="add">
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">姓名</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="name"
						placeholder="请输入名字">
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">性别</label>
				<div class="col-sm-10">
					<input type="radio" name="sex" checked="checked" value="男">男<input
						type="radio" name="sex" value="女">女
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">年龄</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="age"
						placeholder="请输入年龄">
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">部门</label>
				<div class="col-sm-10">
					<select name="depId" class="form-control">
						<option value="">部门</option>
						<c:forEach items="${ depList}" var="dep">

							<option value="${dep.id }">${dep.name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">图片</label>
				<div class="col-sm-7">
					<input type="file" class="form-control" name="myFile" multiple value="选择文件">
				</div>
				<div class="col-sm-3">
					<input type="button" id="upload"  class="form-control"  value="上传">
				</div>
			</div>
			<div class="form-group" id="pics">
			
			</div>
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">保存</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>