package com.app.meli.appmeli.models.calculadorFavoritos;

import com.app.meli.appmeli.models.interfaces.CalculadorFavoritosStrategy;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BacktrackCalculadorFavoritos implements CalculadorFavoritosStrategy {
    private Float amount;
    private Map<String,Float> items;
    private Object[] item_ids;

    @Override
    public List<String> calculate(Map<String, Float> items, Float amount) {
        this.amount = amount;
        this.items = items;
        item_ids = items.keySet().toArray();

//        System.out.println(backtrack(0,(float) 0));
//        System.out.println(convertIntegerToFloat(result[0][0]));
        return Arrays.asList("MLA1", "MLA2");
    }

    private Float backtrack(int i,Float sum){
        if(sum > amount){
            return (float) 0;
        }
        if(i == items.size()){
            return sum;
        }

        Float pick = backtrack(i+1,sum + items.get(item_ids[i]));
        Float leave = backtrack(i+1,sum );
        return Math.max(pick,leave);
    }
}
