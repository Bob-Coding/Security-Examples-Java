package com.example.security.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.security.security.ApplicationUserPermission.*;
//without importing static applicationPermission.* You have to use ApplicationUserPermission.STUDENT_WRITE etc

public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),
    STUDENT(Sets.newHashSet());

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}
