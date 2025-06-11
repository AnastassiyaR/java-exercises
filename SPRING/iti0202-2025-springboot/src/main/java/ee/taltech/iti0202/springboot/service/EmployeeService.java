package ee.taltech.iti0202.springboot.service;

import ee.taltech.iti0202.springboot.repository.Employee;
import ee.taltech.iti0202.springboot.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class handling business logic for Employee operations.
 * Uses EmployeeRepository to interact with the database.
 */
@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    /**
     * Retrieves all employees.
     *
     * @return list of all employees
     */
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Finds all employees working at the specified company.
     *
     * @param company company name
     * @return list of employees at the company
     */
    public List<Employee> findAllByCompany(String company) {
        return employeeRepository.findAllByCompany(company);
    }

    /**
     * Finds an employee by their ID.
     *
     * @param id employee ID
     * @return optional employee
     */
    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    /**
     * Adds a new employee if the email doesn't already exist.
     *
     * @param employee new employee data
     * @return status message
     */
    public String addEmployee(Employee employee) {
        Optional<Employee> emp = employeeRepository.findByEmailIgnoreCase(employee.getEmail());
        if (emp.isPresent()) return "This email is already in database or something went wrong!";

        employeeRepository.save(employee);
        return "Employee added to database";
    }

    /**
     * Deletes an employee by ID if exists.
     *
     * @param id employee ID
     * @return status message
     */
    public String deleteEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isEmpty()) return "No matching ID found in database!";

        employeeRepository.deleteById(id);
        return "Employee deleted";
    }

    /**
     * Updates an employee's data by ID.
     * Checks email uniqueness if email is updated.
     *
     * @param id employee ID
     * @param newData employee data with updates
     * @return status message
     */
    public String updateEmployee(Long id, Employee newData) {
        Optional<Employee> employeeOpt = employeeRepository.findById(id);
        if (employeeOpt.isEmpty()) return "Cannot overwrite employee data!";

        Employee employee = employeeOpt.get();

        if (newData.getEmail() != null && !newData.getEmail().equalsIgnoreCase(employee.getEmail())) {
            Optional<Employee> existingEmail = employeeRepository.findByEmailIgnoreCase(newData.getEmail());
            if (existingEmail.isPresent()) return "Cannot overwrite employee data!";
            employee.setEmail(newData.getEmail());
        }

        if (newData.getFirstName() != null) employee.setFirstName(newData.getFirstName());
        if (newData.getLastName() != null) employee.setLastName(newData.getLastName());
        if (newData.getCompany() != null) employee.setCompany(newData.getCompany());

        employeeRepository.save(employee);
        return "Employee data overwritten.";
    }
}
