package school.controller;
/*
import school.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/faculties")
public class FacultyController {

    @Autowired
    FacultyService facultyService;

    /**
     * TODO: Homework
     * First of all ... there is a lot of unknown code in here, in the FacultyService and FacultySortedAndPagedRepository
     * 1. Try to understand it and ask me on Discord anything you don't understand
     * 2. I've already created an Enum for SortOrder that handles the "sortBy" and "sortDirection" params, but the filtering
     * is based on if-else.
     * 2. a) Create an enum or a "functionMap" that is capable of ingesting an "filterBy" String and returning the right method
     * call from the repository using the "filterValue" param.
     * 2. b) What about trying to sort them by studentCount? How you will extend this?
     */
/* @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") Integer pageNo,
                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                    @RequestParam(defaultValue = "id") String sortBy,
                                    @RequestParam(defaultValue = "desc") String sortDirection,
                                    @RequestParam(defaultValue = "null") String filterBy,
                                    @RequestParam(defaultValue = "null") String filterValue
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(facultyService.getAll(pageNo, pageSize, sortBy, sortDirection, filterBy, filterValue));
    }

}
*/