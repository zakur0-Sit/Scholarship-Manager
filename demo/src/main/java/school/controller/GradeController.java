package school.controller;

import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import school.model.business.Grade;
import school.model.business.Student;
import school.payload.request.LoginRequest;
import school.repository.GradeRepository;
import school.service.CustomUserService;
import school.service.GradeService;
import school.service.StudentService;

import java.util.List;


@RestController
@RequestMapping("/grades")
public class GradeController {
    @Autowired
    private GradeService gradeService;
    @Autowired
    private CustomUserService userService;

    private LoginRequest loginRequest;
    @Autowired
    private StudentService studentService;
    @Autowired
    private GradeRepository gradeRepository;

    @GetMapping
    public Page<Grade> getAllGrades(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        return gradeService.getAll(pageNo, pageSize, sortBy, sortDirection);
    }

    @GetMapping("/{id}")
    public Grade getGradeById(@PathVariable Long id) {
        return gradeService.getGradeById(id);
    }

    @PostMapping
    public Grade createGrade(@RequestBody Grade grade) {
        return gradeService.saveGrade(grade);
    }

    @PutMapping("/{id}")
    public Grade updateGrade(@PathVariable Long id, @RequestBody Grade grade) {
        Grade existingGrade = gradeService.getGradeById(id);
        if (existingGrade != null) {
            grade.setId(id);
            return gradeService.saveGrade(grade);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
    }

/*
    @GetMapping("/profile")
    public String profile(Model model) {
       // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = loginRequest.getUsername();
        Long studentId = userService.findUserIdByUsername(username);

        Student student = studentService.findById(studentId);

        model.addAttribute("fullName", student.getFullName());
        model.addAttribute("id", student.getId());
        model.addAttribute("email", student.getEmail());
        model.addAttribute("phoneNumber", student.getPhoneNumber());
        model.addAttribute("dateOfBirth", student.getDateOfBirth());
        model.addAttribute("county", student.getCountry());
        model.addAttribute("address", student.getAddress());
        model.addAttribute("year", student.getYear());
        model.addAttribute("group", student.getGroup());
        return "profile";
    }
*/
}
