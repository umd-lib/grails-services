class UrlMappings {

	static mappings = {
		
		"/restapi/autonumber/$repository/$initials"(controller: "restAutoNumber") {
			action = [GET: "show", PUT: "save", DELETE: "delete", POST: "save"]
		}
		
		"/autonumberform"(controller: "restAutoNumber") {
			action = [GET: "displayForm", POST: "saveForm"]
		}
		
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
