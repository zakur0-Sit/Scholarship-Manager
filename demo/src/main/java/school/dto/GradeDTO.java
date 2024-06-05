package school.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.model.business.Grade;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GradeDTO {
    private Long id;
    private double value;
    private Long studentId;
    private Long courseId;

    public GradeDTO(Grade grade) {
        this.id = grade.getId();
        this.value = grade.getValue();
        this.studentId = grade.getStudent().getId();
        this.courseId = grade.getCourse().getId();
    }
}
