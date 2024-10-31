package com.zerobase.order.order.application;

import com.zerobase.order.order.service.ProductCommand;
import com.zerobase.order.order.service.ProductCommand.ModifyProduct;
import com.zerobase.order.order.service.ProductInfo;
import com.zerobase.order.order.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductFacade {

    private final ProductService productService;

    public void registerProduct(ProductCommand.RegisterProduct command) {
        productService.registerProduct(command);
    }


    public void modifyProduct(ModifyProduct command) {
        productService.modifyProduct(command);

    }

    public ProductInfo.Main getProduct(Long productId) {
        return productService.getProduct(productId);
    }
}
