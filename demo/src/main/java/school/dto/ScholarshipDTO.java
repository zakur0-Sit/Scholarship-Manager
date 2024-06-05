package school.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.model.business.Scholarship;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ScholarshipDTO {
    private Long id;
    private String type;
    private double totalAmount;

    public ScholarshipDTO(Scholarship scholarship) {
        this.id = scholarship.getId();
        this.type = scholarship.getType();
        this.totalAmount = scholarship.getValue();
    }
}
