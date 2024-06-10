package school.service;
/*
import school.model.business.Faculty;
import school.repository.pagination.FacultySortedAndPagedRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class FacultyService {

    @Autowired
    FacultySortedAndPagedRepository facultySortedAndPagedRepository;

    public Page<Faculty> getAll(Integer pageNo, Integer pageSize, String sortBy,
                                String sortDirection, String filterBy, String filterValue) {

        Sort sort = SortOrder
                .getEnumByAlias(sortDirection)
                .apply(Sort.by(sortBy));

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        if (filterBy.equals("name")) {
            return facultySortedAndPagedRepository.findAllByNameContainsIgnoreCase(filterValue, pageable);
        }
        return facultySortedAndPagedRepository.findAll(pageable);
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

}*/