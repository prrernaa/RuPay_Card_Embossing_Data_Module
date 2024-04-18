package com.card.service;

import org.springframework.stereotype.Service;
import com.card.dto.InputFileDto;

@Service
public class InputFileProcessingService {

	private InputFileDto inputFileDto;
	
	public InputFileDto processFile(String inputString) 
	{	
		inputFileDto = new InputFileDto();
		
		inputFileDto.setSequenceNumber(inputString.substring(0, 6));
		inputFileDto.setCardNumber(inputString.substring(34, 53));
		inputFileDto.setExpireDate(inputString.substring(65, 69));
		inputFileDto.setIssueDate(inputString.substring(69,73));
		inputFileDto.setName(inputString.substring(77, 95));
		inputFileDto.setTrack1(inputString.substring(124, 202));
		inputFileDto.setCvv2(inputString.substring(204, 207));
		inputFileDto.setTrack2(inputString.substring(208, 247));
		inputFileDto.setAccountNumber(inputString.substring(249, 265));
		inputFileDto.setICVV(inputString.substring(266, 269));
		inputFileDto.setCardIssueCode(inputString.substring(270, 273));
		inputFileDto.setTrackOneDiscretionaryDataValue(fetchTrackOneDiscretionaryDataVal(inputString));		
		inputFileDto.setTrackTwoEquivalentDataValue(trackTwoEquivalentData1(inputString));
		
		//Welcome mailer data
		inputFileDto.setCardReferenceNumber(inputString.substring(282, 295));
		inputFileDto.setCourierShipmentId(inputString.substring(298, 307));
		inputFileDto.setMailerSerialNumber(inputString.substring(310, 316));
		inputFileDto.setMailerName(inputString.substring(318, 418));
		inputFileDto.setAddress1(inputString.substring(419, 519));
		inputFileDto.setAddress2(inputString.substring(520, 620));
		inputFileDto.setAddress3(inputString.substring(621, 671));
		inputFileDto.setAddress4(inputString.substring(672, 692));
		inputFileDto.setAddress5(inputString.substring(693, 713));
		inputFileDto.setPinCode(inputString.substring(714, 720));
		inputFileDto.setMobileNumber(inputString.substring(720, 737));
		inputFileDto.setCourierServiceProvider(inputString.substring(738, 788));
		inputFileDto.setAwbNumber(inputString.substring(790, 802));
		inputFileDto.setFullnameString(inputString.substring(143, 168));
		inputFileDto.setFirstSixDigit(inputString.substring(34, 41));
		inputFileDto.setLastfourdigit(inputString.substring(49, 53));
		inputFileDto.setPackagesequence(inputString.substring(0, 6));
		
		return inputFileDto;
	}
	
	private String fetchTrackOneDiscretionaryDataVal(String inputString) 
	{
		String result = "";
		
		result = inputString.substring(266, 269)+inputString.substring(180, 201);
		System.out.println("9F1F="+result);
		result = result.replace("A", "41");
		System.out.println("9F1F="+result);
		
		// Create a StringBuilder to efficiently manipulate the string
        StringBuilder modifiedStringBuilder = new StringBuilder();
        int j =0;
        
        for (int i = 0; i < 48; i++) {
            
      	if((i%2)==0){
      		if(i!=6){
      		modifiedStringBuilder.append('3');
      		}else {
      			modifiedStringBuilder.append(result.charAt(j));
      			j++;
      		}
      	}else {
      		modifiedStringBuilder.append(result.charAt(j));
      	    j++;
      	}
      }

        // Convert StringBuilder back to String
        result = modifiedStringBuilder.toString();
        System.out.println("9F1F="+result);
		
		return result;
	}
	
	
	private String trackTwoEquivalentData1(String inputString) 
	{
		String result = "";
		result = inputString.substring(209, 247);
		System.out.println("57="+result);
		result = result.replace("=", "D");
		result = result.replace("?", "F");
		System.out.println("57="+result);
		String temp = inputString.substring(233, 236);
		System.out.println("57=CVV: "+temp);
		result = result.replace(temp, inputFileDto.getICVV());
		System.out.println("57="+result);
		
		return result;
	}
}
