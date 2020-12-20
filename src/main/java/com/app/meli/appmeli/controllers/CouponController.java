package com.app.meli.appmeli.controllers;


import com.app.meli.appmeli.models.CouponRequest;
import com.app.meli.appmeli.models.CouponResponse;
import com.app.meli.appmeli.models.CompraMaximizada;
import com.app.meli.appmeli.models.Item;
import com.app.meli.appmeli.models.calculadorFavoritos.DynamicProgrammingCalculadorFavoritos;
import com.app.meli.appmeli.sevices.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CouponController {

    @PostMapping("/coupon")
    public CouponResponse calculateCoupon(@RequestBody CouponRequest request){
//        return "responde como la gente";
//        System.out.println(request);
//        List<Item> items = obtainItemList(request.item_ids);

//        Map<String,Float> test = new HashMap<>();
        List<Item> test = new ArrayList<>();
        test.add(new Item("MLA1",(float) 1600));
        test.add(new Item("MLA2",(float) 123));
        test.add(new Item("MLA3",(float) 300));
        test.add(new Item("MLA4",(float) 150));
        test.add(new Item("MLA5",(float) 300));
        test.add(new Item("MLA6",(float) 90));

//        for(int i = 0 ; i < 100; i++){
//            test.put("MLA"+i,(float) roundTwoDecimals((float) Math.random()*100));
////            test.put("MLA"+i,(float) Math.round(Math.random()*100));
//        }

        Float monto = (float) 700;

        CompraMaximizada compra = new CompraMaximizada();
        compra.setStrategy(new DynamicProgrammingCalculadorFavoritos());
        List<Item> lista = compra.calculate(test,monto);

        CouponResponse response = new CouponResponse(lista);

        return response;
    }

    private List<Item> obtainItemList(List<String> items) {
        ItemService service = new ItemService();
        List<Item> lista = new ArrayList<Item>();
        for (String item_id : items) {
            Item item = service.obtainItem(item_id);
            lista.add(item);
            System.out.println(item);
        }
        return lista;
    }

    private Float roundTwoDecimals(Float value) {
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator(',');
        DecimalFormat twoDForm = new DecimalFormat("#.##", otherSymbols);
        return Float.valueOf(twoDForm.format(value));
    }
}
