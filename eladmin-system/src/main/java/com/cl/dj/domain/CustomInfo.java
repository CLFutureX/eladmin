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
package com.cl.dj.domain;

import lombok.Data;
import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.io.Serializable;

/**
* @website https://eladmin.vip
* @description /
* @author cl
* @date 2024-07-21
**/
@Entity
@Data
@Table(name="custom_info")
public class CustomInfo implements Serializable {

    @Id
    @Column(name = "`id`")
    @ApiModelProperty(value = "id主键")
    private String id;

    @Column(name = "`create_by`")
    @ApiModelProperty(value = "创建人")
    private String createBy;

    @Column(name = "`create_time`")
    @ApiModelProperty(value = "创建日期")
    private Timestamp createTime;

    @Column(name = "`update_by`")
    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @Column(name = "`update_time`")
    @ApiModelProperty(value = "更新日期")
    private Timestamp updateTime;

    @Column(name = "`org_code`")
    @ApiModelProperty(value = "所属部门")
    private String orgCode;

    @Column(name = "`custom_name`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "客户名称")
    private String customName;

    @Column(name = "`custom_tel`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "客户联系方式")
    private String customTel;

    @Column(name = "`custom_addr`")
    @ApiModelProperty(value = "客户地址")
    private String customAddr;

    @Column(name = "`business_name`",nullable = false)
    @NotBlank
    @ApiModelProperty(value = "业务员名称")
    private String businessName;

    @Column(name = "`type`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "类别/系别")
    private Integer type;

    @Column(name = "`status`",nullable = false)
    @NotNull
    @ApiModelProperty(value = "状态")
    private Integer status;

    @Column(name = "`check_item`")
    @ApiModelProperty(value = "检查项")
    private Integer checkItem;

    @Column(name = "`create_group`")
    @ApiModelProperty(value = "是否建群")
    private Integer createGroup;

    @Column(name = "`clear_type`")
    @ApiModelProperty(value = "清洗情况")
    private Integer clearType;

    @Column(name = "`repair_type`")
    @ApiModelProperty(value = "维修情况")
    private Integer repairType;

    @Column(name = "`install_type`")
    @ApiModelProperty(value = "安装情况")
    private Integer installType;

    @Column(name = "`install_company`")
    @ApiModelProperty(value = "安装单位")
    private String installCompany;

    @Column(name = "`project_manager`")
    @ApiModelProperty(value = "项目经理")
    private String projectManager;

    public void copy(CustomInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
