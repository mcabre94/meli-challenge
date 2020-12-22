package com.app.meli.appmeli.controllers;

import com.app.meli.appmeli.models.*;
import com.app.meli.appmeli.models.calculadorFavoritos.BacktrackCalculadorFavoritos;
import com.app.meli.appmeli.models.calculadorFavoritos.DynamicProgrammingCalculadorFavoritos;
import com.app.meli.appmeli.sevices.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class CouponController {

    @Autowired
    private ItemService itemService;

    /**
     * calculate best way to use a coupon
     * select the best combination of items from the list that expends the maximum amount of money without exceding the amount of the coupon
     * @param request (list of items, max amount of coupon)
     */
    @PostMapping("/coupon")
    public CouponResponse calculateCoupon(@RequestBody CouponRequest request){
        List<Item> items = obtainItemList(request.getItems());
        Float monto = request.getAmount();

        CompraMaximizada compra = new CompraMaximizada();

        //it the number of items is low, we use the backtrace method, this is becouse its complexity only depends of the number of items, and not of the coupon amount
        if(items.size() <= 20 || (items.size() <= 25 && monto > 10000)){
            compra.setStrategy(new BacktrackCalculadorFavoritos());
        }else{
            //if the number of items is high, the algorithm that is used is the dynamic programming one, this is becouse, despite his complexity depends not only of the number of items, but also of the amount of the coupon, it's grow is much slower
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

    /**
     * obtain the list of items from their ids
     * @param items (list of ids of items)
     */
    private List<Item> obtainItemList(List<String> items) {
        List<String> itemsWithoutDuplicates = new ArrayList<>(new HashSet<>(items));

        List<Item> lista = new ArrayList<Item>();
        for (String item_id : itemsWithoutDuplicates) {
            Item item = itemService.obtainItem(item_id);
            if(item != null){
                lista.add(item);
            }
        }
        return lista;
    }
}
