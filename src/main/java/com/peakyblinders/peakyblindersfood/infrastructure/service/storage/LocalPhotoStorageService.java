package com.peakyblinders.peakyblindersfood.infrastructure.service.storage;

import com.peakyblinders.peakyblindersfood.domain.services.PhotoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalPhotoStorageService implements PhotoStorageService {

    @Value("${peakyblindersfood-api.storage.local.directory-photos}")
    private Path directoryPhoto;

    @Override
    public InputStream toRecover(String nameFile) {
        try {
        Path filePath = getFilePath(nameFile);
                 return Files.newInputStream(filePath);
        } catch (Exception e) {
            throw new StorageException("Não foi possível recuperar arquivo.", e );
        }
    }

    @Override
    public void store(NewPhoto photo) {
        try {
            Path filePath = getFilePath(photo.getNameFile());

            FileCopyUtils.copy(photo.getInputStream(), Files.newOutputStream(filePath));
        } catch (Exception e) {
    throw new StorageException("Não foi possível armazenar arquivo.", e );
        }
    }

    @Override
    public void remove(String nameFile) {
        try {
            Path filePath = getFilePath(nameFile);
            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            throw new StorageException("Não foi possivel excluir arquivo.",e);
        }
    }

    private Path getFilePath(String nameFile){
        return  directoryPhoto.resolve(Path.of(nameFile));
    }
}
