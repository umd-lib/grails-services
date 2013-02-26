<%@ page import="edu.umd.lib.grails.services.AutoNumber" %>
<!DOCTYPE html>
<html>
	<head>
		<style type="text/css">
			#repositoryInput {
				margin-left: -211px;
				width: 173px;
				height: 14px;
			}
		</style>
		<script>
			function getCombo(sel) {
			    var value = sel.options[sel.selectedIndex].value;  
			    var element = document.getElementById('repositoryInput');
			    element.value = value;
			}
		</script>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'autoNumber.label', default: 'AutoNumber')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-autoNumber" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="create-autoNumber" class="content scaffold-create" role="main">
			<h1><g:message code="Request Next Number" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${autoNumberInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${autoNumberInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save2" >
				<fieldset class="form">
					<div class="fieldcontain ${hasErrors(bean: autoNumberInstance, field: 'repository', 'error')} ">
						<label for="repository">
							<g:message code="autoNumber.repository.label" default="Repository" />						
						</label>
						<g:select name="repositorySelect" id="selectBox" from="${repos}" noSelection="['':'-Choose your repository-']" onchange="getCombo(this)"/>
						<g:textField id="repositoryInput" name="repository" value="${autoNumberInstance?.repository}"/>
					</div>				
					<div class="fieldcontain ${hasErrors(bean: autoNumberInstance, field: 'initials', 'error')} ">
						<label for="initials">
							<g:message code="autoNumber.initials.label" default="Initials" />
						</label>
						<g:textField name="initials" value="${autoNumberInstance?.initials}"/>
					</div>
					<div class="fieldcontain ${hasErrors(bean: autoNumberInstance, field: 'fileName', 'error')} ">
						<label for="fileName">
							<g:message code="autoNumber.fileName.label" default="File Name" />	
						</label>
						<g:textField name="fileName" disabled="true" value="${fileName}" />
					</div>	
				</fieldset>		
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create2')}" />
				</fieldset>
			</g:form>

		</div>
	</body>
</html>
