import grails.util.Environment

import javax.naming.Context
import javax.naming.InitialContext

// Place your Spring DSL code here
beans = {
	
	if (Environment.getCurrent() == Environment.DEVELOPMENT) {
//		// Initial environment with various properties
//		Hashtable env = new Hashtable();
//		env.put(Context.INITIAL_CONTEXT_FACTORY,
//			"com.sun.jndi.fscontext.FSContextFactory");
//		env.put(Context.PROVIDER_URL, "file:./test/integration/context.xml");
//		env.put(Context.OBJECT_FACTORIES, "foo.bar.ObjFactory");
//		
//		// Call the constructor
//		Context ctx = new InitialContext(env);
		
		repoFetchUrl(org.springframework.jndi.JndiObjectFactoryBean){
			defaultObject = new String ("http://fedorastage.lib.umd.edu/vocab/lists/repository_prefix/read")
			jndiName = 'java:comp/env/repoFetchUrl'
		}
		repoUpdateUrl(org.springframework.jndi.JndiObjectFactoryBean){
			defaultObject = new String ("http://fedorastage.lib.umd.edu/vocab/lists/repository_prefix/term/{term}/create")
			jndiName = 'java:comp/env/repoUpdateUrl'
		}
	} else if(Environment.getCurrent() == Environment.PRODUCTION) {
		repoFetchUrl(org.springframework.jndi.JndiObjectFactoryBean){
			jndiName = 'java:comp/env/repoFetchUrl'
		}
		repoUpdateUrl(org.springframework.jndi.JndiObjectFactoryBean){
			jndiName = 'java:comp/env/repoUpdateUrl'
		}
	} else if(Environment.getCurrent() == Environment.TEST) {
	}
}
