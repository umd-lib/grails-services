
<%@ page import="edu.umd.lib.grails.services.Repository" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'repository.label', default: 'Repository')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-repository" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-repository" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list repository">
			
				<g:if test="${repositoryInstance?.repositoryName}">
				<li class="fieldcontain">
					<span id="repositoryName-label" class="property-label"><g:message code="repository.repositoryName.label" default="Repository Name" /></span>
					
						<span class="property-value" aria-labelledby="repositoryName-label"><g:fieldValue bean="${repositoryInstance}" field="repositoryName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${repositoryInstance?.autoNumber}">
				<li class="fieldcontain">
					<span id="autoNumber-label" class="property-label"><g:message code="repository.autoNumber.label" default="Auto Number" /></span>
					
						<g:each in="${repositoryInstance.autoNumber}" var="a">
						<span class="property-value" aria-labelledby="autoNumber-label"><g:link controller="autoNumber" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${repositoryInstance?.id}" />
					<g:link class="edit" action="edit" id="${repositoryInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
