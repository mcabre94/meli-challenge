package com.app.meli.appmeli.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CouponRequest {
    @JsonProperty("item_ids")
    public List<String> item_ids;

    @JsonProperty("amount")
    public Float amount;

    public CouponRequest(List<String> items,Float amount) {
        this.item_ids = items;
        this.amount = amount;
    }

    public String toString(){
        return "{ items : " + item_ids + ", amount : " + amount + " }";
    }

    public List<String> getItems() {
        return item_ids;
    }

    public void setItems(List<String> items) {
        this.item_ids = items;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getAmount() {
        return amount;
    }
}
