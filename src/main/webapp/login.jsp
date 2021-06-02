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

<h>先访问index 跳转到login ，login页面提交后，认证通过后跳转到主页面，失败回到login页面，并携带提示信息</h>
<h>欢迎观看登录界面</h><br>
<h1>用户登录</h1>


<form action="${pageContext.request.contextPath}/user/login" method="post">
    用户名:<input type="text" name="username" > <br/>
    密码  : <input type="text" name="password"> <br>
    请输入验证码: <input type="text" name="myVerifyCode"><img src="${pageContext.request.contextPath}/user/getImage" alt=""><br>
    <input type="submit" value="登录">
</form>
错误信息:<font color="red"> ${error} </font>
</body>
</html>