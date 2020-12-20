package com.app.meli.appmeli.models.calculadorFavoritos;

import com.app.meli.appmeli.models.interfaces.CalculadorFavoritosStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DynamicProgrammingCalculadorFavoritos implements CalculadorFavoritosStrategy {

    @Override
    public List<String> calculate(Map<String, Float> items, Float amount) {
        //        System.out.print(items);
        int intAmount = convertToInteger(amount);
        int size = items.size();
        int[][] result = new int[2][intAmount+1] ;
//        StringBuilder[][] selected = new StringBuilder[2][intAmount+1];
        ArrayList<String>[][] selected = new ArrayList[2][intAmount+1];
        Object[] item_ids = items.keySet().toArray();
//        ArrayList<String> elegidos = new ArrayList<>();

        for (int i = 0; i < size ; i++) {
            for (int w = 0; w <= intAmount; w++) {
                int priceOfItem = convertToInteger(items.get(item_ids[i]));

                int currentRow = i&1;
                int lastRow = (i-1)&1;


                int lastValueWithoutPickItem =  i >= 1 ? result[(i-1)&1][w] : 0;

                if(w >= priceOfItem){
                    int lastValueBeforePickItem = i >= 1 ? result[lastRow][w-priceOfItem] : 0;

                    int pick = lastValueBeforePickItem + priceOfItem;
                    int leave = lastValueWithoutPickItem;
                    result[currentRow][w] = Math.max(pick,leave);

                    if(pick == result[i&1][w]){
                        if(i>=1){
                            selected[i&1][w] = (ArrayList<String>) selected[(i-1)&1][w-priceOfItem].clone();
                        }else{
                            selected[i&1][w] = new ArrayList<>();
                        }
                        selected[i&1][w].add(item_ids[i].toString());
                    }else{
                        if(i >= 1){
                            selected[i&1][w] = (ArrayList<String>) selected[(i-1)&1][w].clone();
                        }else{
                            selected[i&1][w] = new ArrayList<>();
                        }
                    }

                }else{
                    result[currentRow][w] = lastValueWithoutPickItem;

                    if(i >= 1){
                        selected[i&1][w] = selected[(i-1)&1][w];
                    }else{
                        selected[i&1][w] = new ArrayList<>();
                    }
                }
            }

        }
//        System.out.println(result);
//        System.out.println("\nresultado final\n");
//        System.out.println(convertIntegerToFloat(result[(size-1)&1][intAmount]));
//        System.out.println(selected[(size-1)&1][intAmount].toString());

        return selected[(size-1)&1][intAmount];
    }


    public Integer convertToInteger(Float value){
        return Math.round((value * 100));
    }

    public Float convertIntegerToFloat(Integer value){
        return (float)value / 100;
    }
}
