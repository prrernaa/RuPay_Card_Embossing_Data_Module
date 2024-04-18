package com.card.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to generate getter, setter, toString, equals, and hashCode methods
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate an all-argument constructor
public class InputFileDto {
	
	private String sequenceNumber;
	private String separator1 = "#";
	private String blanks;
	private String separator2 = "*";
	private String cardNumber;
	private String expireDate;
	private String issueDate;
	private String name;
	private String track1;
	private String cvv2;
	private String track2;
	private String accountNumber;
	private String iCVV;
	private String cardIssueCode;
	private String trackOneDiscretionaryDataValue;
	private String trackTwoEquivalentDataValue;
	
	//variables for Welcome Mailer
	private String mailerSerialNumber;
	private String mailerName;
	private String cardReferenceNumber;
	private String courierShipmentId;
	private String address1;
	private String address2;
	private String address3;
	private String address4;
	private String address5;
	private String pinCode;
	private String mobileNumber;
	private String courierServiceProvider;
	private String awbNumber;
	
	//Variable for Card Packing list
	private String firstSixDigit;
	private String lastfourdigit;
	private String fullnameString;
	private String packagesequence;
	
}
