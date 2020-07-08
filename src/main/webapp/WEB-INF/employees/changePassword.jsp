<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>修改密码</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>

<form id="updatePasswordForm" class="layui-form layui-form-pane" style="margin-top: 20px;align-self: center">
    <div class="layui-form-item" style="margin-top: 20px">
        <label class="layui-form-label">当前密码</label>
        <div class="layui-input-inline">
            <input type="password" id="oldPassword" required  lay-verify="required" placeholder="请输入当前密码" autocomplete="off" class="layui-input">
        </div>
        <div id="tip1" class="layui-form-mid layui-word-aux"></div>
    </div>
    <div class="layui-form-item" style="margin-top: 20px">
        <label class="layui-form-label">新密码</label>
        <div class="layui-input-inline">
            <input type="password" name="emppassword" id="emppassword" required lay-verify="required|pass" placeholder="请输入新密码" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux"></div>
    </div>
    <div class="layui-form-item" style="margin-top: 20px">
        <label class="layui-form-label">确认新密码</label>
        <div class="layui-input-inline">
            <input type="password" id="ensurePassword" required lay-verify="required|pass" placeholder="请再次输入新密码" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux"></div>
    </div>
    <div class="layui-form-item" style="margin-top: 20px">
        <div class="layui-input-inline">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>


<script src="${pageContext.request.contextPath}/layui/layui.js"></script>

<script>
    layui.use(['jquery','form'],function (){
        var $ = layui.jquery,
            form = layui.form;
        $('#oldPassword').on('blur',function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/ensurePassword",
                type:"post",
                data: {'loginName':'${loginName}','password': $("#oldPassword").val()},
                success:function (data) {
                    $("#tip1").html("密码验证结果："+data.msg);
                    if (data.msg == "与原密码不匹配"){
                        $("#oldPassword").val("");
                    }
                }
            });
        });
        /*当前密码与新密码是否相同验证(还没解决两个为空会相同)*/
        $('#emppassword').on('blur',function () {
            if ($("#emppassword").val() == $("#oldPassword").val()) {
                layer.msg("当前密码与新密码相同");
                $("#emppassword").val("");
            }
        });
        /*确认密码与新密码是否相同验证(还没解决两个为空会相同)*/
        $('#ensurePassword').on('blur',function () {
            if ($("#emppassword").val() != $("#ensurePassword").val()){
                layer.msg("输入的两次密码不一致");
                $("#ensurePassword").val("");
            }
        });

        form.on('submit(formDemo)',function (data) {
            /*layer.msg(JSON.stringify(data.field));
            return false;*/

            $.ajax({
                url: "empPasswordUpdate",
                type: "post",
                data: JSON.stringify(data.field),
                contentType: "application/json;charset=utf-8",
                success:function(data){
                    var i = data.num;

                    alert(data.msg);

                    if(1 == i){

                        window.location.href='redirect:toLogin';
                        window.reload();

                    }else if (0 == i){
                        $("#oldPassword").val("");
                        $("#emppassword").val("");
                        $("#ensurePassword").val("");
                    }
                }
            });
        });

        /*自定义表单验证*/
        form.verify({
            username: function(value, item){ //value：表单的值、item：表单的DOM对象
                if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                    return '用户名不能有特殊字符';
                }
                if(/(^\_)|(\__)|(\_+$)/.test(value)){
                    return '用户名首尾不能出现下划线\'_\'';
                }
                if(/^\d+\d+\d$/.test(value)){
                    return '用户名不能全为数字';
                }
            }

            //我们既支持上述函数式的方式，也支持下述数组的形式
            //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
            ,pass: [
                /^[\S]{6,12}$/
                ,'密码必须6到12位，且不能出现空格'
            ]
            ,tel: [
                /^1[3456789]\d{9}$/
                ,'请输入正确的手机号'
            ]
            ,mail: [
                /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,})$/
                ,'邮箱格式不正确'
            ]
        });
    });

</script>
</body>
</html>
