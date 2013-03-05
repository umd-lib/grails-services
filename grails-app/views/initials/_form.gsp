<%@ page import="edu.umd.lib.grails.services.Initials" %>



<div class="fieldcontain ${hasErrors(bean: initialsInstance, field: 'initialsName', 'error')} ">
	<label for="initialsName">
		<g:message code="initials.initialsName.label" default="Initials Name" />
		
	</label>
	<g:textField name="initialsName" value="${initialsInstance?.initialsName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: initialsInstance, field: 'autoNumber', 'error')} ">
	<label for="autoNumber">
		<g:message code="initials.autoNumber.label" default="Auto Number" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${initialsInstance?.autoNumber?}" var="a">
    <li><g:link controller="autoNumber" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="autoNumber" action="create" params="['initials.id': initialsInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'autoNumber.label', default: 'AutoNumber')])}</g:link>
</li>
</ul>

</div>

