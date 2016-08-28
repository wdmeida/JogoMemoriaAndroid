package com.pdm.ifsudestemg.model;

/**
 * Created by wagner on 19/08/16.
 */
public class User {
    private Long id;
    private String name;
    private int points;

    public User() { }

    public User(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString() {
        return points + " - " + name;
    }
}
