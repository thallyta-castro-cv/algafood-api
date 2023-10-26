package br.com.thallyta.algafood.core.storage;

import com.amazonaws.regions.Regions;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Getter
@Setter
@Component
@ConfigurationProperties("algafood.storage")
public class StorageProperties {

    private Local local = new Local();
    private S3 s3 = new S3();
    private StorageType type = StorageType.LOCAL;

    public enum StorageType {
        LOCAL, S3
    }

    @Getter
    @Setter
    public static class Local {
        private Path directoryPhotos;
    }

    @Getter
    @Setter
    public static class S3 {
        private String accessKey;
        private String secretKey;
        private String bucket;
        private String folder;
        private Regions region;
    }

}
