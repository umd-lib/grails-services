<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="AutoNumberForm">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'AutoNumberForm.css')}" type="text/css">
		<link href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" type="text/css" rel="stylesheet" media="screen, projection" />
		<g:javascript src="jquery-1.9.1.js" />
		<g:javascript src="jquery-ui-1.10.1.custom.js" />
		<g:javascript src="AutoNumberForm.js" />
		<script>
			$(document).ready(function() {	
				$( "#initialsSelectBox" ).combobox({inputName : "initials"});	
				$( "#selectBox" ).combobox({inputName : "repository"});
			});
		</script>
		<title></title>
	</head>
	<body>
		<div class="bodyOutline">
			<form class="formLayout" action="autonumberform" method="post">
				<div class="fieldcontain ${hasErrors(bean: autoNumberInstance, field: 'repository', 'error')} ">
					<label class="formLabel" for="repository">
						<g:message code="autoNumber.repository.label" default="Repository:" />			
					</label>
					<g:select name="repositorySelect" id="selectBox" from="${repos}" noSelection="['':'-Choose your repository-']" />
					<%-- <g:textField name="repositoryInput" id="repositoryInput" /> --%>
				</div>
				<div class="fieldcontain ${hasErrors(bean: autoNumberInstance, field: 'initials', 'error')} ">
					<label class="formLabel" for="initials">
						<g:message code="autoNumber.initials.label" default="Initials:" />				
					</label>
					<g:select name="initialsSelect" id="initialsSelectBox" from="${edu.umd.lib.grails.services.Initials.list()}" noSelection="['':'-Choose your initials-']" />
				</div>
				<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
			</form>
			
			<div class="formLayout ${hasErrors(bean: autoNumberInstance, field: 'initials', 'error')} ">
				<label class="formLabel" for="fileName">
					<g:message code="autoNumber.fileName.label" default="File Name:" />				
				</label>
				<g:textField name="fileName" id="fileName" disabled="true" value="${fileName}" />
			</div>
		</div>
	</body>
</html>

