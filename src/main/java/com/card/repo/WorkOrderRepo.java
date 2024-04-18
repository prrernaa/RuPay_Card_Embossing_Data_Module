package com.card.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.card.entity.WorkOrder;

@Repository
public interface WorkOrderRepo extends JpaRepository<WorkOrder, Integer> {
	
	@Query(value = "SELECT MAX(serialNum.workOrderSerial) FROM WorkOrder serialNum")
	Integer findMaxWorkOrderSerial();
	
}

