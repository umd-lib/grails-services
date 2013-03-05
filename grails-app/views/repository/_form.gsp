<%@ page import="edu.umd.lib.grails.services.Repository" %>



<div class="fieldcontain ${hasErrors(bean: repositoryInstance, field: 'repositoryName', 'error')} ">
	<label for="repositoryName">
		<g:message code="repository.repositoryName.label" default="Repository Name" />
		
	</label>
	<g:textField name="repositoryName" value="${repositoryInstance?.repositoryName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: repositoryInstance, field: 'autoNumber', 'error')} ">
	<label for="autoNumber">
		<g:message code="repository.autoNumber.label" default="Auto Number" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${repositoryInstance?.autoNumber?}" var="a">
    <li><g:link controller="autoNumber" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="autoNumber" action="create" params="['repository.id': repositoryInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'autoNumber.label', default: 'AutoNumber')])}</g:link>
</li>
</ul>

</div>

