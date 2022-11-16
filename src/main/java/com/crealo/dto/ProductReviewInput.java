package com.crealo.dto;

import com.crealo.dto.ids.IdInt;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class ProductReviewInput {

    @NotBlank
    private String comment;
    @Min(0)
    private Byte rating;
    //private User user;
    //private Product product;
    private IdInt appearance;
}
