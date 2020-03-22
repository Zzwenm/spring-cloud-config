package com.boss.learning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 职位
 *
 * @author Zzwen
 * @date 2020/3/16 17:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dictionary implements Serializable {
    /**
     * id
     */
    private Long dictionaryId;
    /**
     * 参数值
     */
    private String paramValue;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 状态
     */
    private boolean status;
    /**
     * 机构id
     */
    private Integer organizationId;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 修改人
     */
    private String updatedBy;
    /**
     * 更新时间
     */
    private Date updatedTime;
    /**
     * 版本
     */
    private String version;
    /**
     * 字典类型id
     */
    private Long dictionaryTypeId;

}
