package ee.taltech.iti0202.springboot.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;

/**
 * Entity class representing an employee in the database.
 */
@Getter
@Setter
@Entity
//@DTO
@Table(name = "employee")
public class Employee {

    /**
     * Unique ID of the employee (primary key).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * First name of the employee.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * Last name of the employee.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Email address of the employee.
     */
    @Column(name = "email")
    private String email;

    /**
     * Company where the employee works.
     */
    @Column(name = "company")
    private String company;

//    @Column(name = "credit")
//    private String credit;
}
