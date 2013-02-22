class UrlMappings {

	static mappings = {
		
		"/restapi/autonumber/$repository/$initials"(controller: "restautonumber", action:"show")
		
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
