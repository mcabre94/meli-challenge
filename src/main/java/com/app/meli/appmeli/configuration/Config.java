package com.app.meli.appmeli.configuration;

import com.app.meli.appmeli.sevices.interfaces.ItemService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.app.meli")
public class Config {

    @Bean
    public ItemService itemService(){
        return new com.app.meli.appmeli.sevices.ApiMeliItemService();
    }
}
