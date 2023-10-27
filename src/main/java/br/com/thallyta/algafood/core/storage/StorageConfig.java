package br.com.thallyta.algafood.core.storage;

import br.com.thallyta.algafood.services.storage.PhotoStorageService;
import br.com.thallyta.algafood.services.storage.photo_cloud_storage.PhotoS3StorageService;
import br.com.thallyta.algafood.services.storage.photo_local_storage.PhotoLocalStorageService;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {

    @Autowired
    private StorageProperties storageProperties;

    @Bean
    public AmazonS3 amazonS3() {
        var credentials = new BasicAWSCredentials(
                storageProperties.getS3().getAccessKey(), storageProperties.getS3().getSecretKey());

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(storageProperties.getS3().getRegion())
                .build();
    }

    @Bean
    public PhotoStorageService photoStorageService() {
        if(StorageProperties.StorageType.S3.equals(storageProperties.getType()))
            return new PhotoS3StorageService();
        else
            return new PhotoLocalStorageService();
    }

}
