import grails.util.Environment
import groovy.sql.Sql

class BootStrap {

    def init = { servletContext ->
		if (Environment.current == Environment.DEVELOPMENT) {
			def db = [url:'jdbc:h2:devDB;AUTO_SERVER=TRUE', user:'sa', password:'', driver:'org.h2.Driver']
			def sql = Sql.newInstance(db.url, db.user, db.password, db.driver)
			sql.execute 'INSERT INTO AUTONUMBER (AUTONUMBER_ID,REPOSITORY,INITIALS,ENTRY_DATE) (SELECT * FROM CSVREAD(\'./tblScanningAutoNumber.csv\'))'
		}
    }
    def destroy = {
    }
}
