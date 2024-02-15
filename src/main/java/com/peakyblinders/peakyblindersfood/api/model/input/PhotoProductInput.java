package com.peakyblinders.peakyblindersfood.api.model.input;

import com.peakyblinders.peakyblindersfood.core.validation.FileContentType;
import com.peakyblinders.peakyblindersfood.core.validation.FileSize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PhotoProductInput {

    @NotNull
    @FileSize(max = "1MB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    private MultipartFile file;
    @NotBlank
    private String description;
}
