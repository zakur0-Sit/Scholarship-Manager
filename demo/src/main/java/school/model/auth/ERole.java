package school.model.auth;

/*
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum ERole {

    ROLE_USER("user", 1),
    ROLE_ADMIN("admin", 2);

    private final String alias;
    private final Integer priority;

    public static List<ERole> getAll() {
        return Arrays.stream(ERole.values()).collect(Collectors.toList());
    }

    public static ERole getEnumByAlias(String alias) {
        if (alias == null) {
            return ROLE_USER;
        }
        return Arrays.stream(ERole.values())
                .filter(eRole -> eRole.getAlias().equals(alias.toLowerCase()))
                .findFirst()
                .orElse(ERole.ROLE_USER);
    }

    public static ERole getGreatestERole(Set<ERole> userRoles) {
        if (userRoles.isEmpty()) {
            return ROLE_USER;
        }
        Optional<ERole> roleOptional = userRoles
                .stream()
                .max(Comparator.comparing(ERole::getPriority));

        return roleOptional.orElse(ROLE_USER);
    }
}
*/