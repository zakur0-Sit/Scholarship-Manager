package school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import school.model.business.Course;
import school.repository.CourseRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.*;
import java.util.Arrays;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Page<Course> getAll(Integer pageNo, Integer pageSize, String sortBy,
                               String sortDirection) {
        Sort sort = SortOrder
                .getEnumByAlias(sortDirection)
                .apply(Sort.by(sortBy));

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return courseRepository.findAll(pageable);
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

    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    public void getOutstandingStudents() {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/proiect", "postgres", "master");
             CallableStatement callableStatement = connection.prepareCall("{ call studenti_restantieri() }")) {

            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                String materie = resultSet.getString("materie");
                int nr_restantieri = resultSet.getInt("nr_restantieri");
                System.out.println("Materie: " + materie + ", Număr Restanțieri: " + nr_restantieri);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
