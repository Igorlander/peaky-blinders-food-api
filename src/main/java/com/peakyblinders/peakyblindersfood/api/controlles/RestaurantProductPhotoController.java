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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<InputStreamResource> downloadPhoto(@PathVariable Long restaurantId,
                                                             @PathVariable Long productId,
                                                             @RequestHeader(name = "accept") String acceptHeader)
            throws HttpMediaTypeNotAcceptableException {
        try {
            PhotoProduct photoProduct = photoProductService.seekOrFail(restaurantId, productId);
            MediaType mediaTypePhoto = MediaType.parseMediaType(photoProduct.getContentType());
            List<MediaType> mediaTypesAccepts = MediaType.parseMediaTypes(acceptHeader);
            checkCompatibilityMediaType(mediaTypePhoto, mediaTypesAccepts);
            InputStream inputStream = storageService.toRecover(photoProduct.getNameFile());


            return ResponseEntity.ok()
                    .contentType(mediaTypePhoto)
                    .body(new InputStreamResource(inputStream));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    private void checkCompatibilityMediaType(MediaType mediaTypePhoto,
                                             List<MediaType> mediaTypesAccepts)
            throws HttpMediaTypeNotAcceptableException {
        boolean compatible = mediaTypesAccepts.stream()
                .anyMatch(mediaTypesAccept -> mediaTypesAccept.isCompatibleWith(mediaTypePhoto));

        if (!compatible) {
            throw new HttpMediaTypeNotAcceptableException(mediaTypesAccepts);
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

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePhoto(@PathVariable Long restaurantId, @PathVariable Long productId) {
        photoProductService.removePhoto(restaurantId, productId);
    }
}