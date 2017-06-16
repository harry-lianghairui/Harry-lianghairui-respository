<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>游戏管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/app/source/sourceConfig/">游戏列表</a></li>
		<li class="active"><a href="${ctx}/app/source/sourceConfig/form?id=${sourceConfig.id}">游戏<shiro:hasPermission name="app:source:sourceConfig:edit">${not empty sourceConfig.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="app:source:sourceConfig:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sourceConfig" action="${ctx}/app/source/sourceConfig/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<c:choose>
			<c:when test="${sourceConfig.id != null && sourceConfig.id != ''}">
				<input name="status" value="${sourceConfig.status}" type="hidden"/>
			</c:when>
			<c:otherwise>
				<input name="status" value="0" type="hidden"/>
			</c:otherwise>
		</c:choose>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">游戏id：</label>
			<div class="controls">
				<form:input path="resourceId" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">游戏名称：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">包名称：</label>
			<div class="controls">
				<form:input path="pName" htmlEscape="false" maxlength="30" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
<%-- 		<div class="control-group">
			<label class="control-label">包大小(M)：</label>
			<div class="controls">
				<form:input path="fileSize" htmlEscape="false" maxlength="30" class="input-xlarge number required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">包版本：</label>
			<div class="controls">
				<form:input path="version" htmlEscape="false" maxlength="20" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">包地址：</label>
			<div class="controls">
				<form:input path="url" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">背景图：</label>
			<div class="controls">
				<form:input path="backGround" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="app:source:sourceConfig:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>