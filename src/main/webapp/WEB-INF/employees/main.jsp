<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>后台管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>

<style>
    html,body{
        height: 100%;
    }
    /*body{
        background-color: #9F9F9F;
    }*/
</style>

<body>
<!--顶部-->
<div style="height: 10%">
    <!-- 水平导航栏 -->
    <ul class="layui-col-md12 layui-col-xs12 layui-nav" style="height: 100%">
        <li class="layui-col-md1 layui-col-xs2 layui-nav-item" style="font-size: 25px;">logo</li>
        <li class="layui-col-md2 layui-col-xs6 layui-nav-item" style="font-size: 25px;">客户信息管理系统</li>
        <li class="layui-col-md1 layui-col-md-offset8 layui-col-xs3 layui-col-xs-offset1 layui-nav-item">
            <a href="#"><img src="//t.cn/RCzsdCq" class="layui-nav-img">我</a>
            <dl class="layui-nav-child">
                <dd><a href="toModifyInfo" target="right">修改信息</a></dd>
                <dd><a href="logout">退了</a></dd>
            </dl>
        </li>
    </ul>
</div>
<!--顶部之下-->
<div  style="height: 90%">
    <div style="height: 100%;width: 14%;float: left">
        <!-- 侧边导航栏 -->
        <ul class="layui-nav layui-nav-tree" lay-filter="test" style="height: 100%">
            <c:forEach items="${menuList}" var="p">
                <shiro:hasPermission name="${p.pname}">
                    <li class="layui-nav-item">
                        <a href="javascript:;">${p.pinfo}</a>
                        <c:forEach items="${p.permissionsListOfSon}" var="pson">
                            <dl class="layui-nav-child">
                                <shiro:hasPermission name="${pson.pname}">
                                    <dd><a href="${pson.purl}" target="right">${pson.pinfo}</a></dd>
                                </shiro:hasPermission>
                            </dl>
                        </c:forEach>
                </shiro:hasPermission>
            </c:forEach>

        </ul>
    </div>

    <div style="height: 100%;width:85%;float: left;/*background-color: #FFFFFF*/">
        <!--主要显示区-->
        <iframe src="toRight" name="right" style="width: 100%;height: 100%;border: none;"></iframe>
    </div>

</div>

<script src="${pageContext.request.contextPath}/layui/layui.js"></script>

<script>
    layui.use(['element'],function (){
        var element = layui.element;
    });
</script>

</body>

</html>