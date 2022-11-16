package com.crealo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Entity
@Table(name = "product_reviews")
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //private Integer sizeId; // sizeIds
    @NotBlank
    @Column(length = 500, nullable = false)
    private String comment;
    @Min(0)
    private byte rating;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonIgnoreProperties("reviews")
    private Product product;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "appearance_id")
    private Appearance appearance;

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
        var that = (ProductReview) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
