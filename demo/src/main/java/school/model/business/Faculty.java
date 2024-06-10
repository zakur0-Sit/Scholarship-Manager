package school.model.business;
/*dto.FacultyDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;
import java.util.Random;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "faculties")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long studentCount;
    private String sensitiveData;

    @ManyToMany
    @JoinTable(name = "faculties_students", schema = "pa_spring_schema_demo",
            joinColumns = @JoinColumn(name = "faculty_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    public Faculty(String name, Long studentCount) {
        this.name = name;
        this.studentCount = studentCount;
        this.sensitiveData = "Sensitive-Data-" + getRandomInt();
    }

    public Faculty(FacultyDTO facultyDTO) {
        this.name = facultyDTO.getName();
        this.studentCount = facultyDTO.getStudentCount();
        this.sensitiveData = "Sensitive-Data-" + getRandomInt();
    }

    private static int getRandomInt() {
        return new Random().nextInt(9000000 - 1000) + 1000;
    }

}*/