package com.bs.springblog.domain.Reply;


import com.bs.springblog.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Entity
@Table(name = "Replys")
public class Reply extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;


    /**
     * 생성자에 유저와 post넣기
     */
}
