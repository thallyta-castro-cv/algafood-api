package br.com.thallyta.algafood.services.photo_local_storage;

import br.com.thallyta.algafood.core.exceptions.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PhotoLocalStorageServiceImpl implements PhotoLocalStorageService {

    @Value("${algafood.storage.local.directory-photos}")
    private Path directoryPhotos;

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

    private Path getDirectoryPhotos(String fileName) {
        return directoryPhotos.resolve(Path.of(fileName));
    }

}
