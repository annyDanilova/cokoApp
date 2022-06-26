package com.company.domain.test;

public enum QuestionType {

    OPEN_ANSWER("Открытый ответ"),
    CHOICE_OF_ANSWER("Выбор одного ответа"),
    CHOICE_OF_ANSWER_MN("Выбор нескольких ответов");


    private String type;

    QuestionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
