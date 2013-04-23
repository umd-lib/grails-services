import edu.umd.lib.grails.services.Repository;
import edu.umd.lib.grails.services.Initials;
import edu.umd.lib.grails.services.AssignedAutoNumber;
import edu.umd.lib.grails.services.Repository;        

new File("/tmp/tblScanningAutoNumber.csv").withReader { reader ->
  while ((line = reader.readLine()) != null) {
    println(line)
    def argArray = line.split(',',-1)
    println(argArray)
    //if(argArray.size() > 1) {
        def repo = Repository.findByRepositoryName(argArray[1])
            
        if(repo == null) {
            repo = new Repository(repositoryName : argArray[1])
            repo.save(failOnError : true, flush: true)
        }
            
        def init = Initials.findByInitialsName(argArray[2])
            
        if(init == null) {
            init = new Initials(initialsName : argArray[2])
            init.save(failOnError : true, flush: true)
        }
        
        def newDate = null
        if(!argArray[3].isEmpty()) {
            newDate = new Date(argArray[3].trim())
        }
            
        def newRec = new AssignedAutoNumber(id : argArray[0].toInteger(), initials: init, repository : repo, entryDate: newDate )
        newRec.setId( argArray[0].toInteger())
        println newRec
        def retVal = newRec.save(failOnError : true, flush: true)
    //}
}
}