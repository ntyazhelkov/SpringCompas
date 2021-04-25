package ru.appline.logic;

import java.util.HashMap;
import java.util.Map;

public class CompasModel {
    private static final CompasModel instance = new CompasModel();

    private final Map<String, Compas> model;

    public static CompasModel getInstance() {
        return instance;
    }

    public CompasModel() {
        model = new HashMap<String, Compas>();
    }

    public String getFromList(int degree) {
        for (Map.Entry<String, Compas> entry : model.entrySet()) {
            for (int i : entry.getValue().getDegree()) {
                if (i == degree)
                    return entry.getKey();
            }
        }
        return "Неверно введены градусы!";
    }

    public void add(Compas compas, String idSide) {
        model.put(idSide, compas);
    }

    public Map<String, Compas> getAll() {
        return model;
    }

}
