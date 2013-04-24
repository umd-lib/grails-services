dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
			pooled = false
      		logSql = false
      		username = "sa"
      		password = ""
      		driverClassName = "org.h2.Driver"
      		dbCreate = "create-drop"
      		url = "jdbc:h2:devDB;AUTO_SERVER=TRUE"
        }
    }
	dataloadenv {
		dataSource {
			pooled = false
			  logSql = false
			  dbCreate = "create-drop"
			  username="gs"
			  password="gs"
			  driverClassName="org.postgresql.Driver"
			  url="jdbc:postgresql://localhost:5432/gs"
		}
	}
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            pooled = false
            jndiName = "java:comp/env/jdbc/gsDS"
        }
    }
}
