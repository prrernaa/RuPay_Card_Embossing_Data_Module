package com.card.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.card.entity.WorkOrder;
import com.card.repo.WorkOrderRepo;
import com.card.service.WorkOrderService;

@Service

public class WorkOrderServiceImpl implements WorkOrderService {
	
	@Autowired
    private WorkOrderRepo workOrderRepo;

	
	@Override
	public WorkOrder create(WorkOrder workOrderData) {
//		// Implementation here
        Integer latestSerial = workOrderRepo.findMaxWorkOrderSerial();
        int newSerial = (latestSerial != null) ? latestSerial + 1 : 1;
        YearMonth currentYearMonth = YearMonth.now();
        int currentYear = currentYearMonth.getYear();
        int currentMonth= currentYearMonth.getMonthValue();
        String workOrderNum = String.format("%04d/%02d-%02d", currentYear, currentMonth, newSerial);
		
        WorkOrder workOrder = new WorkOrder();
        
     // Get the current time in milliseconds
        long millis = System.currentTimeMillis();

        // Instantiate java.sql.Date using the milliseconds
        java.sql.Date currentDate = new java.sql.Date(millis);
        workOrderData.setWorkOrderDate(currentDate);       
        workOrderData.setIssueDate("15-03-2023");
        
        
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm a");
        String formattedDateTime = dateTimeFormat.format(currentDate);
        workOrderData.setDateTime(formattedDateTime);      
  
       
        workOrderData.setWorkOrderSerial(newSerial);
        workOrderData.setWorkOrderYear(currentYear);
        workOrderData.setWorkOrderMonth(currentMonth);
        workOrderData.setWorkOrderNum(workOrderNum);
        workOrderData.setNumOfEnvlopes("NA");
        workOrderData.setNumOFCarriers("NA");

        
        return workOrderRepo.save(workOrderData);
	}
}
        
