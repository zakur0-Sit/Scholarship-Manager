package school.model.business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String CNP;
    private String address;
    private String phoneNumber;
    private String email;
    private LocalDate birthDate;
    private String country;
    private int year;
    private String group;

    @OneToMany(mappedBy = "student")
    private List<Grade> grades;

    @OneToMany(mappedBy = "student")
    private List<Beneficiary> beneficiaries;

    public Object getFullName() {
        return firstName + " " + lastName;
    }

    public Object getDateOfBirth() {
        return birthDate;
    }
}
