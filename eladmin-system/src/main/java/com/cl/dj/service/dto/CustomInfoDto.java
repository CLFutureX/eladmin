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
package com.cl.dj.service.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author cl
* @date 2024-07-21
**/
@Data
public class CustomInfoDto implements Serializable {

    /** id主键 */
    private String id;

    /** 创建人 */
    private String createBy;

    /** 创建日期 */
    private Timestamp createTime;

    /** 更新人 */
    private String updateBy;

    /** 更新日期 */
    private Timestamp updateTime;

    /** 所属部门 */
    private String orgCode;

    /** 客户名称 */
    private String customName;

    /** 客户联系方式 */
    private String customTel;

    /** 客户地址 */
    private String customAddr;

    /** 业务员名称 */
    private String businessName;

    /** 类别/系别 */
    private Integer type;

    /** 状态 */
    private Integer status;

    /** 检查项 */
    private Integer checkItem;

    /** 是否建群 */
    private Integer createGroup;

    /** 清洗情况 */
    private Integer clearType;

    /** 维修情况 */
    private Integer repairType;

    /** 安装情况 */
    private Integer installType;

    /** 安装单位 */
    private String installCompany;

    /** 项目经理 */
    private String projectManager;
}