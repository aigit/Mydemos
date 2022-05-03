package org.study.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Category {

    private int id;
    private Integer pid;
    private int name;

    private BigDecimal price;
}
