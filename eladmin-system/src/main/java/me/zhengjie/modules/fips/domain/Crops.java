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
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author ZHL
* @date 2020-12-01
**/
@Entity
@Data
@Table(name="fips_crops")
public class Crops implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "农作物ID")
    private Long id;

    @Column(name = "crops_name")
    @ApiModelProperty(value = "农作物名称")
    private String cropsName;

    @Column(name = "fertilization_interval")
    @ApiModelProperty(value = "施肥间隔")
    private Integer fertilizationInterval;

    @Column(name = "fertilizer_amount")
    @ApiModelProperty(value = "施肥量/次")
    private Integer fertilizerAmount;

    @Column(name = "watering_interval")
    @ApiModelProperty(value = "浇水间隔")
    private Integer wateringInterval;

    @Column(name = "maturity")
    @ApiModelProperty(value = "成熟期")
    private Timestamp maturity;

    @Column(name = "withering")
    @ApiModelProperty(value = "枯萎期")
    private Timestamp withering;

    @Column(name = "suitable_sdate")
    @ApiModelProperty(value = "适宜种植开始日期")
    private Timestamp suitableSdate;

    @Column(name = "suitable_edate")
    @ApiModelProperty(value = "适宜种植结束日期")
    private Timestamp suitableEdate;

    @Column(name = "total_amount")
    @ApiModelProperty(value = "仓储总量")
    private Integer totalAmount;

    @Column(name = "remarks")
    @ApiModelProperty(value = "备注")
    private String remarks;

    public void copy(Crops source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}