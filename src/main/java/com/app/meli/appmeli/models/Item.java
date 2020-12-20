package com.app.meli.appmeli.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Item {

    private String id;

    @JsonProperty("price")
    private Float amount;

    public Item(String id, Float amount) {
        this.id = id;
        this.amount = amount;
    }
}
