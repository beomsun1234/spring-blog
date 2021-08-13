package com.bs.springblog.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import java.util.UUID;

@NoArgsConstructor
@Getter
public class Guest {

    private Long id;
    private String name;

    @Builder
    public Guest(Long id, String name){
        this.id = id;
        this.name = name;
    }
}
