package br.com.thallyta.algafood.services.storage.photo_local_storage;

import br.com.thallyta.algafood.core.exceptions.StorageException;
import br.com.thallyta.algafood.core.storage.StorageProperties;
import br.com.thallyta.algafood.services.storage.PhotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import java.nio.file.Files;
import java.nio.file.Path;

public class PhotoLocalStorageService implements PhotoStorageService {

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public void store(NewPhoto newPhoto) {
        try{
            Path filePath = getDirectoryPhotos(newPhoto.getFileName());
            FileCopyUtils.copy(newPhoto.getInputStream(), Files.newOutputStream(filePath));
        } catch (Exception e) {
            throw  new StorageException("Não foi possível armazenar o arquivo.", e);
        }
    }

    @Override
    public void remove(String nameFile) {
        try{
            Path filePath = getDirectoryPhotos(nameFile);
            Files.deleteIfExists(filePath);
        } catch (Exception e){
            throw  new StorageException("Não foi possível excluir o arquivo.", e);
        }
    }

    @Override
    public RecoverPhoto recover(String nameFile) {
        try {
            Path pathFile = getDirectoryPhotos(nameFile);
            return RecoverPhoto.builder()
                    .inputStream(Files.newInputStream(pathFile))
                    .build();
        } catch (Exception e) {
            throw new StorageException("Não foi possível recuperar o arquivo.", e);
        }
    }

    private Path getDirectoryPhotos(String fileName) {
        return storageProperties.getLocal().getDirectoryPhotos()
                .resolve(Path.of(fileName));
    }

}
