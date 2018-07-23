<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	//String path = request.getContextPath();
	//out.print(path);
	String path = "/suye";
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
	<title>Register One</title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link href="<%=basePath%>/static/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath%>/static/css/templatemo_style.css" rel="stylesheet" type="text/css">
	<link href="<%=basePath%>/static/css/main.css" rel="stylesheet" type="text/css" />
	
	<script type="text/javascript" src="<%=basePath%>/static/js/jQuery.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/fun.base.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/script.js"></script>
	
	<script type="text/javascript">
		function check(){
		    var name=document.getElementsById("username").val();
			var password=document.getElementById("password").value;
			var confirm=document.getElementById("confirm-password").value;
			if(password!=confirm){
				alert("两次密码输入不一致！");
				return false;
			}
//            $.post("icloud",
//                {
//                    name:name,
//                    pass:password
//                },
//                function(data,status){
//                    alert("数据: \n" + data + "\n状态: " + status);
//                });
			return true;
		}

	</script>
</head>
<body>
	<div class="login">
			<div class="input">
			<div class="logo png"></div>
			<form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" role="form" action="<%=basePath%>icloud?method=register" method="post">
		        <div class="form-group">
		          <div class="col-xs-12">		            
		            <div class="control-wrapper">
		            	<label for="username" class="control-label fa-label"><i class="fa fa-user fa-medium"></i></label>
		            	<input type="text" 	required="true" class="form-control" id="username" name="username" placeholder="用户名"><font color='red'>${msg}</font>
		            </div>		            	            
		          </div>              
		        </div>
		        <div class="form-group">
		          <div class="col-md-12">
		          	<div class="control-wrapper">
		            	<label for="password" class="control-label fa-label"><i class="fa fa-lock fa-medium"></i></label>
		            	<input type="password" required="true" class="form-control" id="password" name="password" placeholder="密码">
		            </div>
		          </div>
		        </div>
		        <div class="form-group">
		          <div class="col-md-12">
		          	<div class="control-wrapper">
		            	<label for="password" class="control-label fa-label"><i class="fa fa-lock fa-medium"></i></label>
		            	<input type="password" required="true" class="form-control" id="confirm-password" name="confirm-password" placeholder="确认密码">
		            </div>
		          </div>
		        </div>
		       
		        <div class="form-group">
		          <div class="col-md-12">
		          	<div class="control-wrapper">
		          		<input type="submit" value="注册" class="btn btn-info center-block" onclick="return check();"  >
		          		<font color='blue'>${msg2}</font>
		          	</div>
		          	<div class="col-md-12">
		          			<a href="<%=basePath%>common/login.jsp" class="text-right pull-right">返回用户登录页面</a>
					</div>
		          </div>
		        </div>
		      </form>
		</div>
		<div class="air-balloon ab-1 png"></div>
		<div class="air-balloon ab-2 png"></div>
		<div class="footer">
			<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
				<p>分享你的快乐</p>
			</div>
		</div>
	</div>
</body>
</html>