package br.com.thallyta.algafood.services.storage;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;
import java.util.UUID;

public interface PhotoStorageService {
    void store(NewPhoto newPhoto);

    void remove(String nameFile);

    RecoverPhoto recover(String nameFile);

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
        private String contentType;
        private InputStream inputStream;
    }

    @Builder
    @Getter
    class RecoverPhoto {
        private InputStream inputStream;
        private String url;

        public boolean hasUrl() {
            return url != null;
        }
    }

}
