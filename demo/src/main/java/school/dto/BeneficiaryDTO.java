package school.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.model.business.Beneficiary;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BeneficiaryDTO {
    private Long id;
    private Long studentId;
    private Long scholarshipId;

    public BeneficiaryDTO(Beneficiary beneficiary) {

        this.id = beneficiary.getId();
        this.studentId = beneficiary.getStudent().getId();
        this.scholarshipId = beneficiary.getScholarship().getId();
    }
}
