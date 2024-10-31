package com.zerobase.order.order.controller;

import com.zerobase.order.common.response.CommonResponse;
import com.zerobase.order.order.application.ProductFacade;
import com.zerobase.order.order.controller.ProductDto.GetProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductFacade productFacade;

    @PostMapping("/product")
    public CommonResponse<Void> registerProduct(
        @RequestBody ProductDto.RegisterProductRequest request
    ) {

        productFacade.registerProduct(request.toCommand());

        return CommonResponse.success();
    }

    @PutMapping("/product")
    public CommonResponse<Void> modifyProduct(
        @RequestBody ProductDto.ModifyProductRequest request
    ) {

        productFacade.modifyProduct(request.toCommand());

        return CommonResponse.success();
    }

    @GetMapping("/product/{productId}")
    public CommonResponse<GetProductResponse> getProduct(
        @PathVariable Long productId
    ) {

        return CommonResponse.success(
            ProductDto.GetProductResponse.from(productFacade.getProduct(productId))
        );
    }

}
