import grails.util.Environment

// Place your Spring DSL code here
beans = {
	
	if (Environment.isDevelopmentMode()) {
		repoFetchUrl(org.springframework.jndi.JndiObjectFactoryBean){
			defaultObject = new String ("http://fedorastage.lib.umd.edu/vocab/lists/repository_prefix/read")
			jndiName = 'java:comp/env/repoFetchUrl'
		}
		repoUpdateUrl(org.springframework.jndi.JndiObjectFactoryBean){
			defaultObject = new String ("http://fedorastage.lib.umd.edu/vocab/lists/repository_prefix/term/{term}/create")
			jndiName = 'java:comp/env/repoUpdateUrl'
		}
	} else {
		repoFetchUrl(org.springframework.jndi.JndiObjectFactoryBean){
			jndiName = 'java:comp/env/repoFetchUrl'
		}
		repoUpdateUrl(org.springframework.jndi.JndiObjectFactoryBean){
			jndiName = 'java:comp/env/repoUpdateUrl'
		}
	}
}
