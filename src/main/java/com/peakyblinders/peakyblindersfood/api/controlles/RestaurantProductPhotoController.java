package com.peakyblinders.peakyblindersfood.api.controlles;

import com.peakyblinders.peakyblindersfood.domain.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    @Autowired
    private RestaurantService restaurantService;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updadePhoto(@PathVariable Long restaurantId, @PathVariable Long productId,
                            @RequestParam MultipartFile file){
        var fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        var filePhoto = Path.of("C:/Users/igor/OneDrive/Área de Trabalho/catalogo/" , fileName);

        System.out.println("ARQUIVO - " + filePhoto);
        System.out.println("ARQUIVO - " + file.getContentType());

        try {
            file.transferTo(filePhoto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
