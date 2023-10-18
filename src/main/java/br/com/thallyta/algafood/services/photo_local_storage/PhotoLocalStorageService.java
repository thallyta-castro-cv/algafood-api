package br.com.thallyta.algafood.services.photo_local_storage;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface PhotoLocalStorageService {
    void store(NewPhoto newPhoto);

    void remove(String nameFile);

    InputStream recover(String nameFile);

    default void replace(String nameFileFound, NewPhoto newPhoto){
       this.store(newPhoto);

       if(nameFileFound != null){
           this.remove(nameFileFound);
       }
    }

    default String generateFileName(String fileName) {
        return UUID.randomUUID().toString() + "_" + fileName;
    }

    @Builder
    @Getter
    class NewPhoto {
        private String fileName;
        private InputStream inputStream;
    }
}
