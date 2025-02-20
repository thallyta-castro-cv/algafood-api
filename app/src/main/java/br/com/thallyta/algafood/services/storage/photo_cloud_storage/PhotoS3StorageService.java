package br.com.thallyta.algafood.services.storage.photo_cloud_storage;

import br.com.thallyta.algafood.core.exceptions.StorageException;
import br.com.thallyta.algafood.core.storage.StorageProperties;
import br.com.thallyta.algafood.services.storage.PhotoStorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

public class PhotoS3StorageService implements PhotoStorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public void store(NewPhoto newPhoto) {
        try {
            String filePath = getPathFile(newPhoto.getFileName());
            var objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(newPhoto.getContentType());

            var putObjectRequest = new PutObjectRequest(
                    storageProperties.getS3().getBucket(),
                    filePath,
                    newPhoto.getInputStream(),
                    objectMetadata);

            amazonS3.putObject(putObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Não foi possível enviar arquivo para Amazon S3", e);
        }
    }

    @Override
    public void remove(String nameFile) {
        try {
            String filePath = getPathFile(nameFile);

            var deleteObjectRequest = new DeleteObjectRequest(
                    storageProperties.getS3().getBucket(), filePath);

            amazonS3.deleteObject(deleteObjectRequest);
        } catch (Exception e) {
            throw new StorageException("Não foi possível excluir arquivo na Amazon S3.", e);
        }
    }

    @Override
    public RecoverPhoto recover(String nameFile) {
        String filePath = getPathFile(nameFile);
        URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), filePath);

        return RecoverPhoto.builder()
                .url(url.toString())
                .build();
    }

    private String getPathFile(String fileName) {
        return String.format("%s/%s", storageProperties.getS3().getFolder(), fileName);
    }
}
