<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>查询客户拜访记录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>

<body>

<%--查询条件--%>
<div class="demoTable" style="margin-top: 10px;">
    客户姓名：
    <div class="layui-input-inline layui-form">
        <select name="cusname" id="cusnameReload" lay-search><option value=""></option></select>
    </div>
    日期时间范围
    <div class="layui-input-inline layui-form">
        <input type="text" class="layui-input" name="date" id="visitDate" placeholder=" - ">
    </div>
    <button class="layui-btn" data-type="reload">搜索</button>
</div>

<%--数据展示--%>
<table class="layui-hide" id="demo" lay-filter="test"></table>

<%--=====隐藏的改弹出层=====--%>
<form id="updateBox" class="layui-form layui-form-pane" style="display: none;margin-top: 20px;margin-left: 80px">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">拜访记录ID</label>
        <div class="layui-input-inline">
            <input type="text" name="vid" id="vid" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">客户姓名</label>
        <div class="layui-input-inline">
            <select name="cusname" id="cusname" lay-verify="required" lay-search>
                <option value=""></option>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">专员姓名</label>
        <div class="layui-input-inline">
            <select name="empname" id="empname" lay-verify="required" lay-search></select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">时间</label>
        <div class="layui-input-inline">
            <input type="text" name="date" id="date" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">内容</label>
        <div class="layui-input-block">
            <textarea name="content" id="content" placeholder="请输入内容" class="layui-textarea"></textarea>
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
    layui.use(['table','form','jquery','layer',"laydate","util"], function(){
        var table = layui.table,//表格
            form = layui.form,
            $ = layui.jquery,
            layer = layui.layer,
            laydate = layui.laydate,
            util = layui.util;

        //执行一个laydate实例
        laydate.render({
            elem: '#d' //指定元素
            ,type: 'datetime'
            ,calendar: true
            ,min: -7
            ,max: 7
        });

        //日期时间范围
        laydate.render({
            elem: '#visitDate'
            ,type: 'datetime'
            ,range: true
            ,calendar: true
        });

        /*==========页面初始化完需完成的操作==========*/
        $(function () {
            /*==========初始化select中的选项==========*/
            $.post("backCusSelect",{},function (data) {
                var cus = data.cus;
                var op;
                /*给搜索框中的select赋值*/
                var cusnameReload = $("#cusnameReload")[0];
                for (var i = 0;i < cus.length;i++){
                    op = new Option(cus[i].cusname,cus[i].cusid);
                    cusnameReload.add(op);
                }
                /*给修改框中的select赋值*/
                var cusname = $("#cusname")[0];
                for (var i = 0;i < cus.length;i++){
                    op = new Option(cus[i].cusname,cus[i].cusid);
                    cusname.add(op);
                }
                form.render("select");
            },"json");

            /*=====专员姓名下拉框=====*/
            $.post("ensureRole",{},function (data) {
                var i = data.num;
                if (2 == i){
                    $.post("backEmp",{},function (data) {
                        /*给改弹出框的select赋值*/
                        var empname = $("#empname")[0];
                        var op = new Option(data.empname,data.empid);
                        empname.add(op);
                        form.render('select');
                    })
                }else if (3 == i){
                    /*==========初始化select中的选项==========*/
                    $.post("backSelect",{},function (data) {
                        var emp = data.emp;
                        var op;
                        /*改弹出框*/
                        var empname = $("#empname")[0];
                        for (var i = 0;i < emp.length;i++){
                            op = new Option(emp[i].empname,emp[i].empid);
                            empname.add(op);
                        }
                        form.render('select');
                    },"json");
                }
            },"json");
        });

        //执行一个 table 实例
        table.render({
            elem: '#demo'
            ,url: '${pageContext.request.contextPath}/backOwnCv' //数据接口
            ,title: '客户拜访记录表'
            ,page: true //开启分页
            ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,limit: 5
            ,id: 'conditionTable'
            ,text: {none:'不存在拜访记录'}
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'vid', title: 'ID', sort: true, fixed: 'left',align:'center'}
                ,{field: 'cusname', title: '客户姓名',align:'center'}
                ,{field: 'empname', title: '合作专员姓名',align:'center'}
                ,{field: 'content', title: '内容',align:'center'}
                ,{field: 'date', title: '时间', sort: true,align:'center',templet: function(d) {return util.toDateString(d.date); }}
                ,{fixed: 'right', align:'center', toolbar: '#barDemo'}
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
                            title: '修改'+checkStatus.data[0].cusname+'的信息',
                            shadeClose: true,
                            shade: 0.8,
                            area: ['450px', '500px'],
                            content: $("#updateBox"),
                            success: function (layero, index) {
                                $("#vid").val(checkStatus.data[0].vid);
                                $("#content").val(checkStatus.data[0].content);

                                var cusselect = 'dd[lay-value='+checkStatus.data[0].cusid+']';
                                $("#cusname").siblings("div.layui-form-select").find('dl').find(cusselect).click();

                                $.post("ensureRole",{},function (msg) {
                                    var i = msg.num;
                                    if (3 == i) {
                                        var empselect = 'dd[lay-value='+checkStatus.data[0].empid+']';
                                        $("#empname").siblings("div.layui-form-select").find('dl').find(empselect).click();
                                    }
                                });

                                laydate.render({
                                    elem: '#date' //指定元素
                                    ,type: 'datetime'
                                    ,calendar: true
                                    ,min: -7
                                    ,max: 7
                                    ,value: new Date(checkStatus.data[0].date)
                                });

                            },
                            cancel: function (layero, index) {
                                $("#vid").val("");
                                $("#content").val("");
                                $("#date").val("");
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
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                    $.ajax({
                        url:"cvDelete",
                        type:"post",
                        data: {'id': data.cusid},
                        success:function(data){
                            var i = data.num;

                            layer.msg(data.msg);

                            if (1 == i){
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
                        $("#vid").val(data.vid);
                        $("#content").val(data.content);

                        var cusselect = 'dd[lay-value='+data.cusid+']';
                        $("#cusname").siblings("div.layui-form-select").find('dl').find(cusselect).click();

                        $.post("ensureRole",{},function (msg) {
                            var i = msg.num;
                            if (3 == i) {
                                var empselect = 'dd[lay-value='+data.empid+']';
                                $("#empname").siblings("div.layui-form-select").find('dl').find(empselect).click();
                            }
                        });

                        laydate.render({
                            elem: '#date' //指定元素
                            ,type: 'datetime'
                            ,calendar: true
                            ,min: -7
                            ,max: 7
                            ,value: new Date(data.date)
                        });

                    },
                    cancel: function (layero, index) {
                        $("#vid").val("");
                        $("#content").val("");
                        $("#date").val("");
                    }
                });
            }
        });

        //监听提交
        form.on('submit(updateForm)', function(data){
            /*layer.msg(JSON.stringify(data.field));*/
            $.ajax({
                url: "cvUpdate",
                type: "post",
                data: JSON.stringify(data.field),
                contentType: "application/json;charset=utf-8",
                async: false,
                success:function(data){
                    console.log(data.num);
                    console.log("提示信息："+data.msg);

                    var i = data.num

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

        $("#resetOld").on('click',function () {
            /*重置修改的数据*/
        })

        /*搜索事件*/
        var active = {
            reload: function(){
                var cusnameReload = $('#cusnameReload');
                var visitDate = $("#visitDate");

                //执行重载
                table.reload('conditionTable', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        cusname: cusnameReload.val(),
                        date: visitDate.val()
                    }
                    ,url: '${pageContext.request.contextPath}/backOwnCvByCondition'
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
