package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.model.business.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
}
