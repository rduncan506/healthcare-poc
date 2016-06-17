/*
 * Copyright (C) Red Hat, Inc.
 * http://www.redhat.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.fuse.examples;

import org.apache.activemq.broker.BrokerService;
import org.apache.camel.*;
import org.apache.camel.builder.NotifyBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.apache.camel.util.FileUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:/META-INF/spring/*.xml"})
public class HL7IngressRouteTest {


    @Autowired(required=true)
    private CamelContext testRoutes;

    @Autowired(required=true)
    private CamelContext ingressCamel;

    @Produce(uri = "netty4:tcp://127.0.0.1:8888?sync=true&decoder=#hl7decoder&encoder=#hl7encoder")
    private ProducerTemplate hl7TcpProducer;

    @EndpointInject(uri = "mock:messagingMock")
    private MockEndpoint messagingMock;

    @EndpointInject(uri = "mock:fileMock")
    private MockEndpoint fileMock;

    private static BrokerService broker;

    @BeforeClass
    public static void bootActiveMQ() throws Exception {
        broker = new BrokerService();
        broker.addConnector("tcp://localhost:61616");
        broker.setPersistent(false);
        broker.deleteAllMessages();
        broker.start();
        broker.waitUntilStarted();
    }

    @BeforeClass
    public static void cleanDirectories() {
        File auditDir = new File("./target/audit");
        if (auditDir.exists()) {
            FileUtil.removeDir(auditDir);
        }
        auditDir.mkdir();
    }

    @AfterClass
    public static void stopActiveMQ() throws Exception {
        if (broker != null) {
            broker.stop();
            broker.waitUntilStopped();
        }
    }

    private String createValidHl7Message() {
        StringBuilder hl7Message = new StringBuilder();
        hl7Message.append("MSH|^~\\&|FUSEDEMO|ORG|TEST|JBOSS|20061019172719||ADT^A01^ADT_A01|MSGID12349876|P|2.4").append("\r");
        hl7Message.append("PID|||20301||Durden^Tyler^^^Mr.||19700312|M|||88 Punchward Dr.^^Los Angeles^CA^11221^USA|||||||").append("\r");
        hl7Message.append("PV1||O|OP^^||||4652^Paulson^Robert|||OP|||||||||9|||||||||||||||||||||||||20061019172717|20061019172718").append("\r");
        return hl7Message.toString();
    }
    
    private String createValidQRDMessage() {
    	StringBuilder hl7Message = new StringBuilder();
    	hl7Message.append("MSH|^~\\&|MYSENDER|MYRECEIVER|MYAPPLICATION||200612211200||QRY^A19|1234|P|2.4").append("\r");
    	hl7Message.append("QRD|200612211200|R|I|GetPatient|||1^RD|0101701234|DEM||").append("\r");
    	return hl7Message.toString();
    }

    private String createValidADTMessage() {
    	StringBuilder hl7Message = new StringBuilder();
    	hl7Message.append("MSH|^~\\&|||||20160216213412.809-0400||ADT^A01^ADT_A01|3001|T|2.4").append("\r");
    	hl7Message.append("PID|||XYA6254^4^M11||AIKMAN^EMMITT||19650318|M||C|1^COWBOY WAY^^DALLAS^TX^63976-6644|GL|(474)954-9598|(573)126-9723|English|D||XYA6254001^2^M10|123456789|987654^NC|").append("\r");
    	hl7Message.append("PV1|1|O|4E^234^A^Good Health Hospital&2.16.840.1.113883.19.3.2.3&ISO^N^N^Building 1^4^Nursing unit 4 East^1234&&2.16.840.1.113883.19.3.2.3&ISO^&2.16.840.1.113883.19.3.2.3&ISO|R|||||||||||||||SCREENING||||||||||||||||||||5|||||20150815|200808151200-0700").append("\r");

    	/*
    	 * MSH|^~\&|||||20160216213412.809-0400||ADT^A01^ADT_A01|3001|T|2.4
PID|||XYA6254^4^M11||AIKMAN^EMMITT||19650318|M||C|1^COWBOY WAY^^DALLAS^TX^63976-6644|GL|(474)954-9598|(573)126-9723|English|D||XYA6254001^2^M10|123456789|987654^NC|
PV1|1|O|4E^234^A^Good Health Hospital&2.16.840.1.113883.19.3.2.3&ISO^N^N^Building 1^4^Nursing unit 4 East^1234&&2.16.840.1.113883.19.3.2.3&ISO^&2.16.840.1.113883.19.3.2.3&ISO|R|||||||||||||||SCREENING||||||||||||||||||||5|||||20150815|200808151200-0700
    	 * 
    	 */
    	return hl7Message.toString();
    }
//    @Test
//    public void testHl7TcpRouteValidMessage() throws Exception {
//
//        messagingMock.expectedMessageCount(1);
//        fileMock.expectedMessageCount(1);
//        NotifyBuilder notify = new NotifyBuilder(ingressCamel).whenCompleted(2).create();
//        String resp = hl7TcpProducer.requestBody((Object) createValidHl7Message(), String.class);
//        assertNotNull(resp);
//        assertThat(resp, containsString("MSA|AA|MSGID12349876"));
//        notify.matches(2, TimeUnit.SECONDS);
//
//        MockEndpoint.assertIsSatisfied(testRoutes, 2, TimeUnit.SECONDS);
//
//    }
    
//    @Test
//    public void testHl7TcpRouteValidQRDMessage() throws Exception {
//
//        messagingMock.expectedMessageCount(1);
//        fileMock.expectedMessageCount(1);
//        NotifyBuilder notify = new NotifyBuilder(ingressCamel).whenCompleted(2).create();
//        String resp = hl7TcpProducer.requestBody((Object) createValidQRDMessage(), String.class);
//        assertNotNull(resp);
//        assertThat(resp, containsString("MSA|AA|MSGID12349876"));
//        notify.matches(2, TimeUnit.SECONDS);
//
//        MockEndpoint.assertIsSatisfied(testRoutes, 2, TimeUnit.SECONDS);
//
//    }

    @Test
    public void testHl7TcpRouteValidADTMessage() throws Exception {

        messagingMock.expectedMessageCount(1);
        fileMock.expectedMessageCount(1);
        NotifyBuilder notify = new NotifyBuilder(ingressCamel).whenCompleted(2).create();
        String resp = hl7TcpProducer.requestBody((Object) createValidADTMessage(), String.class);
        assertNotNull(resp);
        assertThat(resp, containsString("MSA|AA|MSGID12349876"));
        notify.matches(2, TimeUnit.SECONDS);

        MockEndpoint.assertIsSatisfied(testRoutes, 2, TimeUnit.SECONDS);

    }

}
