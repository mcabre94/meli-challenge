package com.app.meli.appmeli.models.calculadorFavoritos;

import com.app.meli.appmeli.models.interfaces.CalculadorFavoritosStrategy;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * We use the Recursion aprouch to resolve the problem
 * The problem is well known as the "knapsack problem"
 */
public class BacktrackCalculadorFavoritos implements CalculadorFavoritosStrategy {
    private Float amount;
    private Map<String,Float> items;
    private Object[] item_ids;

    @Override
    public List<String> calculate(Map<String, Float> items, Float amount) {
        this.amount = amount;
        this.items = items;
        item_ids = items.keySet().toArray();

        BackTrackResponse max = backtrack(0,new BackTrackResponse(new ArrayList<String>(),(float) 0));

        return max.getSelected();
    }

    private BackTrackResponse backtrack(int i, BackTrackResponse backtrack){
        if(backtrack.getSum() > amount){
            return new BackTrackResponse(new ArrayList<>(),(float) 0);
        }
        if(i == items.size()){
            return backtrack;
        }

        ArrayList pickedElements = new ArrayList(backtrack.getSelected());
        pickedElements.add(item_ids[i].toString());

        BackTrackResponse pickBacktrack = new BackTrackResponse(pickedElements,backtrack.getSum() + items.get(item_ids[i]));

        BackTrackResponse pick = backtrack(i+1,pickBacktrack);
        BackTrackResponse leave = backtrack(i+1,backtrack);

        if(pick.getSum() > leave.getSum()){
            return pick;
        }else{
            return leave;
        }
    }
}

class BackTrackResponse{
    private ArrayList<String> selected;
    private Float sum;

    public BackTrackResponse(ArrayList<String> selected, Float sum) {
        this.selected = selected;
        this.sum = sum;
    }

    public ArrayList<String> getSelected() {
        return selected;
    }

    public Float getSum() {
        return sum;
    }
}
