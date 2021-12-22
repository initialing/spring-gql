package com.gql.springcloud.enums;

public enum ResEnum {
    SUCCESS(200,"success");

    private String des;
    private Integer code;

    ResEnum(Integer code, String des){
        this.des = des;
        this.code = code;
    }
    public Integer getCode(){
        return this.code;
    }
    public String getDes(){
        return this.des;
    }
}
