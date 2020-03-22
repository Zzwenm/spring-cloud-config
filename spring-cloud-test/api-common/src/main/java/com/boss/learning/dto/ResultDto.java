package com.boss.learning.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Zzwen
 * @date 2020/3/16 15:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto<T> {
    private Integer code;
    private String message;
    private T data;

    public ResultDto(Integer code,String message){
        this(code,message,null);
    }
}
