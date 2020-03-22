package com.boss.learning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 字典
 * @author Zzwen
 * @date 2020/3/16 17:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryType implements Serializable {
    private Long dictionaryTypeId;
    private String typeName;
    private String remarks;

    public DictionaryType(String typeName, String remarks){
        this(null,typeName,remarks);
    }
}
