package com.app.meli.appmeli.models;

import java.util.ArrayList;
import java.util.List;

public class CouponResponse {
    private List<String> items;
    private Float monto;

    public CouponResponse(List<Item> items){
        this.items = new ArrayList<>();
        this.monto = (float) 0;
        for (Item i : items){
            this.items.add(i.getId());
            this.monto += i.getAmount();
        }
    }

    public Float getMonto(){
        return monto;
    }

    public List<String> getItems(){ return items ; }
}
