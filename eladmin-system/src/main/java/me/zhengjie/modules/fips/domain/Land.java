/*
*  Copyright 2019-2020 Zheng Jie
*
*  Licensed under the Apache License, Version 2.0 (the "License");
*  you may not use this file except in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software
*  distributed under the License is distributed on an "AS IS" BASIS,
*  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*  See the License for the specific language governing permissions and
*  limitations under the License.
*/
package me.zhengjie.modules.fips.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author zhl
* @date 2020-11-12
**/
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name="fips_land")
public class Land implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "土地ID")
    private Long id;

    @Column(name = "land_name")
    @ApiModelProperty(value = "土地名称")
    private String landName;

    @Column(name = "total")
    @ApiModelProperty(value = "总量")
    private Integer total;

    @Column(name = "surplus")
    @ApiModelProperty(value = "剩余容量")
    private Integer surplus;

    @Column(name = "charge")
    @ApiModelProperty(value = "负责人")
    private String charge;

    @Column(name = "created")
    @CreatedDate
    @ApiModelProperty(value = "创建时间")
    private Timestamp created;

    @Column(name = "creator")
    @CreatedBy
    @ApiModelProperty(value = "创建人")
    private String creator;

    @Column(name = "edited")
    @LastModifiedDate
    @ApiModelProperty(value = "修改时间")
    private Timestamp edited;

    @Column(name = "editor")
    @LastModifiedBy
    @ApiModelProperty(value = "修改人")
    private String editor;

    @Column(name = "deleted")
    @ApiModelProperty(value = "逻辑删除:0=未删除,1=已删除")
    private Integer deleted;

    public void copy(Land source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}