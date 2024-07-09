package com.maverickshube.maverickshube.dtos.request;

import com.maverickshube.maverickshube.models.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class UpdateMediaRequest {
    private Long id;
    private MultipartFile mediaFile;
    private String description;
    private Category Category;
}
