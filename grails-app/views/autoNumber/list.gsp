
<%@ page import="edu.umd.lib.grails.services.AutoNumber" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'autoNumber.label', default: 'AutoNumber')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-autoNumber" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-autoNumber" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="id" title="${message(code: 'autoNumber.id.label', default: 'AutoNumber')}" />
					
						<g:sortableColumn property="entryDate" title="${message(code: 'autoNumber.entryDate.label', default: 'Entry Date')}" />
					
						<g:sortableColumn property="initials" title="${message(code: 'autoNumber.initials.label', default: 'Initials')}" />
					
						<g:sortableColumn property="repository" title="${message(code: 'autoNumber.repository.label', default: 'Repository')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${autoNumberInstanceList}" status="i" var="autoNumberInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${autoNumberInstance.id}">${fieldValue(bean: autoNumberInstance, field: "id")}</g:link></td>
					
						<td>${fieldValue(bean: autoNumberInstance, field: "entryDate")}</td>
					
						<td>${fieldValue(bean: autoNumberInstance, field: "initials")}</td>
					
						<td>${fieldValue(bean: autoNumberInstance, field: "repository")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${autoNumberInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
