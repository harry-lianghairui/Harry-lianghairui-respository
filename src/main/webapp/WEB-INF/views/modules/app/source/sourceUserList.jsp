<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户来源统计管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/app/source/sourceUser/">用户来源统计列表</a></li>
<%-- 		<shiro:hasPermission name="app:source:sourceUser:edit"><li><a href="${ctx}/app/source/sourceUser/form">用户来源统计添加</a></li></shiro:hasPermission>
 --%>	</ul>
	<form:form id="searchForm" modelAttribute="sourceUser" action="${ctx}/app/source/sourceUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>游戏id：</label>
				<form:input path="sourceId" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>用户id：</label>
				<form:input path="userId" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>操作类型：</label>
				<form:select path="type" class="input-medium" style=" width:150px">
					<form:option value="" label="全部"/>
					<form:options items="${fns:getDictList('source_user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>id</th>
				<th>游戏id</th>
				<th>用户id</th>
				<th>操作类型</th>
				<th>操作次数</th>
				<%-- <shiro:hasPermission name="app:source:sourceUser:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sourceUser">
			<tr>
				<td>
					${sourceUser.id}
				</td>
				<td>
					${sourceUser.sourceId}
				</td>
				<td>
					${sourceUser.userId}
				</td>
				<td>
					${fns:getDictLabel(sourceUser.type, 'source_user_type', '')}
				</td>
				<td>
					${sourceUser.count}
				</td>
<%-- 				<shiro:hasPermission name="app:source:sourceUser:edit"><td>
    				<a href="${ctx}/app/source/sourceUser/form?id=${sourceUser.id}">修改</a>
					<a href="${ctx}/app/source/sourceUser/delete?id=${sourceUser.id}" onclick="return confirmx('确认要删除该用户来源统计吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>