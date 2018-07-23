<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
    <%--pageEncoding="UTF-8" %>--%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--<html>--%>
<%--<head>--%>
<%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
<%--<title>iCloud云网盘</title>--%>
<%--</head>--%>

<%--<body>--%>
  <%--<div>--%>
       <%--<a href="login.jsp" >log in</a>  &nbsp;--%>
       <%--<a href="logup.jsp" >log up</a>  &nbsp;--%>
       <%--<a href="" >我的主页</a>&nbsp;--%>
       <%--<a href="help.jsp">帮助</a>--%>
  <%--</div> --%>
  <%--<center>--%>
        <%--<div style="font-size: 25px ; padding-right: 80px ;padding-top: 200px">Log in to upload files to iCloud !</div><!--搜索-->--%>
	   <%--<form action="icloud" method="post"  style="margin: 10px">--%>
	     <%--<input type="text" name="searchcontent" maxlength="50" size="40" style="font-size: 20px;padding-left:3px; padding-top: 5px; padding-bottom: 3px; text-shadow: blue;" >--%>
	     <%--<input type="submit" style=" font-size: 24px;cursor: pointer" value="Search">--%>
	   <%--</form>--%>
  <%--</center>--%>
<%--</body>--%>
<%--</html>--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
   // String path = request.getContextPath();//项目里的路径
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
    <link href="<%=basePath%>/static/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
    <link href="<%=basePath%>/static/css/style.css" rel='stylesheet' type='text/css' />
    <link href="<%=basePath%>/static/css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath%>/static/css/amazeui.min.css" />
    <link rel="stylesheet" href="<%=basePath%>/static/css/admin.css">
    <link href="<%=basePath%>/static/css/custom.css" rel="stylesheet">

    <script src="<%=basePath%>/static/js/jquery.min.js"></script>
    <script type="text/javascript">
        function submit_form(){
            document.forms["search_form"].submit();
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
                    <strong class="am-text-primary am-text-lg">盘友共享</strong> / <small>AllShare</small>
                </div>
            </div>
            <div class="bs-example4" data-example-id="contextual-table">
                <div class="text-center">
                    <form class="form-inline" id="search_form" method="post">
                        <input placeholder="输入要查询的文件名.." name="filename" class="form-control">
                        <a class="btn btn-info" onclick="submit_form()"><i class="fa fa-search nav_icon"></i>搜索</a>
                    </form>
                </div>
                <table class="table">
                    <thead>
                    <tr>
                        <th>文件名</th>
                        <th>大小</th>
                        <th>分享者</th>
                        <th>上传日期</th>
                        <th>下载次数</th>
                        <th>下载</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${fileList }" var="userFile">
                        <tr>
                            <td>${userFile.filename }</td>
                            <td>${userFile.fileSize }</td>
                            <td>${userFile.owner.username }</td>
                            <td>${userFile.createTime }</td>
                            <td>${userFile.count }</td>
                            <td><a class="btn btn-primary" href="download?id=${userFile.id }"><i class="fa fa-download"></i>下载</a></td>
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
</body>
</html>
