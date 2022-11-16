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
import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Entity
@Table(name = "designs")
public class Design {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    @Column(length = 100, nullable = false)
    private String name;
    @Column(length = 4000)
    private String description;

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
        var design = (Design) o;
        return id != null && Objects.equals(id, design.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
