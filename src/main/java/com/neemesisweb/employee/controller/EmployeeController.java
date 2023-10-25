package com.neemesisweb.employee.controller;

// import java.util.HashMap;
import java.util.List;

// import org.hibernate.mapping.Map;
// import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neemesisweb.employee.entities.Employee;
import com.neemesisweb.employee.exception.ResourcesNotFoundException;
import com.neemesisweb.employee.repositories.EmployeeRepository;

@CrossOrigin
@RestController
@RequestMapping("api/v1/")
public class EmployeeController{

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeRepository.save(employee); 
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Employee not exist with id: " + id) );
        return ResponseEntity.ok(employee);
    }

    // @PutMapping("/employees/{id}")
    //@PutMapping(value="/{id}", consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourcesNotFoundException("Employee not exist with id: " + id));
        
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());

        Employee employeeUpdated = employeeRepository.save(employee);
        return ResponseEntity.ok(employeeUpdated);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id) {
        try {
            if (employeeRepository.existsById(id)) {
                employeeRepository.deleteById(id);
                return ResponseEntity.ok().build(); // Réponse 200 OK en cas de succès
            } else {
                return ResponseEntity.notFound().build(); // Réponse 404 Not Found si l'employé n'existe pas
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Réponse 500 Internal Server Error en cas d'erreur
        }
    }

}

