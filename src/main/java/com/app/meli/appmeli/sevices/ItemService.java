package com.app.meli.appmeli.sevices;

import com.app.meli.appmeli.models.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class ItemService {

    public Item obtainItem(String id){
        final String uri = "https://api.mercadolibre.com/items/";
        RestTemplate restTemplate = new RestTemplate();
        Item result = null;

        try{
            result = restTemplate.getForObject(uri+"{id}", Item.class, id);
        }catch (HttpClientErrorException exception){
            System.out.println(exception);
        }

        return result;
    }
}
