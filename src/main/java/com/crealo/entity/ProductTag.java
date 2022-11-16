package com.crealo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serial;
import java.io.Serializable;

@Deprecated
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Builder
//@Entity
//@Table(name = "product_tags")
public class ProductTag {

    @EmbeddedId
    private ProductTag.ProductTagId id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    private Tag tag;

//    @NoArgsConstructor
//    @Embeddable
//    public record ProductTagId(@Column(name = "product_id") Integer productId,
//                               @Column(name = "tag_id") Integer tagId) implements Serializable {
//        @Serial
//        private static final long serialVersionUID = 8561803318382345600L;
//    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class ProductTagId implements Serializable {

        @Serial
        private static final long serialVersionUID = 8561803318382345600L;

        @Column(name = "product_id")
        private Integer productId;
        @Column(name = "tag_id")
        private Integer tagId;
    }
}
