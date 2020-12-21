package com.app.meli.appmeli.sevices.interfaces;

import com.app.meli.appmeli.models.Item;
import org.springframework.stereotype.Component;

@Component
public interface ItemService {

    /**
     * Retrieves an item from its id
     * @param id
     * @return Item
     */
    public Item obtainItem(String id);
}
