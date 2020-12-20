package com.app.meli.appmeli.models;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class CouponResponse {
    private List<String> items;
    private Float total;

    public CouponResponse(List<Item> items){
        this.items = new ArrayList<>();
        this.total = (float) 0;
        for (Item i : items){
            this.items.add(i.getId());
            this.total += i.getAmount();
        }
        this.total = roundTwoDecimals(this.total);
    }

    public Float getTotal(){
        return total;
    }

    public List<String> getItems(){ return items ; }

    private Float roundTwoDecimals(Float value) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator(',');
        DecimalFormat twoDForm = new DecimalFormat("#.##", otherSymbols);
        return Float.valueOf(twoDForm.format(value));
    }
}
