package school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import school.model.business.Student;
import school.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.*;
import java.util.Arrays;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Page<Student> getAll(Integer pageNo, Integer pageSize, String sortBy,
                                String sortDirection, String filterBy, String filterValue) {

        Sort sort = SortOrder
                .getEnumByAlias(sortDirection)
                .apply(Sort.by(sortBy));

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        if (filterBy.equals("name")) {
            return studentRepository.findAllByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(filterValue, filterValue, pageable);
        }
        return studentRepository.findAll(pageable);
    }

    public Student findById(Long studentId) {
        return studentRepository.findById(studentId).get();
    }

    @AllArgsConstructor
    @Getter
    public enum SortOrder {
        ASCENDING("asc"),
        DESCENDING("desc");
        private String alias;

        public static SortOrder getEnumByAlias(String alias) {
            if (alias == null) {
                return SortOrder.ASCENDING;
            }
            return Arrays.stream(SortOrder.values())
                    .filter(sort -> sort.getAlias().equals(alias.toLowerCase()))
                    .findFirst()
                    .orElse(SortOrder.ASCENDING);
        }

        public Sort apply(Sort currentSort) {
            switch (this) {
                case ASCENDING: {
                    return currentSort.ascending();
                }
                case DESCENDING: {
                    return currentSort.descending();
                }
            }
            return currentSort.ascending();
        }
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
    public Student getStudentByEmail(String email) {return studentRepository.findByEmail(email);}


}

