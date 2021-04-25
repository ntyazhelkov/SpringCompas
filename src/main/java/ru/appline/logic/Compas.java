package ru.appline.logic;

import java.util.List;

public class Compas {

    private List<Integer> degree;

    public Compas(List<Integer> degree) {
        this.degree = degree;
    }

    public List<Integer> getDegree() {
        return degree;
    }

    public void setDegree(List<Integer> degree) {
        this.degree = degree;
    }
}
