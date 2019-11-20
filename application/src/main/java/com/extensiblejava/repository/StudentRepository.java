package com.extensiblejava.repository;

import java.util.List;

// CrudRepository.class in
// org\springframework\data\repository in 
// .m2\repository\org\springframework\data\spring-data-commons\2.1.6.RELEASE\spring-data-commons-2.1.6.RELEASE.jar
import org.springframework.data.repository.CrudRepository;


import org.springframework.stereotype.Repository;

import com.extensiblejava.bill.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    
    List<Student> findByName(String name);
    
}
