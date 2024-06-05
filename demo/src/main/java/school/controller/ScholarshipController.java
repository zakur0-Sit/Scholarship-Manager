package school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import school.model.business.Scholarship;
import school.service.ScholarshipService;

@RestController
@RequestMapping("/scholarships")
public class ScholarshipController {
    @Autowired
    private ScholarshipService scholarshipService;

    @GetMapping
    public Page<Scholarship> getAllScholarships(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        return scholarshipService.getAll(pageNo, pageSize, sortBy, sortDirection);
    }

    @GetMapping("/{id}")
    public Scholarship getScholarshipById(@PathVariable Long id) {
        return scholarshipService.getScholarshipById(id);
    }

    @PostMapping
    public Scholarship createScholarship(@RequestBody Scholarship scholarship) {
        return scholarshipService.saveScholarship(scholarship);
    }

    @PutMapping("/{id}")
    public Scholarship updateScholarship(@PathVariable Long id, @RequestBody Scholarship scholarship) {
        Scholarship existingScholarship = scholarshipService.getScholarshipById(id);
        if (existingScholarship != null) {
            scholarship.setId(id);
            return scholarshipService.saveScholarship(scholarship);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteScholarship(@PathVariable Long id) {
        scholarshipService.deleteScholarship(id);
    }
}
