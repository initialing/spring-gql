package com.gql.springcloud.entities;


import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWTModel {

    private Integer id;

    private String userName;

    private List<String> authorities;
}
