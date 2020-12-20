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

    @PostMapping("/coupon")
    public CouponResponse calculateCoupon(@RequestBody CouponRequest request){
        List<Item> items = obtainItemList(request.getItems());
        Float monto = request.getAmount();

        CompraMaximizada compra = new CompraMaximizada();

        //si el numero de items es bajo realizamos el calculo con el metodo backtrace, ya que su complejidad solo depende de el tamaño de items, no del monto del cupon
        if(items.size() <= 20 || (items.size() <= 25 && monto > 10000)){
            compra.setStrategy(new BacktrackCalculadorFavoritos());
        }else{
            //si el numero de items es elevado realizamos el calculo con el metodo de dynamic programming, ya que, a pesar que su complejidad aumenta tanto con el tamaño de los items como con el monto del cupon, pero, lo hace de una manera mucho mas lenta.
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
