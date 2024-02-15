package com.peakyblinders.peakyblindersfood.api.controlles;

import com.peakyblinders.peakyblindersfood.api.assembler.PhotoProducModelAssembler;
import com.peakyblinders.peakyblindersfood.api.model.dto.PhotoProductModelDTO;
import com.peakyblinders.peakyblindersfood.api.model.input.PhotoProductInput;
import com.peakyblinders.peakyblindersfood.domain.exceptions.EntityNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.models.PhotoProduct;
import com.peakyblinders.peakyblindersfood.domain.models.Product;
import com.peakyblinders.peakyblindersfood.domain.services.PhotoProductService;
import com.peakyblinders.peakyblindersfood.domain.services.PhotoStorageService;
import com.peakyblinders.peakyblindersfood.domain.services.ProductService;
import com.peakyblinders.peakyblindersfood.domain.services.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private PhotoProductService photoProductService;

    @Autowired
    private PhotoProducModelAssembler photoProducModelAssembler;

    @Autowired
    private PhotoStorageService storageService;

    @Autowired
    private ProductService productService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PhotoProductModelDTO search(@PathVariable Long restaurantId, @PathVariable Long productId) {
        PhotoProduct photoProduct = photoProductService.seekOrFail(restaurantId, productId);
        return photoProducModelAssembler.toModel(photoProduct);
    }

    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> downloadPhoto(@PathVariable Long restaurantId,
                                                             @PathVariable Long productId) {
        try {

            PhotoProduct photoProduct = photoProductService.seekOrFail(restaurantId, productId);
            InputStream inputStream = storageService.toRecover(photoProduct.getNameFile());

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(inputStream));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }


    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public PhotoProductModelDTO updadePhoto(@PathVariable Long restaurantId, @PathVariable Long productId,
                                            @Valid PhotoProductInput photoInput) throws IOException {
        Product product = productService.seekOrFail(restaurantId, productId);
        MultipartFile file = photoInput.getFile();

        PhotoProduct photo = new PhotoProduct();
        photo.setProduct(product);
        photo.setDescription(photoInput.getDescription());
        photo.setContentType(file.getContentType());
        photo.setSizeFile(file.getSize());
        photo.setNameFile(file.getOriginalFilename());

        PhotoProduct photoSave = photoProductService.save(photo, file.getInputStream());
        return photoProducModelAssembler.toModel(photoSave);

    }


}
