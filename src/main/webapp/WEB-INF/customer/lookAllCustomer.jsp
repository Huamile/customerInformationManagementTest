<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>查找所有客户</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>

<body>

<%--查询条件--%>
<div class="demoTable" style="margin-top: 10px">
    搜索  客户姓名：
    <div class="layui-inline">
        <input class="layui-input" name="cusname" id="cusnameReload" autocomplete="off">
    </div>
    联系人：
    <div class="layui-input-inline layui-form">
        <select name="contact" id="contactReload" lay-search>
            <option value=""></option>
        </select>
    </div>
    <button class="layui-btn" data-type="reload">搜索</button>
</div>

<table class="layui-hide" id="demo" lay-filter="test"></table>

<script src="${pageContext.request.contextPath}/layui/layui.js"></script>

<script>
    layui.use(['table','jquery','form'], function(){
        var table = layui.table,//表格
            $ = layui.jquery
            ,form = layui.form;

        /*==========页面初始化完需完成的操作==========*/
        $(function () {
            /*==========初始化select中的选项==========*/
            $.post("backSelect",{},function (data) {
                var emp = data.emp;
                var op;
                var contactReload = $("#contactReload")[0];
                for (var i = 0;i < emp.length;i++){
                    op = new Option(emp[i].empname,emp[i].empid);
                    contactReload.add(op);
                }
                form.render('select');
            },"json");
        });

        //执行一个 table 实例
        table.render({
            elem: '#demo'
            ,url: '${pageContext.request.contextPath}/backAllCus' //数据接口
            ,title: '客户信息表'
            ,page: true //开启分页
            ,toolbar: true //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,limit: 5
            ,id: 'conditionTable'
            ,text: {none:'不存在用户'}
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'cusid', title: 'ID', sort: true, fixed: 'left',align:'center'}
                ,{field: 'cusname', title: '客户姓名', sort: true,align:'center'}
                ,{field: 'cusaddress', title: '地址', sort: true,align:'center'}
                ,{field: 'contact', title: '联系人', sort: true,align:'center'}
                ,{field: 'custel', title: '联系电话', sort: true,align:'center'}
                ,{field: 'cusemail', title: '邮箱', sort: true,align:'center'}
            ]]
        });

        /*搜索事件*/
        var active = {
            reload: function(){
                var cusnameReload = $('#cusnameReload');
                var contactReload = $('#contactReload');

                //执行重载
                table.reload('conditionTable', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        cusname: cusnameReload.val(),
                        contact: contactReload.val()
                    }
                    ,url: '${pageContext.request.contextPath}/backCusByCondition'
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