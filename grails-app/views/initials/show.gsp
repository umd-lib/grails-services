
<%@ page import="edu.umd.lib.grails.services.Initials" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'initials.label', default: 'Initials')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-initials" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-initials" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list initials">
			
				<g:if test="${initialsInstance?.initialsName}">
				<li class="fieldcontain">
					<span id="initialsName-label" class="property-label"><g:message code="initials.initialsName.label" default="Initials Name" /></span>
					
						<span class="property-value" aria-labelledby="initialsName-label"><g:fieldValue bean="${initialsInstance}" field="initialsName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${initialsInstance?.autoNumber}">
				<li class="fieldcontain">
					<span id="autoNumber-label" class="property-label"><g:message code="initials.autoNumber.label" default="Auto Number" /></span>
					
						<g:each in="${initialsInstance.autoNumber}" var="a">
						<span class="property-value" aria-labelledby="autoNumber-label"><g:link controller="autoNumber" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${initialsInstance?.id}" />
					<g:link class="edit" action="edit" id="${initialsInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
