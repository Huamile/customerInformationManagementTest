<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>客户分享</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>

<body>

<form class="layui-form layui-form-pane" action="#" style="margin-top: 20px">

    <div class="layui-form-item">
        <label class="layui-form-label">共享客户</label>
        <div class="layui-input-inline">
            <select name="cusid" id="cusid" lay-verify="required" lay-search>
                <option value=""></option>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">共享专员</label>
        <div class="layui-input-inline">
            <select name="empid" id="empid" lay-verify="required" lay-filter="insertselect" lay-search>
                <option value=""></option>
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-inline ">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script src="${pageContext.request.contextPath}/layui/layui.js"></script>

<script>

    layui.use(['form','jquery','element'], function(){
        var form = layui.form
            ,$ = layui.jquery
            ,element = layui.element;

        /*==========页面初始化完需完成的操作==========*/
        $(function () {
            /*==========初始化select中的选项==========*/
            $.post("csSelect",{},function (data) {
                var emp = data.emp;
                var cus = data.cus;
                var op;

                /*给专员框中的select赋值*/
                var empid = $("#empid")[0];
                for (var i = 0;i < emp.length;i++){
                    op = new Option(emp[i].empname,emp[i].empid);
                    empid.add(op);
                }

                /*给客户框中的select赋值*/
                var cusid = $("#cusid")[0];
                for (var i = 0;i < cus.length;i++){
                    op = new Option(cus[i].cusname,cus[i].cusid);
                    cusid.add(op);
                }

                form.render("select");
            },"json");


        });

        //监听提交
        form.on('submit(formDemo)', function(data){
            /*console.log(JSON.stringify(data.field));
            return false;
*/
            $.ajax({
                url:"csInsert",
                type: "post",
                data: JSON.stringify(data.field),
                contentType: 'application/json;charset=UTF-8',
                async: false,
                success:function (msg) {

                    console.log("提示信息：123"+msg.msg);

                    alert(msg.msg);
                }
            });


        });
    });
</script>
</body>
</html>
