package school.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.model.business.Course;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseDTO {
    private Long id;
    private String name;
    private int semester;
    private int credits;

    public CourseDTO(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        this.semester = course.getSemester();
        this.credits = course.getCredits();
    }
}
