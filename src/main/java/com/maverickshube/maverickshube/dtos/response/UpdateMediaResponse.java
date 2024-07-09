package com.maverickshube.maverickshube.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maverickshube.maverickshube.models.Category;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;



@Setter
@Getter
public class UpdateMediaResponse {

    private  Long id;
    private String url;
    private String description;
    private Category category;
    @JsonProperty("created_at")
    private LocalDateTime timeCreated;
    @JsonProperty("updated_at")
    private LocalDateTime timeUpdated;
}
