package com.peakyblinders.peakyblindersfood.api.controlles;

import com.peakyblinders.peakyblindersfood.api.assembler.ProductInputDisassembler;
import com.peakyblinders.peakyblindersfood.api.assembler.ProductModelAssembler;
import com.peakyblinders.peakyblindersfood.api.model.dto.ProductModelDTO;
import com.peakyblinders.peakyblindersfood.api.model.input.ProductInput;
import com.peakyblinders.peakyblindersfood.domain.models.Product;
import com.peakyblinders.peakyblindersfood.domain.models.Restaurant;
import com.peakyblinders.peakyblindersfood.domain.repositories.ProductRepository;
import com.peakyblinders.peakyblindersfood.domain.services.ProductService;
import com.peakyblinders.peakyblindersfood.domain.services.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products")
public class RestaurantProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private ProductModelAssembler productModelAssembler;
    @Autowired
    private ProductInputDisassembler productInputDisassembler;

    @GetMapping
    public List<ProductModelDTO> list(@PathVariable Long restaurantId,
                                      @RequestParam (required = false) boolean includeInactives){
        Restaurant restaurant = restaurantService.seekOrFail(restaurantId);
        List<Product> products = null;
        if (includeInactives){
            products = productRepository.findByRestaurant(restaurant);
        }else {
            products= productRepository.findActiveByRestaurant(restaurant);
        }

        return productModelAssembler.toCollectionModel(products);
    }

    @GetMapping("/{productId}")
    public ProductModelDTO searchById(@PathVariable Long restaurantId, @PathVariable Long productId){
    Product product = productService.seekOrFail(restaurantId, productId);
    return productModelAssembler.toModel(product);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductModelDTO save(@PathVariable Long restaurantId,
                                @RequestBody @Valid ProductInput productInput){
        Restaurant restaurant = restaurantService.seekOrFail(restaurantId);

        Product product = productInputDisassembler.toDomainObject(productInput);

        product.setRestaurant(restaurant);
        product = productRepository.save(product);

        return productModelAssembler.toModel(product);
    }

    @PutMapping("/{productId}")
        public ProductModelDTO update(@PathVariable Long restaurantId, @PathVariable Long productId ,
                                  @RequestBody @Valid ProductInput productInput){
        Product productCurrent = productService.seekOrFail(restaurantId, productId);

        productInputDisassembler.copyToDomainObject(productInput,productCurrent);
        productCurrent = productService.save(productCurrent);
        return productModelAssembler.toModel(productCurrent);
    }
}
