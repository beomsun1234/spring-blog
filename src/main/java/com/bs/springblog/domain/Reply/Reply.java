package com.bs.springblog.domain.Reply;


import lombok.Getter;

import javax.persistence.*;


@Getter
@Entity
@Table(name = "Replys")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
