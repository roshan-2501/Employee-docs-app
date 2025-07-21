package com.company.employee_docs.repository;

import com.company.employee_docs.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// JpaRepository<Employee, Long>	This tells Spring Data JPA:
// Employee → is the entity it manages,
// Long → is the type of the primary key (ID).
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
