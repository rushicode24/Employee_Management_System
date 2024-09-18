package com.Rushikesh.Employee_System.Repository;

import com.Rushikesh.Employee_System.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Employee,Integer>
{

    
}
