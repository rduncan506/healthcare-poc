package org.jboss.fuse.example;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v231.message.ADT_A04;

public class PatientVisitService {
    public Message anonimizePatient(Message input) throws HL7Exception {
//        ADT_A04 adt = (ADT_A04)input.get("ADT_A04");
//        String patientId = adt.getPID().getPatientID().toString();
//        System.out.println("The PID is: " + patientId);
//        
//        
//        // find patient data based on the patient id and create a HL7 model object with the response
//        Message response = input.getMessage();
//        return response;
    	System.out.println("In the anonimize Patient method with:");
    	if( input == null) System.out.println("null input");
    	else System.out.println(input.toString());
    	return input;
    }
    public void testMethod(String input){
    	System.out.println("In the test method with\n" + input);
    }
}
