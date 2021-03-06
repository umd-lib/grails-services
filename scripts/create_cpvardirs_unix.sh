#!/bin/sh
# javadocs cannot use classpath variables in Eclipse. Provide workaround so that a team can share common .classpath and .project files.
# change the default location with grails create-eclipse-files --cpvars-dir=/some/other/dir
mkdir -p "/Users/ascheffe/sandbox/grails-2.2.0/eclipse-cpvars"
cd "/Users/ascheffe/sandbox/grails-2.2.0/eclipse-cpvars"
ln -s "/Users/ascheffe/.grails/ivy-cache" "GRAILS_IVYCACHE"
ln -s "/Users/ascheffe/.grails/2.2.0" "GRAILS_WORKDIR"
echo "Run grails create-eclipse-files again to create common paths to javadocs in .classpath (team members can share .classpath files and they can be in versioned in the source code repository)"
