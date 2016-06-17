package org.jboss.fuse.examples;

import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

import org.slf4j.Logger;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v24.message.ADT_A01;
import ca.uhn.hl7v2.model.v24.message.QRY_A19;
import ca.uhn.hl7v2.model.v24.segment.PID;
import ca.uhn.hl7v2.model.v24.segment.PV1;
import ca.uhn.hl7v2.model.v24.segment.QRD;
import ca.uhn.hl7v2.util.Terser;

public class HL7MessageService {

	final Logger logger = LoggerFactory.getLogger(HL7MessageService.class);

	public String lookupPatient(String body) throws HL7Exception {
		// QRD qrd = (QRD)input.get("QRD");
		// String patientId =
		// qrd.getWhoSubjectFilter(0).getIDNumber().getValue();

		// find patient data based on the patient id and create a HL7 model
		// object with the response
		String response = body.toString();

		return response;
	}

	public Message unhandledMessage(Message input) throws HL7Exception {
		logger.info("Unhandled Message, no processing being done");
		logger.info("Message Name is {}", input.getName());

		return input;
	}

	public Message handleA19(Message input) throws HL7Exception {
		logger.info("Processing QRY_A19 message");

		Message response = input;
		if (input instanceof QRY_A19) {
			QRD qrd = (QRD) input.get("QRD");
			String patientId = qrd.getWhoSubjectFilter(0).getIDNumber().getValue();
			logger.info("Patient ID is {}", patientId);
		}

		return response;
	}

	public Message handleA01(Message input) throws HL7Exception {
		logger.info("Processing ADT_A01 message");

		Message response = input;
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		
		if (input instanceof ADT_A01) {
			ADT_A01 adt = (ADT_A01) input;
			String familyName = "No Name";
			String givenName = "No Name";
			PatientVisit pv = new PatientVisit();
			PID pid = adt.getPID();
			PV1 pv1 = adt.getPV1();
			
			if (pid.getPatientName().length == 1) {
				familyName = pid.getPatientName()[0].getFamilyName().getFn1_Surname().toString();
				givenName = pid.getPatientName()[0].getGivenName().toString();

				// adt.getPID().removePatientName(0);

				Terser terser = new Terser(adt);
				terser.set("/PID-5-1", familyName.substring(0, 1));
				// terser.set("/PID-5-2",givenName.substring(0, 1));

				pv.setFname(pid.getPatientName()[0].getGivenName().toString().substring(0, 1));
				pv.setLname(pid.getPatientName()[0].getFamilyName().getFn1_Surname().toString());

			}

			pv.setDemoehr("DemoEHR");
			if (pid.getPatientIdentifierList().length > 0)
				pv.setPid(pid.getPatientIdentifierList()[0].getID().toString());
			if (pid.getRace().length > 0)
				pv.setRace(pid.getRace()[0].getIdentifier().toString());
			pv.setSex(pid.getAdministrativeSex().getValue());
			
			pv.setMarital_status(pid.getMaritalStatus().getIdentifier().toString());
			if(pid.getEthnicGroup().length > 0 ) pv.setEthnicity(pid.getEthnicGroup()[0].getText().toString());
			pv.setLanguage(pid.getPrimaryLanguage().getText().toString());
			
			pv.setFacility_id(pv1.getServicingFacility().getValue());
			pv.setVisitno(pv1.getVisitNumber().getID().toString());
			
			
			try {
				pv.setVisitDate(sd.parse(pv1.getAdmitDateTime().getTimeOfAnEvent().toString()));
				pv.setDob(sd.parse(pid.getDateTimeOfBirth().getTimeOfAnEvent().toString()));
				if(pid.getLastUpdateDateTime().getTimeOfAnEvent().toString() != null) 
					pv.setUpdate_date(sd.parse(pid.getLastUpdateDateTime().getTimeOfAnEvent().toString()));

				pv.writePatientVisitXML();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("Patient Name is {}, {}", familyName, givenName);
		}

		return response;
	}
}
