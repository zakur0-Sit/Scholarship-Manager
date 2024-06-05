package school.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.model.business.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentDTO {
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
    private List<GradeDTO> grades;
    private List<BeneficiaryDTO> beneficiaries;

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.CNP = student.getCNP();
        this.address = student.getAddress();
        this.phoneNumber = student.getPhoneNumber();
        this.email = student.getEmail();
        this.birthDate = student.getBirthDate();
        this.country = student.getCountry();
        this.year = student.getYear();
        this.group = student.getGroup();
        this.grades = student.getGrades().stream()
                .map(GradeDTO::new)
                .collect(Collectors.toList());
        this.beneficiaries = student.getBeneficiaries().stream()
                .map(BeneficiaryDTO::new)
                .collect(Collectors.toList());
    }
}
