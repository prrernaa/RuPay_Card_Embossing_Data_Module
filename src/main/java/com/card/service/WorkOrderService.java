package com.card.service;

import org.springframework.stereotype.Service;

import com.card.entity.WorkOrder;

@Service
public interface WorkOrderService {
	public WorkOrder create(WorkOrder workOrderData);

}
