package com.maverickshube.maverickshube.models;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.EAGER;

@Entity
@Getter
@Setter
@Table(name="media")
public class Media {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private  Long id;

        private String url;

        private String description;

        @Enumerated(value = STRING)
        private Category category;

        @Setter(AccessLevel.NONE)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime timeCreated;

        @Setter(AccessLevel.NONE)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime timeUpdated;

        @ManyToOne
        private User uploader;

        @ManyToMany(mappedBy = "media", fetch = EAGER)
        private List<Playlist> playlist;


        @PrePersist
        public void setTimeCreated() {
                this.timeCreated = LocalDateTime.now();
        }
        @PreUpdate
        public void setTimeUpdated() {
                this.timeUpdated = LocalDateTime.now();
        }
}
