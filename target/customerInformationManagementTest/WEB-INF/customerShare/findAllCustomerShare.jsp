<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>查看客户共享信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>

<body>


<%--数据展示--%>
<table class="layui-hide" id="demo" lay-filter="test"></table>

<%--=====隐藏的增弹出层=====--%>
<form id="insertBox" class="layui-form layui-form-pane" style="display: none;margin-top: 20px;">
    <div class="layui-form-item">
        <label class="layui-form-label">专员姓名</label>
        <div class="layui-input-inline">
            <select name="empid" id="ename" lay-verify="required" lay-filter="insertselect" lay-search>
                <option value=""></option>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">客户姓名</label>
        <div class="layui-input-inline">
            <select name="cusid" id="cname" lay-verify="required" lay-search>
                <option value=""></option>
            </select>
        </div>
    </div>

    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="insertForm">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<%--=====隐藏的改弹出层=====--%>
<form id="updateBox" class="layui-form layui-form-pane" style="display: none;margin-top: 20px;">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">拜访记录ID</label>
        <div class="layui-input-inline">
            <input type="text" name="sid" id="sid" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">专员姓名</label>
        <div class="layui-input-inline">
            <select name="empid" id="empname" lay-verify="required" lay-filter="updateselect" lay-search>
                <option value=""></option>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">客户姓名</label>
        <div class="layui-input-inline">
            <select name="cusid" id="cusname" lay-verify="required" lay-search>
                <option value=""></option>
            </select>
        </div>
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
            /*==========初始化select中的选项==========*/
            $.post("backESelect",{},function (data) {
                var emp = data.emp;
                var cus = data.cus;
                var op;

                /*给添加框中的select赋值*/
                var ename = $("#ename")[0];
                for (var i = 0;i < emp.length;i++){
                    op = new Option(emp[i].empname,emp[i].empid);
                    ename.add(op);
                }

                /*给修改框中的select赋值*/
                var empname = $("#empname")[0];
                for (var i = 0;i < emp.length;i++){
                    op = new Option(emp[i].empname,emp[i].empid);
                    empname.add(op);
                }

                /*给修改框中的select赋值*/
                var cusname = $("#cusname")[0];
                for (var i = 0;i < cus.length;i++){
                    op = new Option(cus[i].cusname,cus[i].cusid);
                    cusname.add(op);
                }

                form.render("select");
            },"json");
        });

        form.on('select(insertselect)',function (data) {
            var empid = data.elem.value;
            $("#cname").empty();
            $.ajax({
                url:"backCSelect",
                type:"post",
                data: {'empid': empid},
                success:function(data){
                    var cus = data.cus;
                    /*给添加框中的select赋值*/
                    var cname = $("#cname")[0];
                    for (var i = 0;i < cus.length;i++){
                        op = new Option(cus[i].cusname,cus[i].cusid);
                        cname.add(op);
                    }
                    form.render("select");
                }
            });
        });

        form.on('select(updateselect)',function (data) {
            var empid = data.elem.value;
            $("#cusname").empty();
            $.ajax({
                url:"backCSelect",
                type:"post",
                data: {'empid': empid},
                success:function(data){
                    var cus = data.cus;
                    /*给修改框中的select赋值*/
                    var cusname = $("#cusname")[0];
                    for (var i = 0;i < cus.length;i++){
                        op = new Option(cus[i].cusname,cus[i].cusid);
                        cusname.add(op);
                    }
                    form.render("select");
                }
            });
        });

        //执行一个 table 实例
        table.render({
            elem: '#demo'
            ,url: '${pageContext.request.contextPath}/backAllCs' //数据接口
            ,title: '客户共享记录表'
            ,page: true //开启分页
            ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,limit: 5
            ,id: 'conditionTable'
            ,text: {none:'不存在用户'}
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'sid', title: 'ID', sort: true, fixed: 'left',align:'center'}
                ,{field: 'cusname', title: '客户姓名',align:'center'}
                ,{field: 'empname', title: '合作专员姓名',align:'center'}
                ,{fixed: 'right', align:'center', toolbar: '#barDemo'}
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
                        title: '添加客户共享记录',
                        shadeClose: true,
                        shade: 0.8,
                        area: ['450px', '500px'],
                        content: $("#insertBox"),
                        cancel: function (layero, index) {
                            $("#cname").val("");
                            $("#ename").val("");
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
                            title: '修改客户共享记录',
                            shadeClose: true,
                            shade: 0.8,
                            area: ['450px', '500px'],
                            content: $("#updateBox"),
                            success: function (layero, index) {
                                $("#sid").val(checkStatus.data[0].sid);

                                var cusselect = 'dd[lay-value='+data.cusid+']';
                                $("#cusname").siblings("div.layui-form-select").find('dl').find(cusselect).click();

                                var empselect = 'dd[lay-value='+data.empid+']';
                                $("#empname").siblings("div.layui-form-select").find('dl').find(empselect).click();
                            },
                            cancel: function (layero, index) {
                                $("#sid").val("");
                                $("#cusname").val("");
                                $("#empname").val("");
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
                    url:"hasOtherShare",
                    type:"post",
                    data: {'id': data.cusid},
                    success:function(msg){
                        var i = msg.has;

                        if (1 == i){

                            layer.confirm('该共享记录中的客户由于没有其他共享记录,也将删除该客户,真的删除行么', function(index){
                                obj.del(); //删除对应行（tr）的DOM结构
                                layer.close(index);
                                //向服务端发送删除指令
                                $.ajax({
                                    url:"csDelete",
                                    type:"post",
                                    data: {'cusid': data.cusid,'sid':data.sid},
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

                        }else if (0 == i){

                            layer.confirm('真的删除行么', function(index){
                                obj.del(); //删除对应行（tr）的DOM结构
                                layer.close(index);
                                //向服务端发送删除指令
                                $.ajax({
                                    url:"csDelete",
                                    type:"post",
                                    data: {'cusid': data.cusid,'sid':data.sid},
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

                        }
                    }
                });

            } else if(layEvent === 'edit'){
                //iframe层
                layer.open({
                    type: 1,
                    title: '修改客户共享信息',
                    shadeClose: true,
                    shade: 0.8,
                    area: ['450px', '500px'],
                    content: $("#updateBox"),
                    success: function (layero, index) {
                        $("#sid").val(data.sid);

                        /*
                        * 拿到自己想要选择的内容所在的dd元素并对它触发点击事件，即可实现select加载时自动选择操作。
                        * */
                        /*首先需要使用lay-value来确定需要设置哪个元素自动选择*/
                        var cusselect = 'dd[lay-value='+data.cusid+']';
                        /*触发点击事件，实现自动选择*/
                        $("#cusname").siblings("div.layui-form-select").find('dl').find(cusselect).click();

                        var empselect = 'dd[lay-value='+data.empid+']';
                        $("#empname").siblings("div.layui-form-select").find('dl').find(empselect).click();

                    },
                    cancel: function (layero, index) {
                        $("#sid").val("");
                        $("#cusname").val("");
                        $("#empname").val("");
                    }
                });
            }
        });

        //监听提交
        form.on('submit(insertForm)', function(data){
            /*layer.msg(JSON.stringify(data.field));*/
            $.ajax({
                url: "csAdd",
                type: "post",
                data: JSON.stringify(data.field),
                contentType: "application/json;charset=utf-8",
                async: false,
                success:function(data){
                    console.log(data.num);
                    console.log("提示信息："+data.msg);

                    var i = data.num;
                    if (1 == i){
                        layer.closeAll();
                        table.reload('conditionTable',{
                            page:{
                                curr:1
                            },
                            where:{}
                        });
                    }else if (0 == i){

                    }
                    alert(data.msg);
                }
            });
            /*return false;*/
        });

        form.on('submit(updateForm)', function(data){
            /*layer.msg(JSON.stringify(data.field));
            return false;*/

            $.ajax({
                url: "csModify",
                type: "post",
                data: JSON.stringify(data.field),
                contentType: "application/json;charset=utf-8",
                async: false,
                success:function(data){
                    console.log(data.num);
                    console.log("提示信息："+data.msg);

                    var i = data.num;
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
                    }else if (-1 == i){
                        /*待定*/
                    }
                    alert(data.msg);
                }
            });
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
