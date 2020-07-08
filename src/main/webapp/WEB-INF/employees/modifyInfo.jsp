<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>修改信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>
<body>

<form id="updateBox" class="layui-form layui-form-pane" style="margin-top: 20px;">

    <div class="layui-form-item">
        <label class="layui-form-label">我的角色</label>
        <div class="layui-input-inline">
            <select name="roleid" id="roleid" lay-verify="required">
                <option name="middle" value="1">合作主管</option>
                <option name="low" value="2">合作专员</option>
                <option name="high" value="3">超级管理员</option>
            </select>
        </div>
        <div class="layui-form-mid layui-word-aux">不可更改为其它角色</div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-inline">
            <input type="text" name="emploginname" id="emploginname" required  lay-verify="required" placeholder="请输入用户登入名" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">不可修改。一般用于后台登入名</div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-inline">
            <input type="text" name="empname" id="empname" required  lay-verify="required|username" placeholder="请输入用户姓名" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">手机</label>
        <div class="layui-input-inline">
            <input type="text" name="emptel" id="emptel" required  lay-verify="required|tel" placeholder="请输入联系电话" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-inline">
            <input type="text" name="empemail" id="empemail" required  lay-verify="required|mail" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="updateForm">立即提交</button>
            <button type="button" id="resetOld" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script src="${pageContext.request.contextPath}/layui/layui.js"></script>

<script>

    layui.use(['element','form','jquery','layer'], function() {
        var element = layui.element,
            form = layui.form,
            $ = layui.jquery,
            layer = layui.layer;

        /*==========页面初始化完需完成的操作==========*/
        $(function () {
            /*==========初始化select中的选项==========*/
            $.post("ensureRole", {}, function (data) {
                var i = data.num;
                if (1 == i) {

                    $('option[name="high"]').attr('disabled', 'disabled');
                    $('option[name="low"]').attr('disabled', 'disabled');
                    form.render('select');

                } else if (2 == i) {

                    $('option[name="high"]').attr('disabled', 'disabled');
                    $('option[name="middle"]').attr('disabled', 'disabled');
                    form.render('select');

                } else if (3 == i){

                    $('option[name="low"]').attr('disabled', 'disabled');
                    $('option[name="middle"]').attr('disabled', 'disabled');
                    form.render('select');
                }
            }, "json");

            $.post("backSelf",{},function (data) {
                $("#emploginname").val(data.emploginname);
                $("#emploginname").attr('readonly','readonly');
                $("#emptel").val(data.emptel);
                $("#empname").val(data.empname);
                $("#empemail").val(data.empemail);

                var select = 'dd[lay-value='+data.roleid+']';
                $("#roleid").siblings("div.layui-form-select").find('dl').find(select).click();
            },"json");

        });


        form.on('submit(updateForm)', function(data){
            /*layer.msg(JSON.stringify(data.field));
            return false;*/

            $.ajax({
                url: "empUpdate",
                type: "post",
                data: JSON.stringify(data.field),
                contentType: "application/json;charset=utf-8",
                success:function(data){
                    var i = data.num;
                    alert(data.msg);
                    if (1 == i){
                        window.location.reload();
                    }else if (0 == i){
                        /*待定*/
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
