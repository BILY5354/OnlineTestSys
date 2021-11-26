<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>黑马面面管理系统</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->

</head>
<script>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="${ctx}/store/question?operation=delete&id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
<section class="content-header">
    <h1>
        题库管理
        <small>题目管理</small>
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
            <h3 class="box-title">题目列表</h3>
        </div>

        <div class="box-body">

            <!-- 数据表格 -->
            <div class="table-box">

                <!--工具栏-->
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="新建" onclick='location.href="${ctx}/store/question?operation=toAdd"'><i class="fa fa-file-o"></i> 新建</button>
                            <button type="button" class="btn btn-default" title="测试文件上传" onclick='location.href="${ctx}/store/question?operation=toTestUpload"'><i class="fa fa-file-o"></i> 测试文件上传</button>
                            <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
                            <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
<%--
                            <button type="button" class="btn btn-default" title="上传题目" onclick='location.href="${ctx}/store/question?operation=toImport"'><i class="fa fa-adn"></i> 上传题目</button>
--%>
                            <button type="button" class="btn btn-default" title="导出题目" onclick=location.href="${ctx}/store/question?operation=toExport"> <i class="fa fa-download"></i>导出题目</button>
                        </div>
                    </div>
                </div>
                <div class="box-tools pull-right">
                    <div class="has-feedback">
                        <input type="text" class="form-control input-sm" placeholder="搜索">
                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    </div>
                </div>
                <!--工具栏/-->

                <!--数据列表-->
                <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                    <thead>
                    <tr>
                        <th class="" style="padding-right:0px;">
                            <input type="checkbox" name="selid" onclick="checkAll('id',this)">
                        </th>
                        <th class="sorting">企业</th>
                        <th class="sorting">类别</th>
                        <th class="sorting">题目</th>
                        <th class="sorting">类型</th>
                        <th class="sorting">难度</th>
                        <th class="sorting">经典面试题</th>
                        <th class="sorting">状态</th>
                        <th class="sorting">审核结果</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="o" varStatus="status">
                    <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                        <td><input type="checkbox" name="id" value="${o.id}"/></td>
                        <td>${o.company.name}</td>
                        <td>${o.catalog.name}</td>
                        <td>${o.subject}</td>
                        <td>
                            <c:choose>
                                <c:when test="${o.type eq '1'}">单选</c:when>
                                <c:when test="${o.type eq '2'}">多选</c:when>
                                <c:when test="${o.type eq '3'}">简答</c:when>
                            </c:choose>
                        </td>
                        <td>
                            <c:forEach begin="1" end="${o.difficulty}">
                                ★
                            </c:forEach>
                        </td>
                        <td>${o.isClassic eq "1" ? "经典题":"普通题"}</td>
                        <td>${o.state eq "1" ? "<font color='green'>启用</font>" : "<font color='red'>禁用</font>"}</td>
                        <td>
                            <c:choose>
                                <c:when test="${o.reviewStatus eq '1'}"><font color="green">审核通过</font></c:when>
                                <c:when test="${o.reviewStatus eq '0'}">审核中</c:when>
                                <c:when test="${o.reviewStatus eq '-1'}"><font color="red">审核不通过</font></c:when>
                            </c:choose>
                        </td>
                        <th class="text-center">
                            <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/store/question?operation=toEdit&id=${o.id}"'>编辑</button>
                            <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/store/question?operation=toExamine&id=${o.id}"'>审核</button>
                        </th>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="box-footer">
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="${ctx}/store/question?operation=list" name="pageUrl"/>
            </jsp:include>
        </div>
    </div>

</section>
</div>
</body>

</html>