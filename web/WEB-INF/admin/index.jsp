<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = "/suye";
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Home</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="static/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<link href="static/css/style.css" rel='stylesheet' type='text/css' />
<link href="static/css/font-awesome.css" rel="stylesheet">
<link href="static/css/custom.css" rel="stylesheet">
<link rel="stylesheet" href="static/css/amazeui.min.css" />
<link rel="stylesheet" href="static/css/admin.css">

<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function submit_form(){
		document.forms["search_form"].submit();
	}
	function delete_submit_form(){
		document.forms["dele_form"].submit();
	}
	function select_all(){
		var check = $("#chk_all");
		var ids = $('input[name="ids"]');
			if(check.prop('checked'))
			{
				ids.prop("checked", true);
			}
			else
			{
				ids.prop("checked", false);
				check.prop("checked", false);
			}
	}
	function dele(){
		var ids = $('input[name="ids"]');
		var haveDeleFile = false;
		ids.each(function(){
			if($(this).prop('checked')==true){//选中
				haveDeleFile = true;
			}
		});
		if(haveDeleFile == true){
			$("#deleModal").modal('show');
		}else{
			alert("未选择要删除的文件！");
		}
	}
</script>

</head>

<body>
	<div id="wrapper">
		<%@include file="common/top.jsp" %>
		<div id="page-wrapper">
			<div class="admin-content">

				<div class="am-cf am-padding">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">所有共享</strong> / <small>AllShare</small>
					</div>
				</div>

					<div class="bs-example4" data-example-id="contextual-table">
					<div class="row">
						<div class="col-md-2">
							<button class="btn btn-danger" onclick="dele()">删除所选项</button>
						</div>
						<div class="col-md-10 text-center">
							<form class="form-inline" id="search_form" method="post" action="<%=basePath%>icloud?method=admin">
								<input placeholder="输入要查询的文件名.." class="form-control" name="filename">
								<a onclick="submit_form()" class="btn btn-info"><i class="fa fa-search nav_icon"></i>搜索</a>
							</form>
						</div>
					</div>
						<table class="table">
							<thead>
								<tr>
									<th><input id="chk_all" onchange="select_all()" type="checkbox"></th>
									<th>文件名</th>
									<th>大小</th>
									<th>分享者</th>
									<th>修改日期</th>
									<th>下载次数</th>
									<th>下载</th>
								</tr>
							</thead>
							<form action="<%=basePath%>icloud?" id="dele_form" method="post">
							<input type="hidden" name="method" value="delete">
							<input type="hidden" name="action" value="">
							<tbody>
								<c:forEach items="${fileList }" var="userFile">
								<tr>
									<th scope="row"><input type="checkbox" name="ids" value="${userFile.id }"></th>
									<td>${userFile.filename }</td>
									<td>${userFile.fileSize }</td>
									<td>${userFile.owner.username }</td>
									<td>${userFile.createTime }</td>
									<td>${userFile.count }</td>
									<td><a class="btn btn-primary" href="icloud/download?id=${userFile.id }" data-target="#pswdown"><i class="fa fa-download"></i>下载</a></td>
									<!-- 有密码 <td><a class="btn btn-primary" data-toggle="modal" data-target="#pswdown"><i class="fa fa-download"></i>下载</a></td> -->
								</tr>
							</c:forEach>
							</tbody>
							</form>
						</table>
					</div>
				</div>
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
	<!--删除 模态框（Modal） -->
<div class="modal fade" id="deleModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
          		   文件删除
            </h4>
         </div>
         <div class="modal-body">
    		       确定要删除？
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <a class="btn btn-danger" onclick="delete_submit_form()">确定删除</a>
         </div>
      </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
    <!-- /#wrapper -->
	<!--密码 模态框（Modal） -->
<!-- <div class="modal fade" tabindex="-1" role="pswdown">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">请输入密码</h4>
      </div>
      <div class="modal-body">
        <input type="text" class="form-control" id="sharepswd">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">退出</button>
        <button type="button" class="btn btn-primary">确定</button>
      </div>
    </div>/.modal-content
  </div>/.modal-dialog
</div>/.modal -->
</body>
</html>
