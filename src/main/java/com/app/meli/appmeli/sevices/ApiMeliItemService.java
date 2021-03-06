package com.app.meli.appmeli.sevices;

import com.app.meli.appmeli.models.Item;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiMeliItemService implements com.app.meli.appmeli.sevices.interfaces.ItemService {
    private String uri = "https://api.mercadolibre.com/items/";

    /**
     * Retrieves an item from the meli-api from its id
     * @param id
     * @return Item
     */
    public Item obtainItem(String id){
        RestTemplate restTemplate = new RestTemplate();
        Item result = null;

        try{
            result = restTemplate.getForObject(uri+"{id}", Item.class, id);
        }catch (HttpClientErrorException exception){}

        return result;
    }
}
