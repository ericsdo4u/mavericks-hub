package com.maverickshube.maverickshube.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maverickshube.maverickshube.models.Category;
import com.maverickshube.maverickshube.models.User;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

import static java.time.LocalTime.now;

@Setter
@Getter
@ToString
public class UploadMediaResponse {

    private Long id;
    @JsonProperty("media_url")
    private String url;
    private String description;
    private Category category;
    @Setter(AccessLevel.NONE)
    private LocalDateTime timeCreated;
    @ManyToOne
    private User user;

    @PrePersist
    private void setTimeCreated() {
        this.timeCreated = LocalDateTime.now();
    }
}
