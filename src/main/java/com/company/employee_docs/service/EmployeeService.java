package com.company.employee_docs.service;

import com.company.employee_docs.entity.Employee;
import com.company.employee_docs.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    //Saves a new employee or updates an existing one.
    //Uses Spring Data JPA's built-in save() method.
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    //Retrieves all employees from the database.
    //Returns a List<Employee>.
    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    //Returns the employee with the specified id, wrapped in an Optional to handle the case where the employee may not exist.
    public Optional<Employee> getEmployeeById(Long id){
        return employeeRepository.findById(id);
    }

    //Deletes the employee from the database using its id.
    public void deleteEmployeeById(Long id){
        employeeRepository.deleteById(id);
    }

    // ✅ Update Employee by ID
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (optionalEmployee.isPresent()) {
            Employee existingEmployee = optionalEmployee.get();

            // Update fields
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            // Add other fields if you have them

            return employeeRepository.save(existingEmployee);
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }
}
