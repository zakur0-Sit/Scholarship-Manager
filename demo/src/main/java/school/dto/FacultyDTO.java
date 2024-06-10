package school.dto;
/*
import school.model.business.Faculty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class FacultyDTO {

    private Long id;
    private String name;
    private Long studentCount;

    private List<StudentDTO> students;

    //private transient List<LinkDTO> links;

    public FacultyDTO(Long universityId, Faculty faculty) {
        this.id = faculty.getId();
        this.name = faculty.getName();
        this.studentCount = faculty.getStudentCount();
        this.students = faculty.getStudents().stream()
                .map(student -> new StudentDTO(student))
                .collect(Collectors.toList());
       // decorateWithLinks(universityId);
    }

   /* private void decorateWithLinks(Long univId) {
        this.links = students.stream()
                .map(student -> new LinkDTO(
                        "/api/v1/universities" + univId + "/faculties/" + this.getId() + "/students/" + student.getId(),
                        "students", "GET"))
                .collect(Collectors.toList());
    }

}*/