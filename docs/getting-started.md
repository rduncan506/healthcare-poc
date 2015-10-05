# Getting Started

To get started, clone this repository to your local machine and use your favorite tools to dig in deeper.

### Prerequisites
You'll need the following software to complete this POC:

* [JBoss Fuse 6.2](http://www.jboss.org/products/fuse/download/)
* [Maven 3.2.x](http://archive.apache.org/dist/maven/maven-3/3.2.5/)
* [Git](https://git-scm.com/downloads)
* [JBoss Developer Studio](http://www.jboss.org/products/devstudio/download/)

### Command line
Navigate to directory where you want to clone this project:
    
    cd $proj_root
    git clone git@github.com:christian-posta/healthcare-poc.git
    cd healthcare-poc
    
Now run maven to build the project:

    mvn clean install 
    
Things should compile, the tests should run (successfully!) and you should end up with a terminal like this:

    [INFO] --- maven-install-plugin:2.4:install (default-install) @ stub-services ---
    [INFO] Installing /Users/ceposta/dev/poc/CHOP/workspace/hl7-example/stub-services/target/stub-services-1.0.0-SNAPSHOT.jar to /Users/ceposta/.m2/repository/org/jboss/fuse/examples/stub-services/1.0.0-SNAPSHOT/stub-services-1.0.0-SNAPSHOT.jar
    [INFO] Installing /Users/ceposta/dev/poc/CHOP/workspace/hl7-example/stub-services/pom.xml to /Users/ceposta/.m2/repository/org/jboss/fuse/examples/stub-services/1.0.0-SNAPSHOT/stub-services-1.0.0-SNAPSHOT.pom
    [INFO] ------------------------------------------------------------------------
    [INFO] Reactor Summary:
    [INFO] 
    [INFO] Health Care POC: Parent POM ....................... SUCCESS [0.120s]
    [INFO] Health Care POC: Features XML File ................ SUCCESS [0.164s]
    [INFO] Health Care POC: Ingress of HL7 Messages .......... SUCCESS [3.917s]
    [INFO] Health Care POC: Consumer 1 of HL7 Messages ....... SUCCESS [1.911s]
    [INFO] Health Care POC: Transformer 1 .................... SUCCESS [1.338s]
    [INFO] Health Care POC: Stub services .................... SUCCESS [0.616s]
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESS
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time: 8.730s
    [INFO] Finished at: Mon Oct 05 12:59:29 MST 2015
    [INFO] Final Memory: 52M/1141M
    [INFO] ------------------------------------------------------------------------


### Using JBDS
