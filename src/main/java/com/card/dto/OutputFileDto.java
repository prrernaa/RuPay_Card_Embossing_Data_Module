package com.card.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to generate getter, setter, toString, equals, and hashCode methods
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate an all-argument constructor
public class OutputFileDto {
	
	private String recordSeqNo;	
	
	//$
	private String embossingCardNoIdentifier ="$";	
	private String embossingCardNo;	
	
	//#
	private String DateIdentifier1 ="#";
	private String startDate;
	
	//space
	private String filler =" ";	
	private String expiryDate;	
	
	//if no data then need to add 4 space as output
	private String lastFourDigit="    ";
	
	//*
	private String dateIdentifier2 ="*";	
	private String cvv2;	
	
	//&
	private String nameIdentifier ="&";	
	private String cardHolderName;
	
	//@
	private String icvvIdentifier ="@";
	
	//@ for Excel Separation
	private String excelSeparator="@";	
	
	// for Masking
	private String maskingString="XX XXXX";	
	private String icvvValue;
	private String smartCardDataStarter = "[";
	private String trackOneStartSentinel = "%";
	private String trackOne;
	private String trackOneEndSentinel = "?";
	private String trackTwoStartSentinel = ";";
	private String trackTwo;
	private String trackTwoEndSentinel = "?";
	private String overrideTags= ";";
	private String applicationeffectiveDate="5F25=";
	private String trackTwoEquivalentData="57=";
	private String trackOneDiscretionaryData="9F1F=";
	private String smartCardDataEnder="]";
	private String issueDate;
	private String trackTwoEquivalentData1;
	private String trackOneDiscretionaryData1;
	
	//mailer variables
	private String mailerSeqNo;
	private String mailerRecieverName;
	private String cardRefNumber;
	private String ShipmentId;
	private String addressone;
	private String addresstwo;
	private String addressthree;
	private String addressfour;
	private String addressfive;
	private String zipCode;
	private String contactNumber;
	private String courierService;
	private String airWayBillNumber;
	
	//Variable For Card Packaging
	private String fullNamePackage;
	private String firstsixdigits;
	private String lastpart;
	private String packageSeqNo;
}
