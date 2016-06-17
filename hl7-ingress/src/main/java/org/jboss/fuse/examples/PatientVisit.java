package org.jboss.fuse.examples;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PatientVisit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 98761L;
	private String mname;
	private String fname;
	private String lname;
	private String sex;
	private String providerid;
	private String demoehr;
	private String referral_source;
	private String pid;
	private String facility_id;
	private String race;
	private String visitno;
	private Date visitDate;
	private Boolean hipaa_notice;
	private Date dob;
	private Date update_date;
	private String language;
	private String marital_status;
	private String ethnicity;

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getProviderid() {
		return providerid;
	}

	public void setProviderid(String providerid) {
		this.providerid = providerid;
	}

	public String getDemoehr() {
		return demoehr;
	}

	public void setDemoehr(String demoehr) {
		this.demoehr = demoehr;
	}

	public String getReferral_source() {
		return referral_source;
	}

	public void setReferral_source(String referral_source) {
		this.referral_source = referral_source;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getFacility_id() {
		return facility_id;
	}

	public void setFacility_id(String facility_id) {
		this.facility_id = facility_id;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getVisitno() {
		return visitno;
	}

	public void setVisitno(String visitno) {
		this.visitno = visitno;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public Boolean isHipaa_notice() {
		return hipaa_notice;
	}

	public void setHipaa_notice(Boolean hipaa_notice) {
		this.hipaa_notice = hipaa_notice;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	@Override
	public String toString() {
		return "PatientVisit [mname=" + mname + ", fname=" + fname + ", lname=" + lname + ", sex=" + sex
				+ ", providerid=" + providerid + ", demoehr=" + demoehr + ", referral_source=" + referral_source
				+ ", pid=" + pid + ", facility_id=" + facility_id + ", race=" + race + ", visitno=" + visitno
				+ ", visitDate=" + visitDate + ", hipaa_notice=" + hipaa_notice + ", dob=" + dob + ", update_date="
				+ update_date + ", language=" + language + ", marital_status=" + marital_status + ", ethnicity="
				+ ethnicity + "]";
	}

	public String generateFilename() {
		return "patient-visit-" + new SimpleDateFormat("yyyyMMdd-HH:mm:ss").format(new Date()) + ".xml";
	}

	public void writePatientVisitXML() throws Exception {
		try {
			File file = new File("/home/rduncan/RedHat/projects/Wipro/xmlOutput/" + this.generateFilename());
			JAXBContext jaxbContext = JAXBContext.newInstance(PatientVisit.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(this, file);
			jaxbMarshaller.marshal(this, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
