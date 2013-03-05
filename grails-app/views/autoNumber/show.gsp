
<%@ page import="edu.umd.lib.grails.services.AutoNumber" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'autoNumber.label', default: 'AutoNumber')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-autoNumber" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-autoNumber" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list autoNumber">
			
				<g:if test="${autoNumberInstance?.entryDate}">
				<li class="fieldcontain">
					<span id="entryDate-label" class="property-label"><g:message code="autoNumber.entryDate.label" default="Entry Date" /></span>
					
						<span class="property-value" aria-labelledby="entryDate-label"><g:formatDate date="${autoNumberInstance?.entryDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${autoNumberInstance?.initials}">
				<li class="fieldcontain">
					<span id="initials-label" class="property-label"><g:message code="autoNumber.initials.label" default="Initials" /></span>
					
						<span class="property-value" aria-labelledby="initials-label"><g:link controller="initials" action="show" id="${autoNumberInstance?.initials?.id}">${autoNumberInstance?.initials?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${autoNumberInstance?.repository}">
				<li class="fieldcontain">
					<span id="repository-label" class="property-label"><g:message code="autoNumber.repository.label" default="Repository" /></span>
					
						<span class="property-value" aria-labelledby="repository-label"><g:link controller="repository" action="show" id="${autoNumberInstance?.repository?.id}">${autoNumberInstance?.repository?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${autoNumberInstance?.id}" />
					<g:link class="edit" action="edit" id="${autoNumberInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
