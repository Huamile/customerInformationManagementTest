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

<table class="layui-hide" id="demo" lay-filter="test"></table>

<script src="${pageContext.request.contextPath}/layui/layui.js"></script>

<script>
    layui.use(['table','form','jquery','layer',"laydate","util"], function(){
        var table = layui.table,//表格
            form = layui.form,
            $ = layui.jquery,
            layer = layui.layer,
            laydate = layui.laydate,
            util = layui.util;

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

                form.render("select");
            },"json");

        });

        //日期时间范围
        laydate.render({
            elem: '#visitDate'
            ,type: 'datetime'
            ,range: true
            ,calendar: true
        });

        //执行一个 table 实例
        table.render({
            elem: '#demo'
            ,url: '${pageContext.request.contextPath}/backAllCv' //数据接口
            ,title: '客户拜访记录表'
            ,page: true //开启分页
            ,toolbar: true //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,limit: 5
            ,id: 'conditionTable'
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'vid', title: 'ID', sort: true, fixed: 'left'}
                ,{field: 'cusname', title: '客户姓名', sort: true}
                ,{field: 'empname', title: '合作专员姓名', sort: true}
                ,{field: 'content', title: '拜访内容'}
                ,{field: 'date', title: '日期', sort: true}
            ]]
        });

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
                    ,url: '${pageContext.request.contextPath}/backCvByCondition'
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
