package com.app.meli.appmeli.models.interfaces;

import java.util.List;
import java.util.Map;

public interface CalculadorFavoritosStrategy {
    public List<String> calculate(Map<String, Float> items, Float amount);
}
