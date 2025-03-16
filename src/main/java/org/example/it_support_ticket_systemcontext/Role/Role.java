package org.example.it_support_ticket_systemcontext.Role;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Role {

            EMPLOYEE(Collections.emptySet()),
    IT_SUPPORT(
            Set.of(
                    Permission.EMPLOYEE_READ,
                    Permission.EMPLOYEE_CREATE,
                    Permission.IT_SUPPORT_READ,
                    Permission.IT_SUPPORT_UPDATE,
                    Permission.IT_SUPPORT_COMMENT
            )
    );

    private final Set<Permission> permissions;
    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = this.permissions.stream()
                .map(permission ->
                        new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
