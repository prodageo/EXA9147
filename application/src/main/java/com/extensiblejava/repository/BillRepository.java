package com.extensiblejava.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.extensiblejava.bill.Bill;

@Repository
public interface BillRepository extends CrudRepository<Bill, Long> {
    
    List<Bill> findByName(String name);
    
}
