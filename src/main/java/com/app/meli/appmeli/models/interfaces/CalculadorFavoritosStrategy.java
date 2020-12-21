package com.app.meli.appmeli.models.interfaces;

import java.util.List;
import java.util.Map;

public interface CalculadorFavoritosStrategy {

    /**
     * calculate best way to use a coupon
     * select the best combination of items of the list that expends the maximum amount of money without exceding the amount of the coupon
     * @param items
     * @param amount
     */
    public List<String> calculate(Map<String, Float> items, Float amount);
}
