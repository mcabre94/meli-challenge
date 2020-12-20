package com.app.meli.appmeli.models;

import com.app.meli.appmeli.models.interfaces.CalculadorFavoritosStrategy;

import java.lang.reflect.Array;
import java.util.*;

public class CompraMaximizada {
    private CalculadorFavoritosStrategy strategy;

    public void setStrategy(CalculadorFavoritosStrategy strategy){
        this.strategy = strategy;
    }

    public List<Item> calculate(List<Item> items, Float amount) throws Exception {
        Map<String,Float> map = new HashMap<String,Float>();
        for (Item i : items) map.put(i.getId(),i.getAmount());

        List<String> selectedIds = this.strategy.calculate(map,amount);

        if(selectedIds.size() == 0){
            throw new Exception("No puede comprar ningun item con el monto enviado");
        }

        List<Item> selectedItems = new ArrayList<>();

        for (Item i : items){
            if( selectedIds.contains(i.getId()) ){
                selectedItems.add(i);
            }
        }

        return selectedItems;
    }
}
