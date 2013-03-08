<%@ page import="edu.umd.lib.grails.services.AutoNumber" %>
<!DOCTYPE html>
<html>
	<head>
		<style type="text/css">
			a.ui-button {
				height: 29px;
				position: fixed;
				width: 14px;
			}
		</style><%--
		<link href="/grails-services/static/plugins/jquery-ui-1.8.24/jquery-ui/themes/ui-lightness/jquery-ui-1.8.24.custom.css" type="text/css" rel="stylesheet" media="screen, projection" />
		<script src="/grails-services/static/plugins/jquery-1.8.3/js/jquery/jquery-1.8.3.min.js" type="text/javascript" ></script>
		<script src="/grails-services/static/plugins/jquery-ui-1.8.24/jquery-ui/js/jquery-ui-1.8.24.custom.min.js" type="text/javascript" ></script>
		--%>

		<link href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" type="text/css" rel="stylesheet" media="screen, projection" />
		<g:javascript src="jquery-1.9.1.js" />
		<g:javascript src="jquery-ui-1.10.1.custom.js" />
		<g:javascript src="AutoNumberForm.js" />
		<script type="text/javascript">
			$(document).ready(function() {	
				$( "#initialsSelectBox" ).combobox({inputName : "initials"});	
				$( "#selectBox" ).combobox({inputName : "repository"});
			});
		</script>
		<%--<g:javascript library="jquery" plugin="jquery"/>
		<r:require module="jquery-ui"/>
		--%>
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
						<g:select name="repositorySelect" id="selectBox" from="${repos}" noSelection="['':'-Choose your repository-']" onchange="getCombo(this,'repositoryInput')"/>
						<%--
						<g:textField id="repositoryInput" name="repository" value="${autoNumberInstance?.repository?.repositoryName}"/>
					--%></div>				
					<div class="fieldcontain ${hasErrors(bean: autoNumberInstance, field: 'initials', 'error')} ">
						<label for="initials">
							<g:message code="autoNumber.initials.label" default="Initials" />
						</label>
						<g:select name="initialsSelect" id="initialsSelectBox" from="${edu.umd.lib.grails.services.Initials.list(sort:'initialsName',order:'asc')}" noSelection="['':'-Choose your initials-']" />
						<%--			
						<g:textField name="initials" id="initialsInput" value="${autoNumberInstance?.initials?.initialsName}"/>
					--%></div>
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