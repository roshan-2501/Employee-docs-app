package com.company.employee_docs.controller;

import com.company.employee_docs.entity.Employee;
import com.company.employee_docs.repository.EmployeeRepository;
import com.company.employee_docs.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    //@PostMapping → Handles HTTP POST request (used for creating new records).
    //@RequestBody → Converts incoming JSON into an Employee object.
            //Calls saveEmployee() method from EmployeeService to save it in the DB.
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployee();
    }

    //@PathVariable binds {id} from the URL path to the method parameter.
    //Fetches a single employee by ID using the service layer.
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
        return employeeService.updateEmployee(id,employeeDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
    }
}
