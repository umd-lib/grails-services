
<%@ page import="edu.umd.lib.grails.services.FormScan" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'formScan.label', default: 'FormScan')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-formScan" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-formScan" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="repository" title="${message(code: 'formScan.repository.label', default: 'Repository')}" />
					
						<g:sortableColumn property="initials" title="${message(code: 'formScan.initials.label', default: 'Initials')}" />
					
						<g:sortableColumn property="entryDate" title="${message(code: 'formScan.entryDate.label', default: 'Entry Date')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${formScanInstanceList}" status="i" var="formScanInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${formScanInstance.id}">${fieldValue(bean: formScanInstance, field: "repository")}</g:link></td>
					
						<td>${fieldValue(bean: formScanInstance, field: "initials")}</td>
					
						<td><g:formatDate date="${formScanInstance.entryDate}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${formScanInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
