package com.app.meli.appmeli.models;

import com.app.meli.appmeli.models.interfaces.CalculadorFavoritosStrategy;

import java.lang.reflect.Array;
import java.util.*;

public class CompraMaximizada {
    private CalculadorFavoritosStrategy strategy;

    public void setStrategy(CalculadorFavoritosStrategy strategy){
        this.strategy = strategy;
    }

////este anda
//    public List<String> calculate(Map<String, Float> items, Float amount){
////        System.out.print(items);
//        int intAmount = convertToInteger(amount);
//        int size = items.size();
//        int[][] result = new int[size][intAmount+1] ;
//        Object[] item_ids = items.keySet().toArray();
//
//        for (int i = 0; i < size ; i++) {
//            for (int w = 0; w <= intAmount; w++) {
//                int priceOfItem = convertToInteger(items.get(item_ids[i]));
//
//                if(w >= priceOfItem){
//                    int pick = i >= 1 ? result[i-1][w-priceOfItem] + priceOfItem : priceOfItem;
//                    int leave = i >= 1 ? result[i-1][w] : 0;
//                    result[i][w] = Math.max(pick,leave);
//                }else{
//                    result[i][w] = i >= 1 ? result[i-1][w] : 0;
//                }
//            }
//
//
//
//        }
////        System.out.println(result);
//        System.out.println("\nresultado final\n");
//        System.out.println(convertIntegerToFloat(result[size-1][intAmount]));
//        return Arrays.asList("MLA1", "MLA2");
//    }

    public List<Item> calculate(List<Item> items, Float amount){
        Map<String,Float> map = new HashMap<String,Float>();
        for (Item i : items) map.put(i.getId(),i.getAmount());

        List<String> selectedIds = this.strategy.calculate(map,amount);
        List<Item> selectedItems = new ArrayList<>();

        for (Item i : items){
            if( selectedIds.contains(i.getId()) ){
                selectedItems.add(i);
            }
        }

        return selectedItems;
    }
}
