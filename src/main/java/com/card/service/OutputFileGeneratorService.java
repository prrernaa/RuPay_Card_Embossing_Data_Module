package com.card.service;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.springframework.stereotype.Service;

import com.card.dto.InputFileDto;
import com.card.dto.OutputFileDto;


@Service
public class OutputFileGeneratorService {

	private OutputFileDto outputDto; 
	
	///x0D/x0A
	//private String endLine="";
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
	 String date = formatter.format(new Date());
	//000001$6077 9102 0000 2488#07/23 07/28     *745&TEST1                    @040[%B6077910200002488^TEST1                    /^2807620198A00000000000000000000?;6077910200002488=28076201980000000000?;5F25=230701;57=6077910200002488D28076200400000000000F;9F1F=303430413030303030303030303030303030303030303030]

	String result="";
	String excelResult="";
	String cardlist="";
	
	public String populateValue(InputFileDto inputFileDto ) 
	{	outputDto = new OutputFileDto();
		outputDto.setRecordSeqNo(inputFileDto.getSequenceNumber());
		outputDto.setEmbossingCardNo(inputFileDto.getCardNumber());
		outputDto.setStartDate(inputFileDto.getIssueDate().substring(2)+"/"+inputFileDto.getIssueDate().substring(0, 2));
		outputDto.setExpiryDate(inputFileDto.getExpireDate().substring(2)+"/"+inputFileDto.getExpireDate().substring(0, 2));
		outputDto.setCvv2(inputFileDto.getCvv2());
		
		// Get the name from inputFileDto
        //String name = inputFileDto.getName();
        
        // Check if the length is less than 26
//        if (name.length() < 25) {
//            // Calculate the number of spaces needed
//            int spacesToAdd = 25 - name.length();
//            
//            // Append the spaces to the end of the string
//            StringBuilder paddedName = new StringBuilder(name);
//            for (int i = 0; i < spacesToAdd; i++) {
//                paddedName.append(" ");
//            }
//            
//            // Now paddedName will have a length of 26
//            outputDto.setCardHolderName(paddedName.toString());
//        } else {
//            // The name is already 26 characters or longer
//        	outputDto.setCardHolderName(name);
//        }
		
		outputDto.setCardHolderName(inputFileDto.getName());
		outputDto.setIcvvValue(inputFileDto.getICVV());
		outputDto.setTrackOne(inputFileDto.getTrack1());
		outputDto.setTrackTwo(inputFileDto.getTrack2());
		outputDto.setIssueDate(inputFileDto.getIssueDate()+"01");
		
		result = outputDto.getRecordSeqNo()+outputDto.getEmbossingCardNoIdentifier()+outputDto.getEmbossingCardNo()+outputDto.getDateIdentifier1()+outputDto.getStartDate()
		+outputDto.getFiller()+outputDto.getExpiryDate()+outputDto.getFiller()+outputDto.getLastFourDigit()+outputDto.getDateIdentifier2()+outputDto.getCvv2()+outputDto.getNameIdentifier()
		+outputDto.getCardHolderName()+outputDto.getIcvvIdentifier()+outputDto.getIcvvValue()+outputDto.getSmartCardDataStarter()
		+outputDto.getTrackOne()+outputDto.getTrackTwo()+outputDto.getOverrideTags()+outputDto.getApplicationeffectiveDate()
		+outputDto.getIssueDate()+outputDto.getOverrideTags()+outputDto.getTrackTwoEquivalentData()+inputFileDto.getTrackTwoEquivalentDataValue()+outputDto.getOverrideTags()+outputDto.getTrackOneDiscretionaryData()
		+inputFileDto.getTrackOneDiscretionaryDataValue()+outputDto.getSmartCardDataEnder();
		
		
		
		
		return result;
	}
	public String excelPopulateValue(InputFileDto inputFileDto)
	{	outputDto = new OutputFileDto();
		outputDto.setMailerSeqNo(inputFileDto.getMailerSerialNumber());
		outputDto.setMailerRecieverName(inputFileDto.getMailerName());
		outputDto.setCardRefNumber(inputFileDto.getCardReferenceNumber());
		outputDto.setShipmentId(inputFileDto.getCourierShipmentId());
		outputDto.setAddressone(inputFileDto.getAddress1());
		outputDto.setAddresstwo(inputFileDto.getAddress2());
		outputDto.setAddressthree(inputFileDto.getAddress3());
		outputDto.setAddressfour(inputFileDto.getAddress4());
		outputDto.setAddressfive(inputFileDto.getAddress5());
		outputDto.setZipCode(inputFileDto.getPinCode());
		outputDto.setContactNumber(inputFileDto.getMobileNumber());
		outputDto.setAirWayBillNumber(inputFileDto.getAwbNumber());
		outputDto.setCourierService(inputFileDto.getCourierServiceProvider());
		
		
		
		excelResult=outputDto.getMailerSeqNo()+outputDto.getExcelSeparator()+date+outputDto.getExcelSeparator()+outputDto.getCardRefNumber()+outputDto.getExcelSeparator()
		+outputDto.getShipmentId()+outputDto.getExcelSeparator()+outputDto.getMailerRecieverName()+outputDto.getExcelSeparator()+outputDto.getAddressone()+outputDto.getExcelSeparator()+outputDto.getAddresstwo()+outputDto.getExcelSeparator()
		+outputDto.getAddressthree()+outputDto.getExcelSeparator()+outputDto.getAddressfour()+outputDto.getExcelSeparator()+outputDto.getAddressfive()+outputDto.getExcelSeparator()
		+outputDto.getZipCode()+outputDto.getExcelSeparator()+outputDto.getContactNumber()+outputDto.getExcelSeparator()+outputDto.getAirWayBillNumber()
		+outputDto.getExcelSeparator()+outputDto.getCourierService();
		return excelResult;
	}
	
	public String cardPackageListValue(InputFileDto inputFileDto)
	{	outputDto = new OutputFileDto();
		outputDto.setPackageSeqNo(inputFileDto.getPackagesequence());
		outputDto.setFullNamePackage(inputFileDto.getFullnameString());
		outputDto.setFirstsixdigits(inputFileDto.getFirstSixDigit());
		outputDto.setLastpart(inputFileDto.getLastfourdigit());
		
		
		
		cardlist=outputDto.getPackageSeqNo()+outputDto.getExcelSeparator()+outputDto.getFirstsixdigits()+outputDto.getMaskingString()+outputDto.getFiller()+outputDto.getLastpart()+outputDto.getExcelSeparator()+outputDto.getFullNamePackage();
		return cardlist;
	}
	
}
