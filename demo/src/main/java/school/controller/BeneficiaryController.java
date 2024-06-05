package school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import school.model.business.Beneficiary;
import school.service.BeneficiaryService;

@RestController
@RequestMapping("/beneficiaries")
public class BeneficiaryController {
    @Autowired
    private BeneficiaryService beneficiaryService;

    @GetMapping
    public Page<Beneficiary> getAllBeneficiaries(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        return beneficiaryService.getAll(pageNo, pageSize, sortBy, sortDirection);
    }

    @GetMapping("/{id}")
    public Beneficiary getBeneficiaryById(@PathVariable Long id) {
        return beneficiaryService.getBeneficiaryById(id);
    }

    @PostMapping
    public Beneficiary createBeneficiary(@RequestBody Beneficiary beneficiary) {
        return beneficiaryService.saveBeneficiary(beneficiary);
    }

    @PutMapping("/{id}")
    public Beneficiary updateBeneficiary(@PathVariable Long id, @RequestBody Beneficiary beneficiary) {
        Beneficiary existingBeneficiary = beneficiaryService.getBeneficiaryById(id);
        if (existingBeneficiary != null) {
            beneficiary.setId(id);
            return beneficiaryService.saveBeneficiary(beneficiary);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteBeneficiary(@PathVariable Long id) {
        beneficiaryService.deleteBeneficiary(id);
    }
}
