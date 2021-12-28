package com.gql.springcloud.entities;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Integer id;

    private String account;

    private String password;
}
