package school;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import school.controller.StudentController;
import school.service.BeneficiaryService;
import school.service.CourseService;
import school.service.GradeService;
import school.service.StudentService;


@SpringBootApplication
@SecurityScheme(
        name = "bearerAuth",
        scheme = "bearer",
        bearerFormat = "JWT",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
public class AppStarter {

    public static void main(String[] args) {
        //StudentService studentService = new StudentService();
        //studentService.callStudentStatusFunction(2);

        //BeneficiaryService beneficiaryService = new BeneficiaryService();
        //beneficiaryService.addBeneficiary(17,2);

       //GradeService gradeService = new GradeService();
       //gradeService.update_grades(1,5);
        //gradeService.averageGradesPerObject(12);

        //CourseService courseService = new CourseService();
        //courseService.getOutstandingStudents();
        //SpringApplication.run(AppStarter.class, args);
    }
}
