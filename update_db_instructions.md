## Update DB Instructions

`export GRAILS_HOME=/home/cms/grails-2.2.1`

`cd /apps/cms/src/grails-services/`

`/home/cms/grails-2.2.1/bin/grails` 

`-Dgrails.env=dataloadenv console`

open and run script called tmp in grails-services/scripts dir

expects csv to be located `/tmp/tblScanningAutoNumber.csv`

`psql --username=gs`

`ALTER SEQUENCE hibernate_sequence RESTART WITH 18666;`
