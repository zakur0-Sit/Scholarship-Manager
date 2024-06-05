package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.model.business.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
