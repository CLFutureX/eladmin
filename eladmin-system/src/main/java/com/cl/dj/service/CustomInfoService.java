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
package com.cl.dj.service;

import com.cl.dj.domain.CustomInfo;
import com.cl.dj.service.dto.CustomInfoDto;
import com.cl.dj.service.dto.CustomInfoQueryCriteria;
import org.springframework.data.domain.Pageable;
import java.util.Map;
import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import me.zhengjie.utils.PageResult;

/**
* @website https://eladmin.vip
* @description 服务接口
* @author cl
* @date 2024-07-21
**/
public interface CustomInfoService {

    /**
    * 查询数据分页
    * @param criteria 条件
    * @param pageable 分页参数
    * @return Map<String,Object>
    */
    PageResult<CustomInfoDto> queryAll(CustomInfoQueryCriteria criteria, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param criteria 条件参数
    * @return List<CustomInfoDto>
    */
    List<CustomInfoDto> queryAll(CustomInfoQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return CustomInfoDto
     */
    CustomInfoDto findById(String id);

    /**
    * 创建
    * @param resources /
    */
    void create(CustomInfo resources);

    /**
    * 编辑
    * @param resources /
    */
    void update(CustomInfo resources);

    /**
    * 多选删除
    * @param ids /
    */
    void deleteAll(String[] ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    void download(List<CustomInfoDto> all, HttpServletResponse response) throws IOException;
}