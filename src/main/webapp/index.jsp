<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/user/logout">退出用户</a>
<h1>人力管理系统主页V1.0</h1>
<h>欢迎登录啊！！！</h><br>
<shiro:hasAnyRoles name="admin,user">
<p>
<ul>
    <a href="#">订单管理</a>
    <shiro:hasPermission name="haha:add:01">
   <li><a href="">添加</a></li>
    </shiro:hasPermission>
    <shiro:hasPermission name="haha:update:02">
        <li><a href="">修改</a></li>
    </shiro:hasPermission>
    <shiro:hasPermission name="haha:query:03">
        <li><a href="">查询</a></li>
    </shiro:hasPermission>
    <shiro:hasPermission name="haha:del:04">
        <li><a href="">删除</a></li>
    </shiro:hasPermission>
</ul>
</p>
</shiro:hasAnyRoles>
<shiro:hasRole name="admin">
<p><a href="#">用户管理</a> </p>
<p><a href="#">系统管理</a> </p>
<p><a href="#">日志管理</a> </p>
</shiro:hasRole>
<shiro:hasPermission name="worker:01:01">
<p><a href="${pageContext.request.contextPath}/user/work">工单管理</a> </p>
</shiro:hasPermission>
</body>
</html>