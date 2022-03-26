package org.study.functional.model;

import lombok.Data;

import java.util.Set;

@Data
public class Artist {

    private Set<Singer> singers;

}
