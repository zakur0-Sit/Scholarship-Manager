package school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import school.model.business.Student;
import school.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public Page<Student> getAllStudents(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection,
            @RequestParam(defaultValue = "") String filterBy,
            @RequestParam(defaultValue = "") String filterValue) {
        return studentService.getAll(pageNo, pageSize, sortBy, sortDirection, filterBy, filterValue);
    }

  // public void callStudentStatusFunction(int year){
    //   studentService.callStudentStatusFunction(year);
  // }
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student existingStudent = studentService.getStudentById(id);
        if (existingStudent != null) {
            student.setId(id);
            return studentService.saveStudent(student);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
