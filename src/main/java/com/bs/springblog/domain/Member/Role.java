package com.bs.springblog.domain.Member;


import lombok.Getter;

import javax.persistence.Enumerated;


@Getter
public enum Role {
    ROLE_USER, ROLE_ADMIN;
}
