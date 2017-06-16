<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>游戏统计管理</title>
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
		<li class="active"><a href="${ctx}/app/source/sourceCount/">游戏统计列表</a></li>
<%-- 		<shiro:hasPermission name="app:source:sourceCount:edit"><li><a href="${ctx}/app/source/sourceCount/form">游戏统计添加</a></li></shiro:hasPermission>
 --%>	</ul>
	<form:form id="searchForm" modelAttribute="sourceCount" action="${ctx}/app/source/sourceCount/groupList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>游戏id：</label>
				<form:input path="sourceId" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>开始时间：</label>
				<input type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required" placeholder="开始时间"
					value="<fmt:formatDate value="${sourceCount.beginTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" name="beginTimeStr"/> - 
				<input type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required" placeholder="结束时间"
					value="<fmt:formatDate value="${sourceCount.endTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" name="endTimeStr"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>游戏id</th>
				<th>时间</th>
				<th>点击次数</th>
				<th>进入下载页次数</th>
				<th>下载完成次数</th>
				<th>打开次数</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sourceCount">
			<tr>
				<td>
					${sourceCount.sourceId}
				</td>
				<td>
					<fmt:formatDate value="${sourceCount.createDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${sourceCount.clickCount == null ? 0 : sourceCount.clickCount}
				</td>
				<td>
					${sourceCount.inDownloadCount == null ? 0 : sourceCount.inDownloadCount}
				</td>
				<td>
					${sourceCount.completeCount == null ? 0 : sourceCount.completeCount}
				</td>
				<td>
					${sourceCount.openCount == null ? 0 : sourceCount.openCount}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>