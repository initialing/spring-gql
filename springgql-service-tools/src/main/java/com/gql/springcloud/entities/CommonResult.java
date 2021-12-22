package com.gql.springcloud.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String description;
    private T data;

    public CommonResult(Integer code, String des){
        this(code, des, null);
    }

}
