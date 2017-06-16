<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>游戏管理</title>
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
		<li class="active"><a href="${ctx}/app/source/sourceConfig/">游戏列表</a></li>
		<shiro:hasPermission name="app:source:sourceConfig:edit"><li><a href="${ctx}/app/source/sourceConfig/form">游戏添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sourceConfig" action="${ctx}/app/source/sourceConfig/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>游戏id：</label>
				<form:input path="resourceId" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>游戏名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="30" class="input-medium"/>
			</li>
			<li><label>包版本号：</label>
				<form:input path="version" htmlEscape="false" maxlength="20" class="input-medium"/>
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
				<th>游戏名称</th>
				<th>包名称</th>
				<th>包版本</th>
				<th>包大小(M)</th>
				<th>包地址</th>
				<th>背景图</th>
				<th>状态</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>创建人</th>
				<th>更新人</th>
				<shiro:hasPermission name="app:source:sourceConfig:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sourceConfig">
			<tr>
				<td>
					${sourceConfig.id}
				</td>
				<td>
					${sourceConfig.resourceId}
				</td>
				<td>
					${sourceConfig.name}
				</td>
				<td>
					${sourceConfig.pName}
				</td>
				<td>
					${sourceConfig.version}
				</td>
				<td>
					${sourceConfig.fileSize}
				</td>
				<td>
					${sourceConfig.url}
				</td>
				<td>
					<img alt="${sourceConfig.name}" src="${sourceConfig.backGround}">
				</td>
				<td id="status-${sourceConfig.id}">
					${fns:getDictLabel(sourceConfig.status, 'source_config_status', '')}
				</td>
				<td>
					<fmt:formatDate value="${sourceConfig.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${sourceConfig.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sourceConfig.createBy.name}
				</td>
				<td>
					${sourceConfig.updateBy.name}
				</td>
				<shiro:hasPermission name="app:source:sourceConfig:edit"><td>
    				<a href="${ctx}/app/source/sourceConfig/form?id=${sourceConfig.id}">修改</a>
					<c:if test="${sourceConfig.status==0}"><a id="updateClose" href="#" data-id="${sourceConfig.id}" data-status="1"><span style="color:#b94a48">上架</span></a></c:if>
					<c:if test="${sourceConfig.status==1}"><a id="updateClose" href="#" data-id="${sourceConfig.id}" data-status="0"><span style="color:#b94a48">下架</span></a></c:if>
					<a href="${ctx}/app/source/sourceConfig/delete?id=${sourceConfig.id}" onclick="return confirmx('确认要删除该游戏吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	<script type="text/javascript">
		$("a#updateClose").click(function () {
			var element = $(this);
			var id = $(this).attr("data-id");
			var status = $(this).attr("data-status");
			
			if (0 == status) {
				var tip = '确认要下架该游戏吗？';
			} else {
				var tip = '确认要上架该游戏吗？该游戏的其他上架版本将会改成下架状态';
			}
			
			var url = '${ctx}/app/source/sourceConfig/updateStatus';
			confirmx(tip, function() {
						$.getJSON(url, {
							"id" : id,
							"status" : status,
							"anticache" : Math.floor(Math.random() * 1000)
						}, function(json) {
							if (json.code == 0) {
								if (status == 0) {
									alertx("下架成功");
									//element.attr("onclick", "updateClose(this, " + lessonId + ", 1);");
									element.attr("data-status", 1);
									element.find("span").html('上架');
									$('#status-' + id).html('下架');
									window.location.reload();
								} else {
									//element.attr("onclick", "updateClose(this, " + lessonId + ", 0);");
									element.attr("data-status", 0);
									element.find("span").html('下架');
									$('#status-' + id).html('上架');
									alertx("上架成功");
									window.location.reload();
								}
							} else {	
								alertx(json.reson);
							}
					});
			});
		});
	</script>
</body>
</html>