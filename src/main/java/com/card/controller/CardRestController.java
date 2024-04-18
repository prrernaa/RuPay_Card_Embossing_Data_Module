package com.card.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.card.dto.InputFileDto;
import com.card.service.InputFileProcessingService;
import com.card.service.OutputFileGeneratorService;

@RestController
public class CardRestController {
	
	@Autowired
	private OutputFileGeneratorService outputFileGeneratorService;
	
	@Autowired
	private InputFileProcessingService inputFileProcessingService;
	
	
	@GetMapping("/hello")
	public String test() {
		return "Thank You";
	}
	
	@GetMapping("/generateOutput")
	public String generateOutputFile()
	{
		String inputString = "000001#                          *8175 5100 0090 6966#           28112311   XShridhar Lanje     >                          \"%B8175510000906966^Shridhar Nilkanthrao Lanj/^2811620544A00000000000000000000?  875 ;8175510000906966=28116205440000000000? `8175510000906966`964`NEW`      |*UAX0000578020*|*438934175*|*000001*|Shridhar Nilkanthrao Lanje";
		
		InputFileDto inputFileDto = inputFileProcessingService.processFile(inputString);
		System.out.println(inputFileDto.toString());
		System.out.println(outputFileGeneratorService.populateValue(inputFileDto));
		return "File Generated!!!";
	}
}
