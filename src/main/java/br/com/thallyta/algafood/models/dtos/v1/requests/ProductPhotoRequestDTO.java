package br.com.thallyta.algafood.models.dtos.v1.requests;

import br.com.thallyta.algafood.core.validation.annotation.FileContentType;
import br.com.thallyta.algafood.core.validation.annotation.FileSize;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductPhotoRequestDTO {

    @Schema(description = "Arquivo da foto do produto (máximo 500KB, apenas JPG e PNG)")
    @NotNull(message="É necessário enviar um arquivo para completar o cadastro")
    @FileSize(max="5000KB", message="A foto deve ter o tamanho máximo de 500KB")
    @FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE },
                     message = "A foto deve ser do tipo PNG ou JPG")
    private MultipartFile file;

    @Schema(description = "Descrição da foto do produto")
    @NotBlank(message="É necessário informar uma descrição para a foto do produto")
    private String description;
}
