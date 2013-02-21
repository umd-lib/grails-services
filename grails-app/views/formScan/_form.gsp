<%@ page import="edu.umd.lib.grails.services.FormScan" %>



<div class="fieldcontain ${hasErrors(bean: formScanInstance, field: 'repository', 'error')} ">
	<label for="repository">
		<g:message code="formScan.repository.label" default="Repository" />
		
	</label>
	<g:textField name="repository" value="${formScanInstance?.repository}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: formScanInstance, field: 'initials', 'error')} ">
	<label for="initials">
		<g:message code="formScan.initials.label" default="Initials" />
		
	</label>
	<g:textField name="initials" value="${formScanInstance?.initials}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: formScanInstance, field: 'entryDate', 'error')} ">
	<label for="entryDate">
		<g:message code="formScan.entryDate.label" default="Entry Date" />
		
	</label>
	<g:datePicker name="entryDate" precision="day"  value="${formScanInstance?.entryDate}" default="none" noSelection="['': '']" />
</div>

