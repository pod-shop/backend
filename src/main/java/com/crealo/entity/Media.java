package com.crealo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Entity
@Table(name = "medias")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(length = 2048, nullable = false)
    private URL url;
    @NotBlank
    @Column(nullable = false)
    private String name;
    @Column(name = "original_filename")
    private String originalFilename;
    @Column(name = "content_type", length = 200)
    private String contentType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Builder.Default
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", updatable = false, nullable = false, columnDefinition = "timestamp with time zone")
    @CreationTimestamp
    private ZonedDateTime createdAt = ZonedDateTime.now();

    @Builder.Default
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at", nullable = false, columnDefinition = "timestamp with time zone")
    @UpdateTimestamp
    private ZonedDateTime updatedAt = ZonedDateTime.now();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        var media = (Media) o;
        return id != null && Objects.equals(id, media.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
