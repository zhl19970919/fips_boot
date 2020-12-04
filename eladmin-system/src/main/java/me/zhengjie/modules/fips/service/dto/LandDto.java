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
* @author zhl
* @date 2020-11-12
**/
@Data
public class LandDto implements Serializable {

    /** 土地ID */
    private Long id;

    /** 土地名称 */
    private String landName;

    /** 总量 */
    private Integer total;

    /** 剩余容量 */
    private Integer surplus;

    /** 负责人 */
    private String charge;

    /** 创建时间 */
    private Timestamp created;

    /** 创建人 */
    private String creator;

    /** 修改时间 */
    private Timestamp edited;

    /** 修改人 */
    private String editor;

    /** 逻辑删除:0=未删除,1=已删除 */
    private Integer deleted;
}