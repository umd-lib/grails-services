<%@ page import="edu.umd.lib.grails.services.AutoNumber" %>



<div class="fieldcontain ${hasErrors(bean: autoNumberInstance, field: 'repository', 'error')} ">
	<label for="repository">
		<g:message code="autoNumber.repository.label" default="Repository" />
		
	</label>
	<g:textField name="repository" value="${autoNumberInstance?.repository}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: autoNumberInstance, field: 'initials', 'error')} ">
	<label for="initials">
		<g:message code="autoNumber.initials.label" default="Initials" />
		
	</label>
	<g:textField name="initials" value="${autoNumberInstance?.initials}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: autoNumberInstance, field: 'entryDate', 'error')} ">
	<label for="entryDate">
		<g:message code="autoNumber.entryDate.label" default="Entry Date" />
		
	</label>
	<g:datePicker name="entryDate" precision="day"  value="${autoNumberInstance?.entryDate}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: autoNumberInstance, field: 'autonumber', 'error')} required">
	<label for="autonumber">
		<g:message code="autoNumber.autonumber.label" default="Autonumber" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="autonumber" type="number" value="${autoNumberInstance.autonumber}" required=""/>
</div>

