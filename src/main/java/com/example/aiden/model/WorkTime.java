package com.example.aiden.model;

public enum WorkTime {
    FULL_TIME("Pełny etat"),
    PART_TIME_3_4("3/4 etatu"),
    PART_TIME_1_2("1/2 etatu"),
    PART_TIME_1_4("1/4 etatu");

    private String name;

    WorkTime(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
