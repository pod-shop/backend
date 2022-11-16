package com.crealo.dto;

import com.crealo.dto.ids.IdInt;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryInput {

    private String name;
    private IdInt parent;
}
