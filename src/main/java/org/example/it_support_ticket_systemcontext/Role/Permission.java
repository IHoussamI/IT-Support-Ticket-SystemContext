package org.example.it_support_ticket_systemcontext.Role;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_CREATE("employee:create"),

    IT_SUPPORT_READ("it_support:read"),
    IT_SUPPORT_UPDATE("it_support:update"),
    IT_SUPPORT_COMMENT("it_support:comment");

    private final String permission;
}
