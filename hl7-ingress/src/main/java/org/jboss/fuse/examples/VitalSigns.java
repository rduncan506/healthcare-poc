package org.jboss.fuse.examples;

import java.io.Serializable;
import java.util.Date;

public class VitalSigns implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6578774741616443133L;
	
	private double waist_circ;
	private double weight;
	private double head_circ;
	private double bmi;
	private String demoehr;
	private int bpd;
	private double oxygen_saturation;
	private int pid;
	private double pulse;
	private String visitno;
	private String bmi_status;
	private double temperature;
	private Date visit_date;
	private double height;
	private int bps;
	private double respiration;
	private int siteid;
	private String temp_method;
	public double getWaist_circ() {
		return waist_circ;
	}
	public void setWaist_circ(double waist_circ) {
		this.waist_circ = waist_circ;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getHead_circ() {
		return head_circ;
	}
	public void setHead_circ(double head_circ) {
		this.head_circ = head_circ;
	}
	public double getBmi() {
		return bmi;
	}
	public void setBmi(double bmi) {
		this.bmi = bmi;
	}
	public String getDemoehr() {
		return demoehr;
	}
	public void setDemoehr(String demoehr) {
		this.demoehr = demoehr;
	}
	public int getBpd() {
		return bpd;
	}
	public void setBpd(int bpd) {
		this.bpd = bpd;
	}
	public double getOxygen_saturation() {
		return oxygen_saturation;
	}
	public void setOxygen_saturation(double oxygen_saturation) {
		this.oxygen_saturation = oxygen_saturation;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public double getPulse() {
		return pulse;
	}
	public void setPulse(double pulse) {
		this.pulse = pulse;
	}
	public String getVisitno() {
		return visitno;
	}
	public void setVisitno(String visitno) {
		this.visitno = visitno;
	}
	public String getBmi_status() {
		return bmi_status;
	}
	public void setBmi_status(String bmi_status) {
		this.bmi_status = bmi_status;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public Date getVisit_date() {
		return visit_date;
	}
	public void setVisit_date(Date visit_date) {
		this.visit_date = visit_date;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public int getBps() {
		return bps;
	}
	public void setBps(int bps) {
		this.bps = bps;
	}
	public double getRespiration() {
		return respiration;
	}
	public void setRespiration(double respiration) {
		this.respiration = respiration;
	}
	public int getSiteid() {
		return siteid;
	}
	public void setSiteid(int siteid) {
		this.siteid = siteid;
	}
	public String getTemp_method() {
		return temp_method;
	}
	public void setTemp_method(String temp_method) {
		this.temp_method = temp_method;
	}

	
}
