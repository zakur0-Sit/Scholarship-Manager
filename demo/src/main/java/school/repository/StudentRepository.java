package school.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.model.business.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findAllByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(String firstName, String lastName, Pageable pageable);


    Student findByEmail(String email);
}
