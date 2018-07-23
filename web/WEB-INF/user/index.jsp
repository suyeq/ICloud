<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Home</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="static/css/bootstrap.min.css" rel='stylesheet'
	type='text/css' />
<link href="static/css/style.css" rel='stylesheet' type='text/css' />
<link href="static/css/font-awesome.css" rel="stylesheet">
<link rel="stylesheet" href="static/css/amazeui.min.css" />
<link rel="stylesheet" href="static/css/admin.css">
<link href="static/css/custom.css" rel="stylesheet">

<script src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function submit_form() {
		document.forms["search_form"].submit();
	}
	function addfollow(id){
		$("#addfollow_id").val(id);
		$("#addfollowModal").modal('show');
	}
	function pswdown(id){
		$("#pswdown_id").val(id);
		$("#pswdownModal").modal('show');
	}
</script>
</head>

<body>
	<div id="wrapper">
		<%@include file="common/top.jsp"%>
		<div id="page-wrapper">
			<div class="admin-content">

				<div class="am-cf am-padding">
					<div class="am-fl am-cf">
						<strong class="am-text-primary am-text-lg">盘友共享</strong> / <small>AllShare</small>
					</div>
				</div>

				<div class="bs-example4" data-example-id="contextual-table">
					<div class="text-center">
						<form class="form-inline" id="search_form" method="post">
							<input placeholder="输入要查询的文件名.." name="filename"
								class="form-control"> <a class="btn btn-info"
								onclick="submit_form()"><i class="fa fa-search nav_icon"></i>搜索</a>
						</form>
					</div>
					<table class="table">
						<thead>
							<tr>
								<th>文件名</th>
								<th>大小</th>
								<th>上传日期</th>
								<th>下载次数</th>
								<th>下载</th>
								<th>分享者</th>
								<th>添加关注</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${fileList }" var="userFile">
								<tr>
									<td>${userFile.filename }</td>
									<td>${userFile.fileSize }</td>
									<td>${userFile.createTime }</td>
									<td>${userFile.count }</td>
									<td>
									<c:choose>
										<c:when test="${userFile.filepassword==null}">
											<a class="btn btn-primary"
											href="download?id=${userFile.id }"><i
											class="fa fa-download" ></i>下载</a>
										</c:when>
										<c:otherwise>
											<input type="button"  value="密码下载" class="btn btn-primary" onclick="pswdown(${userFile.id })"/>
										</c:otherwise>
									</c:choose>
									</td>
									<td>${userFile.owner.username }</td>
									<td>
									<c:choose>
										<c:when test="${userFile.followed==1 }">
											<div style="margin-left:20px;">
												<font color=#999>已关注</font>
											</div>
										</c:when>
										<c:otherwise>
											<input type="button"  value="添加关注" class="btn btn-primary" onclick="addfollow(${userFile.owner.id })"/>
										</c:otherwise>
									</c:choose>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
	
<!--输入分享密码 模态框（Modal） -->
<div class="modal fade" id="pswdownModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
          		  请输入密码
            </h4>
         </div>
         <form action="download" id="pswdown_form">
         <div class="modal-body">
    		<input type="password" required="true" class="form-control" id="filePassword" name="filePassword" placeholder="密码">
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            	<input class="hidden" value="download" name="action">
            	<input class="hidden" id="pswdown_id" name="id">
           		<input type="submit" class="btn btn-primary" value="确定" />
         </div>
         </form>
      </div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>

<!--添加关注 模态框（Modal） -->
<div class="modal fade" id="addfollowModal" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
          		   确认
            </h4>
         </div>
         <div class="modal-body">
    		       确定要添加关注？
         </div>
         <div class="modal-footer">
            <form action="user">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            	<input class="hidden" value="addfollow" name="action">
            	<input class="hidden" id="addfollow_id" name="id">
           		<input type="submit" class="btn btn-primary" value="确定" />
            </form>
         </div>
      </div>
      <!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>
</body>
</html>
