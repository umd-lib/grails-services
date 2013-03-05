import edu.umd.lib.grails.services.AutoNumber
import edu.umd.lib.grails.services.Initials
import edu.umd.lib.grails.services.Repository
import grails.util.Environment


class BootStrap {

    def init = { servletContext ->
		println "Enviroment [" + Environment.current + "]"
		if (Environment.current == Environment.DEVELOPMENT) {
			println "loading dev"
			for(val in ['bcast', 'bna', 'histmss', 'litmss', 'map', 'md', 'ntl', 'rare', 'scpa', 'univarch', 'usgov']) {
				def newRec = new Repository(repositoryName : val)
				newRec.save(failOnError : true)
			}
			println "Rep done"
			def init = new Initials (initialsName: 'SRE')
			init.save()
			def aut = new AutoNumber(initials: init, repository : Repository.find(new Repository(repositoryName : "bna")), entryDate: new Date())
			aut.save(failOnError : true)
			println "Aut done"
//			def db = [url:'jdbc:h2:devDB;AUTO_SERVER=TRUE', user:'sa', password:'', driver:'org.h2.Driver']
//			def sql = Sql.newInstance(db.url, db.user, db.password, db.driver)
//			sql.execute 'INSERT INTO AUTONUMBER (AUTONUMBER_ID,REPOSITORY,INITIALS,ENTRY_DATE) (SELECT * FROM CSVREAD(\'./tblScanningAutoNumber.csv\'))'
		

			
		}
    }
    def destroy = {
    }
}
