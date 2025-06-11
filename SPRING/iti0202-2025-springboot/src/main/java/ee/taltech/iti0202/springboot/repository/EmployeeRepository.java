package ee.taltech.iti0202.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for accessing Employee data from the database.
 * <p>
 * Extends JpaRepository to provide basic CRUD operations:
 * Create, Read, Update, Delete.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    /**
     * Finds an employee by email, ignoring case.
     *
     * @param email the email to search by
     * @return an Optional containing the found employee, or empty if not found
     */
    Optional<Employee> findByEmailIgnoreCase(String email);

    /**
     * Finds all employees working in the specified company.
     *
     * @param company the company name
     * @return a list of employees in that company
     */
    List<Employee> findAllByCompany(String company);
}
