package com.company.domain.user;

/**
 * Роль пользователя
 */
public enum UserRole {

    SUPERVISOR_PPE("Руководитель ППЭ / Член ГЭК"),
    TECH_MAN("Тех специалист"),
    ORGANIZER_PPE("Организатор ППЭ"),
    PUBLIC_OBSERVER("Общественный наблюдатель"),
    EXPERT("Эксперт"),
    STUDENT("Студент");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
