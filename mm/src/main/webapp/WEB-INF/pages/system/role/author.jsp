<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <base href="${ctx}/">
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
    <link rel="stylesheet" href="${ctx}/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${ctx}/plugins/ztree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${ctx}/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="${ctx}/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>

    <SCRIPT type="text/javascript">
        var zTreeObj;
        var setting = {check: {enable: true},data: {simpleData: {enable: true}}};

        var zNodes = [
            {id:1,pId:0,name:'平台系统管理',checked:false},
            {id:101,pId:1,name:'企业管理',checked:false},
            {id:102,pId:1,name:'部门管理',checked:false},
            {id:103,pId:1,name:'用户管理',checked:false},
            {id:104,pId:1,name:'角色管理',checked:false},
            {id:105,pId:1,name:'模块管理',checked:false},
            {id:106,pId:1,name:'系统日志管理',checked:false},
            {id:2,pId:0,name:'题库管理',checked:false},
            {id:201,pId:2,name:'题目学科管理',checked:false},
            {id:202,pId:2,name:'题目类型管理',checked:false},
            {id:203,pId:2,name:'题目管理',checked:false},
            {id:204,pId:2,name:'题目审核日志',checked:false},
            {id:3,pId:0,name:'会员管理',checked:false},
            {id:301,pId:3,name:'会员账号管理',checked:false},
            {id:302,pId:3,name:'会员答题管理',checked:false}
        ]

        $(document).ready(function(){
            /*
            $.get("${ctx}/system/role?operation=getModuleByRoleId&id=${role.id}",function(data) {
                var json = eval('(' + data + ')');
                initZtree(json);
            });
            */
            zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes)
            var zTree = $.fn.zTree.getZTreeObj("treeDemo")
            zTree.setting.check.chkboxType = { "Y" : "ps", "N" : "ps" }
            zTreeObj.expandAll(true);//true：展开所有
        });

        //实现权限分配
        function submitCheckedNodes() {
            //1.获取所有的勾选权限节点
            var nodes = zTreeObj.getCheckedNodes(true);//true:被勾选，false：未被勾选
            //2.循环nodes，获取每个节点的id，并将数据加入数组
            var moduleArrays = [];
            for(var i=0;i<nodes.length;i++) {
                moduleArrays.push(nodes[i].id);
            }
            //3.将数组中的数据使用,连接后，赋值给表单，传入后台
            $("#moduleIds").val(moduleArrays.join(','));
            $("#icform").submit();
        }

</SCRIPT>


</head>

<body style="overflow: visible;">
<div id="frameContent" class="content-wrapper" style="margin-left:0px;height: 1200px" >
    <section class="content-header">
        <h1>
            菜单管理
            <small>菜单列表</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">角色 [${role.name}] 权限列表</h3>
            </div>
            <div class="box-body">
                <!-- 数据表格 -->
                <div class="table-box">
                    <!-- 树菜单 /-->
                    <form name="icform" id="icform" method="post" action="${ctx}/system/role?operation=updateRoleModule">
                        <input type="hidden" name="roleId" value="${role.id}"/>
                        <input type="hidden" id="moduleIds" name="moduleIds" value=""/>
                        <ul id="treeDemo" class="ztree"></ul>
                    </form>
                    <!--工具栏-->
                    <div class="box-tools text-left">
                    <button type="button" class="btn bg-maroon" onclick="submitCheckedNodes();">保存</button>
                    <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>