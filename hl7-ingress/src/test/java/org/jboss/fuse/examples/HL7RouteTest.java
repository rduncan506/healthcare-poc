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
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringJUnit4ClassRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(CamelSpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:/META-INF/spring/*.xml"})
public class HL7RouteTest {

    @Produce(uri = "netty4:tcp://127.0.0.1:8888?sync=true&decoder=#hl7decoder&encoder=#hl7encoder")
    private ProducerTemplate hl7TcpProducer;

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

    @Test
    public void testHl7TcpRouteValidMessage() throws Exception {
        String resp = hl7TcpProducer.requestBody((Object) createValidHl7Message(), String.class);
        System.out.println(resp);
    }

}
