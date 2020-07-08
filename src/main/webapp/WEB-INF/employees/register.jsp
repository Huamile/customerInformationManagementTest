<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>注册</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/iconic/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/util.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
<div class="limiter">
    <div class="container-login100" style="background-image: url('${pageContext.request.contextPath}/images/bg-01.jpg');">
        <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
            <form class="login100-form validate-form" id="registerForm"  action="${pageContext.request.contextPath}/register" method="post">
                <span class="login100-form-title p-b-49"><f:message key="register"/><%--注册--%></span>

                <div class="wrap-input100 validate-input m-b-23" data-validate="<f:message key="enterUserName"/><%--请输入用户名--%>">
                    <span class="label-input100"><f:message key="userName"/><%--用户名--%></span>
                    <input class="input100" type="text" name="emploginname" placeholder="<f:message key="enterUserName"/><%--请输入用户名--%>" autocomplete="off">
                    <span class="focus-input100" data-symbol="&#xf206;"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-23" data-validate="<f:message key="enterPassword"/><%--请输入密码--%>">
                    <span class="label-input100"><f:message key="password"/> <%--密码--%></span>
                    <input class="input100" type="password" name="emppassword" placeholder="<f:message key="enterPassword"/><%--请输入密码--%>" autocomplete="off">
                    <span class="focus-input100" data-symbol="&#xf190;"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-23" data-validate="<f:message key="enterPasswordAgain"/><%--请再次输入密码--%>" autocomplete="off">
                    <span class="label-input100"><f:message key="confirmPassword"/> <%--确认密码--%></span>
                    <input class="input100" type="password" name="pass" placeholder="<f:message key="enterPasswordAgain"/><%--请再次输入密码--%>">
                    <span class="focus-input100" data-symbol="&#xf190;"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-23" data-validate="<f:message key="enterName"/> <%--请输入姓名--%>" autocomplete="off">
                    <span class="label-input100"><f:message key="name"/> <%--姓名--%></span>
                    <input class="input100" type="text" name="empname" placeholder="<f:message key="enterName"/> <%--请输入姓名--%>">
                    <span class="focus-input100" data-symbol="&#xf207;"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-23" data-validate="<f:message key="enterContactNumber"/> <%--请输入联系电话--%>" autocomplete="off">
                    <span class="label-input100"><f:message key="phone"/> <%--联系电话--%></span>
                    <input class="input100" type="text" name="emptel" placeholder="<f:message key="enterContactNumber"/> <%--请输入联系电话--%>">
                    <span class="focus-input100" data-symbol="&#xf2be;"></span>
                </div>


                <div class="wrap-input100 validate-input m-b-23" data-validate="<f:message key="enterEmail"/> <%--请输入邮箱--%>" autocomplete="off">
                    <span class="label-input100"><f:message key="email"/> <%--邮箱--%></span>
                    <input class="input100" type="email" name="empemail" placeholder="<f:message key="enterEmail"/> <%--请输入邮箱--%>">
                    <span class="focus-input100" data-symbol="&#xf15a;"></span>
                </div>

                <div class="wrap-input100 validate-input m-b-23" data-validate="<f:message key="selectType"/> <%--请选择用户类型--%>" autocomplete="off">
                    <span class="label-input100"><f:message key="userType"/> <%--用户类型--%></span>
                    <input class="input100" type="text" name="emptype" value="<f:message key="cooperationDirector"/> <%--合作主管--%>" readonly>
                    <span class="focus-input100" data-symbol="&#xf209;"></span>
                </div>

                <div class="wrap-input60 validate-input m-b-23" data-validate="<f:message key="enterCode"></f:message> <%--请输入图形验证码--%>" autocomplete="off">
                    <span class="label-input100"><f:message key="verify"/> <%--验证--%></span><br>
                    <input class="input100" type="text" name="verify" style="display: inline" placeholder="<f:message key="enterCode"/> <%--图形验证码--%>">
                    <span class="focus-input100" data-symbol="&#xf1c7;"></span>
                </div>

                <div class="wrap-input-images validate-input m-b-23">
                    <img src="https://www.oschina.net/action/user/captcha">
                </div>

                <div class="p-t-20 p-b-31"/>

                <div class="container-login100-form-btn">
                    <div class="wrap-login100-form-btn">
                        <div class="login100-form-bgbtn"></div>
                        <button class="login100-form-btn" type="submit"><f:message key="register1"/> <%--注 册--%></button>
                    </div>
                </div>

                <div style="margin-top: 20px">
                    <a href="changeLanguage?locale=zh_CN">中文</a>
                    <a href="changeLanguage?locale=en_US" style="margin-left: 20px">English</a>
                </div>

                <div class="flex-col-c p-t-25">
                    <a href="toLogin" class="txt2"><f:message key="login"/> <%--用已有账号登入--%></a>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/vendor/jquery/jquery-3.2.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>

<%--表单验证--%>
<%--<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/js/messages_zh.min.js"></script>--%>

<script>
    if (window != top){
        top.location.href = location.href;
    }
</script>


<script>
    /*表单不会提交，只进行检查*/
    /*$().ready(function() {
        $("#registerForm").validate({
            debug:true
        });
    });*/

    // 在键盘按下并释放及提交后验证提交表单
    /*$().ready(function() {
        $("#signupForm").validate({
            rules: {
                emploginname: {
                    required: true,
                    minlength: 4
                },
                emppassword: {
                    required: true,
                    minlength: 7,
                    maxlength: 20
                },
                pass: {
                    required: true,
                    minlength: 5,
                    maxlength: 20,
                    equalTo: "#emppassword"
                },
                empname: "required",
                emptel: {
                    required: true,

                },
                empemail: {
                    required: true,
                    email: true
                },

            },
            messages: {
                emploginname: {
                    required: "请输入用户名",
                    minlength: "用户名必需最少由四个字母组成"
                },
                emppassword: {
                    required: "请输入密码",
                    minlength: "密码长度不能小于 7 个字母",
                    maxlength: "密码长度不能大于 20 个字母"
                },
                pass: {
                    required: "请输入密码",
                    minlength: "密码长度不能小于 7 个字母",
                    maxlength: "密码长度不能大于 20 个字母",
                    equalTo: "两次密码输入不一致"
                },
                empname: "请输入姓名",
                emptel: {
                    required: "请输入电话",

                },
                empemail: {
                    required: "请输入邮箱",
                    email: "邮箱格式不正确"
                }
            }
        })
    });*/
</script>

</body>
</html>
