package com.ticketrising1.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ticketrising1.entity.Employee;



public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

	List<Employee> findByDeptNum(int deptNum);

}
