<%@ page import="edu.umd.lib.grails.services.AutoNumber" %>



<div class="fieldcontain ${hasErrors(bean: autoNumberInstance, field: 'entryDate', 'error')} ">
	<label for="entryDate">
		<g:message code="autoNumber.entryDate.label" default="Entry Date" />
		
	</label>
	<g:datePicker name="entryDate" precision="day"  value="${autoNumberInstance?.entryDate}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: autoNumberInstance, field: 'initials', 'error')} required">
	<label for="initials">
		<g:message code="autoNumber.initials.label" default="Initials" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="initials" name="initials.id" from="${edu.umd.lib.grails.services.Initials.list()}" optionKey="id" required="" value="${autoNumberInstance?.initials?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: autoNumberInstance, field: 'repository', 'error')} required">
	<label for="repository">
		<g:message code="autoNumber.repository.label" default="Repository" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="repository" name="repository.id" from="${edu.umd.lib.grails.services.Repository.list()}" optionKey="id" required="" value="${autoNumberInstance?.repository?.id}" class="many-to-one"/>
</div>

