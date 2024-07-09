package com.maverickshube.maverickshube.dtos.request;


import com.maverickshube.maverickshube.models.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UploadMediaRequest {
    private Long UserId;
    private MultipartFile mediaFile;
    private String description;
    private Category category;
}
