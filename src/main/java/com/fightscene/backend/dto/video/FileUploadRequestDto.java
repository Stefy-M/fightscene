package com.fightscene.backend.dto.video;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record FileUploadRequestDto (
	

    @NotBlank
    @Size(max = 150)
    String title,

    @Size(max = 1000)
    String description,

    @NotBlank
    @Size(max = 255)
    String originalFileName,

    @NotNull
    @Positive
    Long sizeBytes

) {}