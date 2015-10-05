## Karaf features file  
This module builds a [karaf-feature file](https://karaf.apache.org/manual/latest/users-guide/provisioning.html) to make it easier to provision the pieces of the project to a _stand-alone_ [JBoss Fuse](http://www.jboss.org/products/fuse/download/) container.

For example, to install on a Fuse 6.x JVM, type the following from the shell console:

    features:addurl mvn:org.jboss.fuse.examples/hl7-example-features/1.0.0-SNAPSHOT/xml/features
    features:install hl7-example-all