<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>查看客户信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>

<body>

<%--数据展示--%>
<table class="layui-hide" id="demo" lay-filter="test"></table>

<%--=====隐藏的增弹出层=====--%>
<form id="insertBox" class="layui-form layui-form-pane" style="display: none;margin-top: 20px;margin-left: 80px">
    <div class="layui-form-item">
        <label class="layui-form-label">用户姓名</label>
        <div class="layui-input-inline">
            <input type="text" name="cusname" id="name" required lay-verify="required|username" placeholder="请输入用户姓名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">地址</label>
        <div class="layui-input-inline">
            <input type="text" name="cusaddress" id="address" required  lay-verify="required" placeholder="请输入地址" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">联系人</label>
        <div class="layui-input-inline">
            <select name="contact" id="ctt" lay-verify="required">
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-inline">
            <input type="text" name="custel" id="tel" required  lay-verify="required|tel" placeholder="请输入联系电话" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-inline">
            <input type="text" name="cusemail" id="email" required  lay-verify="required|mail" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
        </div>
    </div>

    </div>
    <div class="layui-form-item">
        <div class="layui-input-inline">
            <button class="layui-btn" lay-submit lay-filter="insertForm">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<%--=====隐藏的改弹出层=====--%>
<form id="updateBox" class="layui-form layui-form-pane" style="display: none;margin-top: 20px;margin-left: 80px">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">用户ID</label>
        <div class="layui-input-inline">
            <input type="text" name="cusid" id="cusid" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">用户姓名</label>
        <div class="layui-input-inline">
            <input type="text" name="cusname" id="cusname" required lay-verify="required|username" placeholder="请输入用户姓名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">地址</label>
        <div class="layui-input-inline">
            <input type="text" name="cusaddress" id="cusaddress" required  lay-verify="required" placeholder="请输入地址" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">联系人</label>
        <div class="layui-input-inline">
            <select name="contact" id="contact" lay-verify="required"></select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">联系电话</label>
        <div class="layui-input-inline">
            <input type="text" name="custel" id="custel" required  lay-verify="required|tel" placeholder="请输入联系电话" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-inline">
            <input type="text" name="cusemail" id="cusemail" required  lay-verify="required|mail" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
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
    layui.use(['table','form','jquery','layer'], function(){
        var table = layui.table,//表格
            form = layui.form,
            $ = layui.jquery,
            layer = layui.layer;

        /*==========页面初始化完需完成的操作==========*/
        $(function () {
            $.post("ensureRole",{},function (data) {
                var i = data.num;
                if (2 == i){
                    $.post("backEmp",{},function (data) {
                        var op;
                        /*给增弹出框的select赋值*/
                        var ctt = $("#ctt")[0];
                        op = new Option(data.empname,data.empid);
                        ctt.add(op);
                        /*给改弹出框的select赋值*/
                        var contact = $("#contact")[0];
                        op = new Option(data.empname,data.empid);
                        contact.add(op);
                        form.render('select');
                    });
                }else if (3 == i){
                    /*==========初始化select中的选项==========*/
                    $.post("backSelect",{},function (data) {
                        var emp = data.emp;
                        var op;
                        /*增弹出框*/
                        var ctt = $("#ctt")[0];
                        for (var i = 0;i < emp.length;i++){
                            op = new Option(emp[i].empname,emp[i].empid);
                            ctt.add(op);
                        }
                        /*改弹出框*/
                        var contact = $("#contact")[0];
                        for (var i = 0;i < emp.length;i++){
                            op = new Option(emp[i].empname,emp[i].empid);
                            contact.add(op);
                        }
                        form.render('select');
                    },"json");
                }
            },"json");
        });


        //执行一个 table 实例
        table.render({
            elem: '#demo'
            ,url: '${pageContext.request.contextPath}/backOwnCus' //数据接口
            ,title: '客户信息表'
            ,page: true //开启分页
            ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,limit: 5
            ,id: 'conditionTable'
            ,text: {none:'不存在用户'}
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'cusid', title: 'ID', sort: true, fixed: 'left',align:'center'}
                ,{field: 'cusname', title: '客户姓名',align:'center'}
                ,{field: 'cusaddress', title: '地址',align:'center'}
                ,{field: 'contact', title: '联系人',align:'center'}
                ,{field: 'custel', title: '联系电话', sort: true,align:'center'}
                ,{field: 'cusemail', title: '邮箱',align:'center'}
                ,{fixed: 'right', width: 210, align:'center', toolbar: '#barDemo'}
            ]]
        });

        //监听头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                ,data = checkStatus.data; //获取选中的数据
            switch(obj.event){
                case 'add':
                    //iframe层
                    layer.open({
                        type: 1,
                        title: '添加客户',
                        shadeClose: true,
                        shade: 0.8,
                        area: ['450px', '500px'],
                        content: $("#insertBox"),
                        cancel: function (layero, index) {
                            $("#name").val("");
                            $("#address").val("");
                            $("#tel").val("");
                            $("#email").val("");
                        }
                    });
                    break;
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
                            title: '修改'+checkStatus.data[0].cusname+'的信息',
                            shadeClose: true,
                            shade: 0.8,
                            area: ['450px', '500px'],
                            content: $("#updateBox"),
                            success: function (layero, index) {
                                $("#cusid").val(checkStatus.data[0].cusid);
                                $("#cusname").val(checkStatus.data[0].cusname);
                                $("#cusaddress").val(checkStatus.data[0].cusaddress);
                                $("#custel").val(checkStatus.data[0].custel);
                                $("#cusemail").val(checkStatus.data[0].cusemail);

                                $.post("ensureRole",{},function (msg) {
                                    var i = msg.num;
                                    if (3 == i) {
                                        $.post("backContact",{"contact":checkStatus.data[0].contact},function (data) {
                                            var select = 'dd[lay-value='+data.empid+']';
                                            $("#contact").siblings("div.layui-form-select").find('dl').find(select).click();
                                        },"json");
                                    }
                                });

                            },
                            cancel: function (layero, index) {
                                $("#cusid").val("");
                                $("#cusname").val("");
                                $("#cusaddress").val("");
                                $("#custel").val("");
                                $("#cusemail").val("");
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
                $.ajax({
                    url:"hasVisit",
                    type:"post",
                    data: {'id': data.cusid},
                    success:function(msg){
                        var i = msg.has;
                        if (1 == i){

                            layer.confirm('存在该客户的拜访记录，真的删除行么？', function(index){

                                layer.close(index);
                                //向服务端发送删除指令
                                $.ajax({
                                    url:"cusDelete",
                                    type:"post",
                                    data: {'id': data.cusid},
                                    success:function(data){
                                        var i = data.num;

                                        layer.msg(data.msg);

                                        if (1 == i){
                                            obj.del(); //删除对应行（tr）的DOM结构
                                            table.reload('conditionTable',{
                                                page:{
                                                    curr:1
                                                },
                                                where:{}
                                            });
                                        }
                                    }
                                });
                            });

                        }else if(0 == i){

                            layer.confirm('真的删除行么？', function(index){

                                layer.close(index);
                                //向服务端发送删除指令
                                $.ajax({
                                    url:"cusDelete",
                                    type:"post",
                                    data: {'id': data.cusid},
                                    success:function(data){
                                        var i = data.num;

                                        layer.msg(data.msg);

                                        if (1 == i){
                                            obj.del(); //删除对应行（tr）的DOM结构
                                            table.reload('conditionTable',{
                                                page:{
                                                    curr:1
                                                },
                                                where:{}
                                            });
                                        }
                                    }
                                });
                            });

                        }
                    }
                });

            } else if(layEvent === 'edit'){
                //iframe层
                layer.open({
                    type: 1,
                    title: '修改'+data.cusname+'的信息',
                    shadeClose: true,
                    shade: 0.8,
                    area: ['450px', '500px'],
                    content: $("#updateBox"),
                    success: function (layero, index) {
                        $("#cusid").val(data.cusid);
                        $("#cusname").val(data.cusname);
                        $("#cusaddress").val(data.cusaddress);
                        $("#custel").val(data.custel);
                        $("#cusemail").val(data.cusemail);

                        $.post("ensureRole",{},function (msg) {
                            var i = msg.num;
                            if (3 == i) {
                                $.post("backContact",{"contact":data.contact},function (data) {
                                    var select = 'dd[lay-value='+data.empid+']';
                                    $("#contact").siblings("div.layui-form-select").find('dl').find(select).click();
                                },"json");
                            }
                        });

                    },
                    cancel: function (layero, index) {
                        $("#cusid").val("");
                        $("#cusname").val("");
                        $("#cusaddress").val("");
                        $("#custel").val("");
                        $("#cusemail").val("");
                    }
                });
            }
        });

        //监听提交
        form.on('submit(insertForm)', function(data){
            $.ajax({
                url: "cusInsert",
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
                    }else if (0 == i){
                        $("#name").val("");
                        $("#address").val("");
                        $("#tel").val("");
                        $("#email").val("");
                    }
                }
            });
            /*return false;*/
        });

        form.on('submit(updateForm)', function(data){
            /*layer.msg(JSON.stringify(data.field));*/
            $.ajax({
                url: "cusUpdate",
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
                    }else if (0 == i){
                        /*待定*/
                    }
                }
            });
            /*return false;*/
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


        $("#resetOld").on('click',function () {
            /*$("#emptel").val(data.emptel);
            $("#empname").val(data.empname);
            $("#empemail").val(data.empemail);*/
            /*待定*/
        })

    });

</script>



</body>
</html>
