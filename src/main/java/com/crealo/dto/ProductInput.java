package com.crealo.dto;

import com.crealo.dto.ids.IdInt;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductInput {

    private Float price;
    private String name;
    private String description;
    private Boolean enabled;
    private IdInt category;
    private List<IdInt> tags;
    private List<IdInt> views;
    private List<IdInt> appearances;
}
