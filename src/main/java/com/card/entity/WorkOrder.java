package com.card.entity;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name="work_order")
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrder {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "work_order_seq_generator")
	@SequenceGenerator(name = "work_order_seq_generator", sequenceName = "work_order_seq", allocationSize = 1, schema = "dbo")
	private int id;

	
	private String workOrderNum;
	private Date workOrderDate;
	private String polfNum;
	private String polfQty;
	private String customerPoOrder;
	private String customerName;
	private String productName;
	private String custArtworkRef;
	private String schemeAuthority;
	private String chipType;
	private String cardType;
	private String electricalInfo;
	private String graphicalInfo;
	private String foilInfo;
	private String binNumber;
	private String persoBatchName;
	private String batchQty;
	private String numOfEnvlopes;
	private String numOfCarriers;
	private String numOfCards;
	private String remarks;
	private String preparedBy;
	private String dateTime;
	private String verifiedBy;
	private int issueNum;
	private int revNum;
	private String issueDate;
	private int workOrderYear;
	private int workOrderSerial;
	private int workOrderMonth;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWorkOrderNum() {
		return workOrderNum;
	}
	public void setWorkOrderNum(String workOrderNum) {
		this.workOrderNum = workOrderNum;
		
	}
	public Date getWorkOrderDate() {
		return workOrderDate;
	}
	public void setWorkOrderDate(Date workOrderDate) {
		this.workOrderDate = workOrderDate;
	}
	public String getPolfNum() {
		return polfNum;
	}
	public void setPolfNum(String polfNum) {
		this.polfNum = polfNum;
	}
	public String getPolfQty() {
		return polfQty;
	}
	public void setPolfQty(String polfQty) {
		this.polfQty = polfQty;
	}
	public String getCustomerPoOrder() {
		return customerPoOrder;
	}
	public void setCustomerPoOrder(String customerPoOrder) {
		this.customerPoOrder = customerPoOrder;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCustArtworkRef() {
		return custArtworkRef;
	}
	public void setCustArtworkRef(String custArtworkRef) {
		this.custArtworkRef = custArtworkRef;
	}
	public String getSchemeAuthority() {
		return schemeAuthority;
	}
	public void setSchemeAuthority(String schemeAuthority) {
		this.schemeAuthority = schemeAuthority;
	}
	public String getChipType() {
		return chipType;
	}
	public void setChipType(String chipType) {
		this.chipType = chipType;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getElectricalInfo() {
		return electricalInfo;
	}
	public void setElectricalInfo(String electricalInfo) {
		this.electricalInfo = electricalInfo;
	}
	public String getGraphicalInfo() {
		return graphicalInfo;
	}
	public void setGraphicalInfo(String graphicalInfo) {
		this.graphicalInfo = graphicalInfo;
	}
	public String getFoilInfo() {
		return foilInfo;
	}
	public void setFoilInfo(String foilInfo) {
		this.foilInfo = foilInfo;
	}
	public String getBinNumber() {
		return binNumber;
	}
	public void setBinNumber(String binNumber) {
		this.binNumber = binNumber;
	}
	public String getPersoBatchName() {
		return persoBatchName;
	}
	public void setPersoBatchName(String persoBatchName) {
		this.persoBatchName = persoBatchName;
	}
	public String getBatchQty() {
		return batchQty;
	}
	public void setBatchQty(String batchQty) {
		this.batchQty = batchQty;
	}
	public String getNumOfEnvlopes() {
		return numOfEnvlopes;
	}
	public void setNumOfEnvlopes(String numOfEnvlopes) {
		this.numOfEnvlopes = numOfEnvlopes;
	}
	public String getNumOFCarriers() {
		return numOfCarriers;
	}
	public void setNumOFCarriers(String numOfCarriers) {
		this.numOfCarriers = numOfCarriers;
	}
	public String getNumOfCards() {
		return numOfCards;
	}
	public void setNumOfCards(String numOfCards) {
		this.numOfCards = numOfCards;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPreparedBy() {
		return preparedBy;
	}
	public void setPreparedBy(String preparedBy) {
		this.preparedBy = preparedBy;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getVerifiedBy() {
		return verifiedBy;
	}
	public void setVerifiedBy(String verifiedBy) {
		this.verifiedBy = verifiedBy;
	}
	public int getIssueNum() {
		return issueNum;
	}
	public void setIssueNum(int issueNum) {
		this.issueNum = issueNum;
	}
	public int getRevNum() {
		return revNum;
	}
	public void setRevNum(int revNum) {
		this.revNum = revNum;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public int getWorkOrderYear() {
		return workOrderYear;
	}
	public void setWorkOrderYear(int currentYear) {
		this.workOrderYear = currentYear;
	}
	public int getWorkOrderSerial() {
		return workOrderSerial;
	}
	public void setWorkOrderSerial(int workOrderSerial) {
		this.workOrderSerial = workOrderSerial;
	}
	public int getWorkOrderMonth() {
		return workOrderMonth;
	}
	public void setWorkOrderMonth(int workOrderMonth) {
		this.workOrderMonth = workOrderMonth;
	}
	
	@Override
	public String toString() {
		return "Work_order [id=" + id + ", workOrderNum=" + workOrderNum + ", workOrderDate=" + workOrderDate
				+ ", polfNum=" + polfNum + ", polfQty=" + polfQty + ", customerPoOrder=" + customerPoOrder
				+ ", customerName=" + customerName + ", productName=" + productName + ", custArtworkRef="
				+ custArtworkRef + ", schemeAuthority=" + schemeAuthority + ", chipType=" + chipType + ", cardType="
				+ cardType + ", electricalInfo=" + electricalInfo + ", graphicalInfo=" + graphicalInfo + ", foilInfo="
				+ foilInfo + ", binNumber=" + binNumber + ", persoBatchName=" + persoBatchName + ", batchQty="
				+ batchQty + ", numOfEnvlopes=" + numOfEnvlopes + ", numOFCarriers=" + numOfCarriers + ", numOfCards="
				+ numOfCards + ", remarks=" + remarks + ", preparedBy=" + preparedBy + ", dateTime=" + dateTime
				+ ", verifiedBy=" + verifiedBy + ", issueNum=" + issueNum + ", revNum=" + revNum + ", issueDate="
				+ issueDate + ", workOrderYear=" + workOrderYear + ", workOrderSerial=" + workOrderSerial
				+ ", workOrderMonth=" + workOrderMonth + "]";
	}		
}
