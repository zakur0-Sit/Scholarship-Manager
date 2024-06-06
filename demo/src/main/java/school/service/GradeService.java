package school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import school.model.business.Grade;
import school.repository.GradeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.*;
import java.util.Arrays;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public Page<Grade> getAll(Integer pageNo, Integer pageSize, String sortBy,
                              String sortDirection) {
        Sort sort = SortOrder
                .getEnumByAlias(sortDirection)
                .apply(Sort.by(sortBy));

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return gradeRepository.findAll(pageable);
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

    public Grade getGradeById(Long id) {
        return gradeRepository.findById(id).orElse(null);
    }

    public Grade saveGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }
    public void update_grades(int idStudent, int newValue){
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/proiect", "postgres", "master");

            CallableStatement callableStatement = connection.prepareCall("{ call update_grades(?, ?) }");
            callableStatement.setInt(1, idStudent);
            callableStatement.setInt(2, newValue);
            callableStatement.executeUpdate();
            System.out.println("Updated successfully.");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void averageGradesPerObject(int courseID){
        try {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/proiect", "postgres", "master");

        CallableStatement callableStatement = connection.prepareCall("{ call average_grades_per_subject(?) }");

        callableStatement.setInt(1, courseID);

        ResultSet resultSet = callableStatement.executeQuery();

        while (resultSet.next()) {
            String courseName = resultSet.getString("course_name");
            double averageGrade = resultSet.getDouble("average_grade");
            System.out.println("Course: " + courseName + ", Average Grade: " + averageGrade);
        }

        resultSet.close();
        callableStatement.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    }
}
