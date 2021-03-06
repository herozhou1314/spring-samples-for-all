package com.heibaiying.consumer.controller;


import com.heibaiying.common.bean.Product;
import com.heibaiying.consumer.feign.CProductFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : heibaiying
 */
@Controller
@RequestMapping("sell")
public class SellController {

    @Autowired
    private CProductFeign cproductFeign;

    @GetMapping("products")
    public String productList(Model model) {
        List<Product> products = cproductFeign.productList();
        model.addAttribute("products", products);
        return "products";
    }


    @GetMapping("product/{id}")
    public String productDetail(@PathVariable int id, Model model) {
        Product product = cproductFeign.productDetail(id);
        model.addAttribute("product", product);
        return "product";
    }


    @PostMapping("product")
    public String save(@RequestParam String productName) {
        long id = Math.round(Math.random() * 100);
        Product product = new Product(id, productName, false, new Date(), 88);
        cproductFeign.save(product);
        return "redirect:products";
    }
}
