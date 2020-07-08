<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>客户分享</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
</head>

<body>

<div class="layui-form-item">
    请选择共享的对象：
    <div class="layui-input-block">
        <select name="emploginname" id="emploginname" lay-verify="required" lay-filter="shareName">
            <option value="0"></option>
        </select>
    </div>
</div>

<button id="insertBtn" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>

<div id="test1" class="demo-transfer"></div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>客户共享</legend>
</fieldset>


<script src="${pageContext.request.contextPath}/layui/layui.js"></script>

<script>
    layui.use(['transfer', 'layer', 'util','form'], function() {
            var $ = layui.$
                , transfer = layui.transfer
                , layer = layui.layer
                , util = layui.util
                ,form = layui.form;


        //模拟数据
        var data1 = [
            {"value": "1", "title": "李白"}
            ,{"value": "2", "title": "杜甫"}
            ,{"value": "3", "title": "苏轼"}
            ,{"value": "4", "title": "李清照"}
            ,{"value": "5", "title": "鲁迅", "disabled": true}
            ,{"value": "6", "title": "巴金"}
            ,{"value": "7", "title": "冰心"}
            ,{"value": "8", "title": "矛盾"}
            ,{"value": "9", "title": "贤心"}
        ]


        /*==========页面初始化完需完成的操作==========*/
        $(function () {
            /*==========初始化select中的选项==========*/
            $.post("backCusExSelfSelect",{},function (data) {
                var emp = data.emp;
                var op;
                /*给添加框中的select赋值*/
                var emploginname = $("#emploginname")[0];
                for (var i = 0;i < emp.length;i++){
                    op = new Option(emp[i].empname,emp[i].empid);
                    emploginname.add(op);
                }
                form.render("select");
            },"json");

            /*获取被选中共享对象的名称*/
            var shareName = '';
            form.on('select(shareName)', function (data) {
                shareName = data.elem[data.elem.selectedIndex].text;
                form.render('select');
            });

            /*=====初始化穿梭框=====*/
            $.post("csSelect",{},function (data) {
                /*console.log(JSON.stringify(data));*/

                /*穿梭框*/
                transfer.render({
                    elem: '#test1'
                    , title: ['${loginName}的共列表', '被共享列表']  //自定义标题
                    , id: 'subData'
                    //,width: 150 //定义宽度
                    //, height: 210 //定义高度
                    , parseData: function(res){
                        return {
                            "value": res.cusid //数据值
                            ,"title": res.cusname //数据标题
                            ,"disabled": res.disabled  //是否禁用
                            ,"checked": res.checked //是否选中
                        }
                    }
                    , data: data
                    , onchange: function(obj, index){
                        var arr = ['左边', '右边'];
                        layer.alert('来自 <strong>'+ arr[index] + '</strong> 的数据：'+ JSON.stringify(obj)); //获得被穿梭时的数据
                    }

                });
            },"json");
        });

        $("#insertBtn").on("click",function () {
            console.log(JSON.stringify(transfer.getData('subData')).replace(/[\\]/g,''));
            /*layer.msg("cusid: "+transfer.getData('subData')+"   empid: "+$("#emploginname").val());*/

            $.ajax({
                url:"csInsert?empid="+$('#emploginname').val(),
                type: "post",
                data: JSON.stringify(transfer.getData('subData')).replace(/[\\]/g,''),
                contentType: 'application/json;charset=UTF-8',
                success:function (data) {
                    var i = data.num;
                    layer.msg(data.msg);
                }
            });
        })

    });

</script>
</body>
</html>
