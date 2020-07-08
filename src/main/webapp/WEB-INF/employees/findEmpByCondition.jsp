<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>查询合作专员</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>

<body>

<%--查询条件--%>
<div class="demoTable" style="margin-top: 10px;">
    用户姓名：
    <div class="layui-inline">
        <input class="layui-input" name="empname" id="demoReload" autocomplete="off">
    </div>
    <button class="layui-btn" data-type="reload">搜索</button>
</div>
<%--数据展示--%>
<table class="layui-hide" id="demo" lay-filter="test"></table>

<%--=====隐藏的改弹出层=====--%>
<form id="updateBox" class="layui-form layui-form-pane" style="display: none;margin-top: 20px;margin-left: 80px">
    <div class="layui-form-item">
        <label class="layui-form-label">用户登入名</label>
        <div class="layui-input-inline">
            <input type="text" name="emploginname" id="emploginname" required  lay-verify="required|username" placeholder="请输入用户登入名" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux" id="updatetip"></div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-inline">
            <input type="password" name="emppassword" id="emppassword" lay-verify="pass1" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-inline">
            <input type="text" name="emptel" id="emptel" required  lay-verify="required|tel" placeholder="请输入联系电话" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户姓名</label>
        <div class="layui-input-inline">
            <input type="text" name="empname" id="empname" required  lay-verify="required|username" placeholder="请输入用户姓名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-inline">
            <input type="text" name="empemail" id="empemail" required  lay-verify="required|mail" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">用户类型</label>
        <div class="layui-input-inline">
            <select name="roleid" id="roleid" lay-verify="required">
                <option value=""></option>
                <option name="middle" value="1">合作主管</option>
                <option name="low" value="2">合作专员</option>
                <option name="high" value="3">超级管理员</option>
            </select>
        </div>
    </div>

    </div>
    <div class="layui-form-item">
        <div class="layui-input-inline">
            <button class="layui-btn" lay-submit lay-filter="updateForm">立即提交</button>
            <button type="button" id="resetOld" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script src="${pageContext.request.contextPath}/layui/layui.js"></script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<script>
    layui.use(['table','element','form','jquery','layer'], function(){
        var table = layui.table,//表格
            element = layui.element,
            form = layui.form,
            $ = layui.jquery,
            layer = layui.layer;

        /*==========页面初始化完需完成的操作==========*/
        $(function () {
            /*==========初始化select中的选项==========*/
            $.post("ensureRole",{},function (data) {
                var i = data.num;
                if (1 == i){
                    $('option[name="middle"]').attr('disabled', 'disabled');
                    $('option[name="high"]').attr('disabled', 'disabled');
                    form.render('select');

                }else if (3 == i){
                    $('option[name="high"]').attr('disabled', 'disabled');
                    form.render('select');

                }
            },"json");

        });

        //执行一个 table 实例
        table.render({
            elem: '#demo'
            ,url: '${pageContext.request.contextPath}/backAllEmp' //数据接口
            ,title: '合作专员信息表'
            ,page: true //开启分页
            ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,limit: 5
            ,id: 'conditionTable'
            ,text: {none:'不存在用户'}
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'empid', title: 'ID', sort: true, fixed: 'left',align:'center'}
                ,{field: 'emploginname', title: '用户登入名',align:'center'}
                ,{field: 'emppassword', title: '密码',align:'center'}
                ,{field: 'emptel', title: '联系电话',align:'center'}
                ,{field: 'empname', title: '用户姓名', sort: true,align:'center'}
                ,{field: 'empemail', title: '邮箱',align:'center'}
                ,{field: 'emptype', title: '用户类型',align:'center'}
                ,{fixed: 'right', width: 210, align:'center', toolbar: '#barDemo'}
            ]]
        });

        //监听头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                ,data = checkStatus.data; //获取选中的数据
            switch(obj.event){
                case 'update':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else if(data.length > 1){
                        layer.msg('只能同时编辑一个');
                    } else {
                        /*layer.alert('编辑 [id]：'+ checkStatus.data[0].empid);*/
                        //iframe层
                        layer.open({
                            type: 1,
                            title: '修改'+checkStatus.data[0].emploginname+'的信息',
                            shadeClose: true,
                            shade: 0.8,
                            area: ['450px', '500px'],
                            content: $("#updateBox"),
                            success: function (layero, index) {
                                $("#emploginname").val(checkStatus.data[0].emploginname);
                                $("#emploginname").attr('readonly','readonly');
                                $("#emptel").val(checkStatus.data[0].emptel);
                                $("#empname").val(checkStatus.data[0].empname);
                                $("#empemail").val(checkStatus.data[0].empemail);

                                var select = 'dd[lay-value='+checkStatus.data[0].roleid+']';
                                $("#roleid").siblings("div.layui-form-select").find('dl').find(select).click();

                            },
                            cancel: function (layero, index) {
                                $("#emploginname").removeAttr('readonly');
                                $("#emploginname").val("");
                                $("#emppassword").val("");
                                $("#emptel").val("");
                                $("#empname").val("");
                                $("#empemail").val("");
                                $("#roleid").val("");
                            }
                        });
                    }
                    break;
                case 'delete':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else {
                        layer.msg('删除'+checkStatus.data[0]);

                    }
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                layer.msg('查看操作');
            } else if(layEvent === 'del'){

                $.post("ensureHasCus",{"empid":data.empid},function (msg) {
                    var i = msg.num;
                    if (1 == i){

                        layer.alert('请先转移专员下的客户',{icon: 6});

                    }else if (0 == i){

                        layer.confirm('真的删除行么', function(index){
                            obj.del(); //删除对应行（tr）的DOM结构
                            layer.close(index);
                            //向服务端发送删除指令
                            $.ajax({
                                url:"empDelete",
                                type:"post",
                                data: {'id': data.empid},
                                success:function(data){
                                    var i = data.num;
                                    layer.msg(data.msg);
                                    table.reload('conditionTable',{page:{curr:1}});
                                }
                            });
                        });

                    }
                },"json");

            } else if(layEvent === 'edit'){
                //iframe层
                layer.open({
                    type: 1,
                    title: '修改'+data.emploginname+'的信息',
                    shadeClose: true,
                    shade: 0.8,
                    area: ['450px', '500px'],
                    content: $("#updateBox"),
                    success: function (layero, index) {
                        $("#emploginname").val(data.emploginname);
                        $("#emploginname").attr('readonly','readonly');
                        $("#emptel").val(data.emptel);
                        $("#empname").val(data.empname);
                        $("#empemail").val(data.empemail);

                        var select = 'dd[lay-value='+data.roleid+']';
                        $("#roleid").siblings("div.layui-form-select").find('dl').find(select).click();
                    },
                    cancel: function (layero, index) {
                        $("#emploginname").removeAttr('readonly');
                        $("#emploginname").val("");
                        $("#emppassword").val("");
                        $("#emptel").val("");
                        $("#empname").val("");
                        $("#empemail").val("");
                        $("#roleid").val("");
                    }
                });
            }
        });

        //监听提交
        form.on('submit(insertForm)', function(data){
            $.ajax({
                url: "empInsert",
                type: "post",
                data: JSON.stringify(data.field),
                contentType: "application/json;charset=utf-8",
                success:function(data){
                    var i = data.num;

                    alert(data.msg);

                    if (1 == i){
                        layer.closeAll();
                        table.reload('conditionTable',{
                            page:{
                                curr:1
                            },
                            where:{}
                        });
                    }else if (0 == i){
                        $("#loginname").val("");
                        $("#password").val("");
                        $("#tel").val("");
                        $("#name").val("");
                        $("#email").val("");
                        $("#roleid").val("");
                    }
                }
            });
            /*return false;*/
        });

        form.on('submit(updateForm)', function(data){
            /*layer.msg(JSON.stringify(data.field));*/
            $.ajax({
                url: "empUpdate",
                type: "post",
                data: JSON.stringify(data.field),
                contentType: "application/json;charset=utf-8",
                async: false,
                success:function(data){
                    console.log(data.num);
                    console.log("提示信息："+data.msg);

                    var i = data.num;

                    alert(data.msg);

                    if (1 == i){
                        layer.closeAll();
                        table.reload('conditionTable',{
                            page:{
                                curr:1
                            },
                            where:{}
                        });
                        $("#emploginname").removeAttr("readonly");
                    }else if (0 == i){
                        /*待定*/
                    }
                }
            });
            /*return false;*/
        });

        $("#resetOld").on('click',function () {
            /*$("#emptel").val(data.emptel);
            $("#empname").val(data.empname);
            $("#empemail").val(data.empemail);*/
            /*待定*/
        })

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
            ,pass1: function (value, item) {
                if (!(value == "" && /^[\S]{6,12}$/.test(value))){
                    return '密码必须6到12位，且不能出现空格';
                }
            }
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

        /*搜索事件*/
        var active = {
            reload: function(){
                var demoReload = $('#demoReload');

                //执行重载
                table.reload('conditionTable', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {empname: demoReload.val()}
                    ,url: '${pageContext.request.contextPath}/backEmpByCondition'
                });
            }
        };

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
</script>

</body>
</html>
