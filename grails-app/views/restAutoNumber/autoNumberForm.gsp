<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="AutoNumberForm">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'AutoNumberForm.css')}" type="text/css">
		<script>
			function getCombo(sel) {
			    var value = sel.options[sel.selectedIndex].value;  
			    var element = document.getElementById('repositoryInput');
			    element.value = value;
			}
		</script>
		<title></title>
	</head>
	<body>
		<div>
		<form action="autonumberform" method="post">
			<div class="fieldcontain ${hasErrors(bean: autoNumberInstance, field: 'repository', 'error')} ">
				<label class="formLabel" for="repository">
					<g:message code="autoNumber.repository.label" default="Repository" />			
				</label>
				<g:select name="repositorySelect" id="selectBox" from="${repos}" noSelection="['':'-Choose your repository-']" onchange="getCombo(this)"/>
				<g:textField name="repositoryInput" id="repositoryInput" />
			</div>
			<div class="fieldcontain ${hasErrors(bean: autoNumberInstance, field: 'initials', 'error')} ">
				<label class="formLabel" for="initials">
					<g:message code="autoNumber.initials.label" default="Initials" />				
				</label>
				<g:textField name="initials" id="initialInput" />
			</div>
			<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
		</form>

		</div>
	</body>
</html>

