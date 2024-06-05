package school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.model.business.Scholarship;

@Repository
public interface ScholarshipRepository extends JpaRepository<Scholarship, Long> {
}
