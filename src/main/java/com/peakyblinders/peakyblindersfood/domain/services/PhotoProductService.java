package com.peakyblinders.peakyblindersfood.domain.services;

import com.peakyblinders.peakyblindersfood.domain.exceptions.PermissionNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.PhotoProductNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.models.PhotoProduct;
import com.peakyblinders.peakyblindersfood.domain.repositories.ProductRepository;
import com.peakyblinders.peakyblindersfood.domain.services.PhotoStorageService.NewPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Optional;

@Service
public class PhotoProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PhotoStorageService photoStorage;
    @Transactional
    public PhotoProduct save(PhotoProduct photo , InputStream dataFile){
        Long restaurantId = photo.getRestauranId();
        Long productId = photo.getProduct().getId();
        String nameNewFile = photoStorage.manageNameFile(photo.getNameFile());
        String nameFileExisting = null;

        Optional<PhotoProduct> photoExisting = productRepository
                .findPhotoById(restaurantId, productId);
        if (photoExisting.isPresent()){
            nameFileExisting = photoExisting.get().getNameFile();
            productRepository.delete(photoExisting.get());
        }

       photo.setNameFile(nameNewFile);
       photo  = productRepository.save(photo);
       productRepository.flush();

       NewPhoto newPhoto = NewPhoto.builder().
               nameFile(photo.getNameFile())
               .inputStream(dataFile)
               .build();

        photoStorage.toReplace(nameFileExisting ,newPhoto);
         return photo;
    }

<<<<<<< HEAD
    @Transactional
    public void removePhoto(Long restaurantId, Long productId){
        PhotoProduct photoProduct = seekOrFail(restaurantId, productId);
        productRepository.delete(photoProduct);
        productRepository.flush();
        photoStorage.remove(photoProduct.getNameFile());
    }

=======
>>>>>>> origin/main
    public PhotoProduct seekOrFail(Long restaurantId, Long productId) {
        return productRepository.findPhotoById(restaurantId, productId)
                .orElseThrow(() -> new PhotoProductNotFoundException(restaurantId, productId));

    }
}
