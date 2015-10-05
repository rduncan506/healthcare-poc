This is an intial POC for using Fuse/Camel/AMQ from JBoss Fuse 6.2 to prove out health-care use cases with HL7

features:addurl mvn:org.jboss.fuse.examples/hl7-example-features/1.0.0-SNAPSHOT/xml/features
features:install hl7-example-all

for fabric deployment:
    mvn clean install -Pfabric fabric8:deploy
    
deploy w/ insight:

    mvn clean install -Pfabric -Pinsight fabric8:deploy