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
package me.zhengjie.modules.fips.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://el-admin.vip
* @description /
* @author ZHL
* @date 2020-12-01
**/
@Data
public class CropsDto implements Serializable {

    /** 农作物ID */
    private Long id;

    /** 农作物名称 */
    private String cropsName;

    /** 施肥间隔 */
    private Integer fertilizationInterval;

    /** 施肥量/次 */
    private Integer fertilizerAmount;

    /** 浇水间隔 */
    private Integer wateringInterval;

    /** 成熟期 */
    private Timestamp maturity;

    /** 枯萎期 */
    private Timestamp withering;

    /** 适宜种植开始日期 */
    private Timestamp suitableSdate;

    /** 适宜种植结束日期 */
    private Timestamp suitableEdate;

    /** 仓储总量 */
    private Integer totalAmount;

    /** 备注 */
    private String remarks;
}