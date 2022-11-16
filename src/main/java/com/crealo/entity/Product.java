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
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Min(0)
    private float price;
    @NotBlank
    @Column(length = 100, nullable = false)
    private String name;
    @NotBlank
    @Column(length = 4000, nullable = false)
    private String description;
    @Builder.Default
    @ColumnDefault("false")
    @Column(nullable = false)
    private boolean enabled = false;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "product_tags",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "tag_id"})
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    private List<Tag> tags = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "product_views",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "view_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "view_id"})
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    private List<View> views = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "product_appearances",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "appearance_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "appearance_id"})
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    private List<Appearance> appearances = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("product")
    @LazyCollection(LazyCollectionOption.TRUE)
    @ToString.Exclude
    private List<ProductReview> reviews = new ArrayList<>();

    @Builder.Default
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at", updatable = false, nullable = false, columnDefinition = "timestamp with time zone")
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at", nullable = false, columnDefinition = "timestamp with time zone")
    @UpdateTimestamp
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        var product = (Product) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
