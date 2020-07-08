<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>登入</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/iconic/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/util.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<!-- 我的HTML代码 -->
<div class="limiter">
    <div class="container-login100" style="background-image: url('${pageContext.request.contextPath}/images/bg-01.jpg');">
        <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
            <form class="login100-form validate-form" id="loginForm" action="${pageContext.request.contextPath}/login" method="post">
                <span class="login100-form-title p-b-49">登录</span>

                <div class="wrap-input100 validate-input m-b-23" data-validate="请输入用户名">
                    <span class="label-input100">用户名</span>
                    <input class="input100" type="text" name="emploginname" placeholder="请输入用户名">
                    <span class="focus-input100" data-symbol="&#xf206;"></span>
                </div>

                <div class="wrap-input100 validate-input" data-validate="请输入密码" autocomplete="off">
                    <span class="label-input100">密码</span>
                    <input class="input100" type="password" name="emppassword" placeholder="请输入密码">
                    <span class="focus-input100" data-symbol="&#xf190;"></span>
                </div>

                <div class="p-t-15 p-b-31">
                    <input type="checkbox" name="remember">
                    <label>保持登入</label>

                    <a href="javascript:" style="padding-left: 52%">忘记密码？</a>
                </div>

                <div class="container-login100-form-btn">
                    <div class="wrap-login100-form-btn">
                        <div class="login100-form-bgbtn"></div>
                        <button class="login100-form-btn" type="submit">登 录</button>
                    </div>
                </div>

                <%--<div class="flex-col-c p-t-25">
                    <a href="toRegister" class="txt2">立即注册</a>
                </div>--%>
            </form>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>

<script>
    if (window != top){
        top.location.href = location.href;
    }
</script>


</body>
</html>
