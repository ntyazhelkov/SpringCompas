package ru.appline.controller;

import org.springframework.web.bind.annotation.*;
import ru.appline.logic.Compas;
import ru.appline.logic.CompasModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@RestController
public class Controller {

    private static final CompasModel compasModel = CompasModel.getInstance();

    @PostMapping(value = "/createCompas", consumes = "application/json", produces = "application/json")
    public Map<String, Compas> createPet(@RequestBody Map<String, String> compas) {
        Compas compasResult;
        for(Map.Entry<String, String> entry : compas.entrySet()) {
            ArrayList result = new ArrayList();
            String ranges[] = entry.getValue().split("-");
            try {
                if ((Integer.parseInt(ranges[1]) > 359 || Integer.parseInt(ranges[1]) < 0) || (Integer.parseInt(ranges[0]) > 359 || Integer.parseInt(ranges[0]) < 0)) {
                    throw new NumberFormatException();
                } else {
                    if (Integer.parseInt(ranges[0]) > Integer.parseInt(ranges[1])) {
                        if (Integer.parseInt(ranges[0]) < 360) {
                            int i = Integer.parseInt(ranges[0]);
                            while (i < 360) {
                                result.add(i);
                                i++;
                            }
                        }
                        if (Integer.parseInt(ranges[1]) >= 0) {
                            int i = 0;
                            while (i <= Integer.parseInt(ranges[1])) {
                                result.add(i);
                                i++;
                            }
                        }
                    } else if (Integer.parseInt(ranges[0]) < Integer.parseInt(ranges[1])) {
                        int i = Integer.parseInt(ranges[0]);
                        while (i <= Integer.parseInt(ranges[1])) {
                            result.add(i);
                            i++;
                        }
                    } else if (Integer.parseInt(ranges[0]) == Integer.parseInt(ranges[1])) {
                        result.add(Integer.parseInt(ranges[0]));
                    }
                }
            } catch (NumberFormatException e) {
                System.err.println("Incorrect data type");
                break;
            }
            compasResult = new Compas(result);
            compasModel.add(compasResult, entry.getKey());
        }
        return compasModel.getAll();
    }

    @GetMapping(value = "/getSide", consumes = "application/json", produces = "application/json")
    public Map<String, String> getSideOfTheWorld(@RequestBody Map<String, Integer> degreeOfSide) {
        Map<String, String> response = new HashMap<String, String>();
        response.put("Side", compasModel.getFromList(degreeOfSide.get("Degree")));
        return response;
    }
}
