<%@page import="cn.od.bean.User"%>
<%@page import="cn.od.util.Const"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	//List<User> userlist = (List<User>)request.getSession().getAttribute("userlist");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>myfollow</title>
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
	function unfollow(id){
		$("#unfollow_id").val(id);
		$("#unfollowModal").modal('show');
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
						<strong class="am-text-primary am-text-lg">我的关注</strong> / <small>AllShare</small>
					</div>
				</div>

				<div class="bs-example4" data-example-id="contextual-table">
					<div class="text-center">
						<form class="form-inline" id="search_form" method="post">
							<input placeholder="输入要查询的用户名.." name="username"
								class="form-control"> <a class="btn btn-info"
								onclick="submit_form()"><i class="fa fa-search nav_icon"></i>搜索</a>
						</form>
					</div>
					<table class="am-table am-table-striped am-table-hover table-main">
						<thead>
							<tr>
								<th class="table-author">作者</th>
								<th>取消关注</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.userlist}" var="user"><!-- 若servlet中是getsession是sessionScope等 -->
								<tr>
									<td>${user.username}</td>
									<td>
                						<div class="am-btn-toolbar">
                  							<div class="am-btn-group am-btn-group-xs">
                    							<input type="button"  value="取消关注" class="btn btn-primary" onclick="unfollow(${user.id })"/>
                  							</div>
                						</div>
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
<!--取消关注 模态框（Modal） -->
<div class="modal fade" id="unfollowModal" tabindex="-1" role="dialog" 
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
    		       确定要取消关注？
         </div>
         <div class="modal-footer">
            <form action="user">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            	<input class="hidden" value="unfollow" name="action">
            	<input class="hidden" id="unfollow_id" name="id">
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
