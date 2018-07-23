<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	//String path = request.getContextPath();
	String path = "/suye";
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>

<head>
<title>Login One</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="<%=basePath %>/static/css/bootstrap.min.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath %>/static/css/templatemo_style.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath %>/static/css/main.css" rel="stylesheet" type="text/css" >

<script type="text/javascript" src="<%=basePath %>/static/js/jQuery.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/js/fun.base.js"></script>
<script type="text/javascript" src="<%=basePath %>/static/js/script.js"></script>
</head>
<body>
	<div class="login">
			<div class="input">
			<div class="logo png"></div>
				<form
					class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30"
					role="form" action="<%=basePath%>icloud?method=login" method="post">
					<div class="form-group">
						<div class="col-xs-12">
							<div>
								<font color="red">${msg}</font>
							</div>
							<div class="control-wrapper">
								<label for="username">用户名</label> <input type="text"
									required="true" class="text" id="username"
									name="username" placeholder="用户名">
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-12">
							<div class="control-wrapper">
								<label for="password">密　码</label> <input type="password"
									required="true" class="text" id="password"
									name="password" placeholder="密码">
							</div>
						</div>
					</div>
					<div class="form-group"></div>
					<div class="form-group">
						<div class="col-md-12">
							<div class="control-wrapper">
								<div class="row">
									<div class="col-md-6">
										<input type="submit" value="登录"
											class="btn btn-info pull-right">
									</div>
									<div class="col-md-6">
										<a href="register.jsp" class="text-right pull-right">新用户注册</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		<div class="air-balloon ab-1 png"></div>
		<div class="air-balloon ab-2 png"></div>
		<div class="footer">
			<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
				<p>分享你的快乐 </p>
			</div>
		</div>
	</div>
	
</body>
</html>