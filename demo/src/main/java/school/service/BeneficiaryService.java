package school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import school.model.business.Beneficiary;
import school.repository.BeneficiaryRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

@Service
public class BeneficiaryService {

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    public Page<Beneficiary> getAll(Integer pageNo, Integer pageSize, String sortBy,
                                    String sortDirection) {
        Sort sort = SortOrder
                .getEnumByAlias(sortDirection)
                .apply(Sort.by(sortBy));

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return beneficiaryRepository.findAll(pageable);
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

    public Beneficiary getBeneficiaryById(Long id) {
        return beneficiaryRepository.findById(id).orElse(null);
    }

    public Beneficiary saveBeneficiary(Beneficiary beneficiary) {
        return beneficiaryRepository.save(beneficiary);
    }

    public void deleteBeneficiary(Long id) {
        beneficiaryRepository.deleteById(id);
    }

}
