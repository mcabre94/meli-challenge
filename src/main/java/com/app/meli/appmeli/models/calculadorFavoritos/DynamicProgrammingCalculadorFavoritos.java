package com.app.meli.appmeli.models.calculadorFavoritos;

import com.app.meli.appmeli.models.interfaces.CalculadorFavoritosStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * We use the dynamic programming approach to resolve the problem
 * The problem is well known as the "knapsack problem"
 * its using a space optimized version of the regular dynamic programming approach
 */
public class DynamicProgrammingCalculadorFavoritos implements CalculadorFavoritosStrategy {

    @Override
    public List<String> calculate(Map<String, Float> items, Float amount) {
        int intAmount = convertToInteger(amount);
        int size = items.size();
        //its using a space optimized version of the regular dynamic programming approach (only 2 rows)
        int[][] result = new int[2][intAmount+1] ;
        ArrayList<String>[][] selected = new ArrayList[2][intAmount+1];
        Object[] item_ids = items.keySet().toArray();

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

        return selected[(size-1)&1][intAmount];
    }

    /**
     * Multiply the value per 100, this way, we convert a number with 2 decimals to an integer, for the dp algorithm purposes
     * @param value
     * @return value converted to integer
     */
    public Integer convertToInteger(Float value){
        return Math.round((value * 100));
    }
}
