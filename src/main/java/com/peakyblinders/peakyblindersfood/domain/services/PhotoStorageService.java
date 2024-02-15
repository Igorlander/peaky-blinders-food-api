package com.peakyblinders.peakyblindersfood.domain.services;

import lombok.Builder;
import lombok.Getter;


import java.io.InputStream;
import java.util.UUID;

public interface PhotoStorageService {

    InputStream toRecover(String nameFile);

    void store(NewPhoto photo);

    void remove(String nameFile);

    default void toReplace(String fileOld, NewPhoto newPhoto){
        this.store(newPhoto);

        if (fileOld !=null){
            this.remove(fileOld);
        }
    }


    default String manageNameFile(String name){
    return UUID.randomUUID().toString() + "_" + name;

    }

    @Builder
    @Getter
    class NewPhoto{
        private String nameFile;
        private InputStream inputStream;

    }


}
