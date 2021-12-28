package com.gql.springcloud.enums;

public enum ResEnum {
    SUCCESS(200,"success"),
    UNAUTH(401,"unauthorized"),
    AUTH_FAIL(402, "authorized fail");

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
