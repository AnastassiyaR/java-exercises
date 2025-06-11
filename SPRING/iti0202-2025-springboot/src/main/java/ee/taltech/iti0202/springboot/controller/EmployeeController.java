package ee.taltech.iti0202.springboot.controller;

import ee.taltech.iti0202.springboot.repository.Employee;
import ee.taltech.iti0202.springboot.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;
import java.util.Optional;


// Generates a constructor with required arguments (i.e., final fields and fields with @NonNull)
// In this case, it's used to automatically inject EmployeeService via constructor injection
@RequiredArgsConstructor

// Marks this class as a REST controller, meaning it handles HTTP requests and returns JSON/XML responses
@RestController

/**
 * Controller class for managing Employee-related HTTP requests.
 *
 * Example of usage: GET http://localhost:8080/employees
 */
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * Retrieves a list of all employees.
     *
     * @return List of all employees
     */
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    /**
     * Retrieves a list of employees working in the specified company.
     *
     * @param company the company name to filter employees by
     * @return List of employees working in the specified company
     */
    @GetMapping("/employee")
    public List<Employee> getEmployeesByCompany(@RequestParam String company) {
        return employeeService.findAllByCompany(company);
    }

    /**
     * Retrieves an employee by their ID.
     *
     * @param id the ID of the employee
     * @return Optional containing the employee if found, otherwise empty
     */
    @GetMapping("/employee/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    /**
     * Adds a new employee to the database.
     *
     * @param employee the employee object to be added
     * @return success message if added, or error message if email already exists
     */
    @PostMapping("/employee/add")
    public String addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    /**
     * Deletes an employee from the database by ID.
     *
     * @param id the ID of the employee to be deleted
     * @return success or error message based on deletion result
     */
    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

    /**
     * Updates an existing employee's information by ID.
     *
     * @param id       the ID of the employee to be updated
     * @param employee the employee object containing updated data
     * @return success or error message based on update result
     */
    @PutMapping("/employee/{id}")
    public String updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }
}
