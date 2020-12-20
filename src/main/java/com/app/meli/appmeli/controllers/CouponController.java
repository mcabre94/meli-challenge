package com.app.meli.appmeli.controllers;


import com.app.meli.appmeli.configuration.Config;
import com.app.meli.appmeli.models.*;
import com.app.meli.appmeli.models.calculadorFavoritos.BacktrackCalculadorFavoritos;
import com.app.meli.appmeli.models.calculadorFavoritos.DynamicProgrammingCalculadorFavoritos;
import com.app.meli.appmeli.sevices.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

@RestController
public class CouponController {

    @Autowired
    private ItemService itemService;

//    public CouponController(ApiMeliItemService service){
//        this.itemService = service;
//    }

    @PostMapping("/coupon")
    public CouponResponse calculateCoupon(@RequestBody CouponRequest request){
//        return "responde como la gente";
//        System.out.println(request);
        List<Item> items = obtainItemList(request.getItems());
        Float monto = request.getAmount();

//        Map<String,Float> test = new HashMap<>();
//        List<Item> items = new ArrayList<>();
//        items.add(new Item("MLA1",(float) 100));
//        items.add(new Item("MLA2",(float) 210));
//        items.add(new Item("MLA3",(float) 260));
//        items.add(new Item("MLA4",(float) 80));
//        items.add(new Item("MLA5",(float) 90));
//        items.add(new Item("MLA6",(float) 90));

//        for(int i = 0 ; i < 60; i++){
//            items.add(new Item("MLA"+i,(float) roundTwoDecimals((float) Math.random()*100)));
////            items.put("MLA"+i,(float) roundTwoDecimals((float) Math.random()*100));
////            test.put("MLA"+i,(float) Math.round(Math.random()*100));
//        }

//        Float monto = (float) 1500;

        CompraMaximizada compra = new CompraMaximizada();
        if(items.size() <= 20 || (items.size() <= 25 && monto > 10000)){
            System.out.println("backtrack");
            compra.setStrategy(new BacktrackCalculadorFavoritos());
        }else{
            System.out.println("dynamic");
            compra.setStrategy(new DynamicProgrammingCalculadorFavoritos());
        }

        List<Item> lista;
        try{
            lista = compra.calculate(items,monto);
        }catch (Exception e){
            throw new NotFoundResponse();
        }

        CouponResponse response = new CouponResponse(lista);

        return response;
    }

    private List<Item> obtainItemList(List<String> items) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
//        ApiMeliItemService service = context.getBean(ApiMeliItemService.class);

        List<String> itemsWithoutDuplicates = new ArrayList<>(new HashSet<>(items));

        List<Item> lista = new ArrayList<Item>();
        for (String item_id : itemsWithoutDuplicates) {
            Item item = itemService.obtainItem(item_id);
            if(item != null){
                lista.add(item);
                System.out.println(item);
            }
        }
        System.out.println(lista);
        return lista;
    }

//    private Float roundTwoDecimals(Float value) {
//        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
//        otherSymbols.setDecimalSeparator('.');
//        otherSymbols.setGroupingSeparator(',');
//        DecimalFormat twoDForm = new DecimalFormat("#.##", otherSymbols);
//        return Float.valueOf(twoDForm.format(value));
//    }
}
