package com.crealo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;
    @Column(length = 200, nullable = false)
    private String name;
    @NaturalId
    @Column(updatable = false, nullable = false)
    private String email;
    @NaturalId
    @Column(length = 100, updatable = false, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    private boolean enabled = false;
    //private List<> authorities;
    //private List<Role> roles;

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

    @JsonIgnore
    @JsonProperty("password")
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        var user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
