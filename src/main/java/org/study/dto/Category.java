package org.study.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {

    private int id;
    private Integer pid;
    private int name;
}
