package com.peakyblinders.peakyblindersfood.api.controlles;

import com.peakyblinders.peakyblindersfood.api.model.input.PhotoProductInput;
import com.peakyblinders.peakyblindersfood.domain.services.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    @Autowired
    private RestaurantService restaurantService;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updadePhoto(@PathVariable Long restaurantId, @PathVariable Long productId,
                            @Valid PhotoProductInput photoInput) {
        var fileName = UUID.randomUUID().toString() + "_" + photoInput.getFile().getOriginalFilename();
        var filePhoto = Path.of("C:/Users/igor/OneDrive/Área de Trabalho/catalogo/", fileName);

        System.out.println("ARQUIVO - " + photoInput.getDescription());
        System.out.println("ARQUIVO - " + filePhoto);
        System.out.println("ARQUIVO - " + photoInput.getFile().getContentType());

        try {
            photoInput.getFile().transferTo(filePhoto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
