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
package com.cl.dj.service.impl;

import com.cl.dj.domain.CustomInfo;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import com.cl.dj.repository.CustomInfoRepository;
import com.cl.dj.service.CustomInfoService;
import com.cl.dj.service.dto.CustomInfoDto;
import com.cl.dj.service.dto.CustomInfoQueryCriteria;
import com.cl.dj.service.mapstruct.CustomInfoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.util.IdUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import me.zhengjie.utils.PageResult;

/**
* @website https://eladmin.vip
* @description 服务实现
* @author cl
* @date 2024-07-21
**/
@Service
@RequiredArgsConstructor
public class CustomInfoServiceImpl implements CustomInfoService {

    private final CustomInfoRepository customInfoRepository;
    private final CustomInfoMapper customInfoMapper;

    @Override
    public PageResult<CustomInfoDto> queryAll(CustomInfoQueryCriteria criteria, Pageable pageable){
        Page<CustomInfo> page = customInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(customInfoMapper::toDto));
    }

    @Override
    public List<CustomInfoDto> queryAll(CustomInfoQueryCriteria criteria){
        return customInfoMapper.toDto(customInfoRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public CustomInfoDto findById(String id) {
        CustomInfo customInfo = customInfoRepository.findById(id).orElseGet(CustomInfo::new);
        ValidationUtil.isNull(customInfo.getId(),"CustomInfo","id",id);
        return customInfoMapper.toDto(customInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(CustomInfo resources) {
        resources.setId(IdUtil.simpleUUID()); 
        customInfoRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CustomInfo resources) {
        CustomInfo customInfo = customInfoRepository.findById(resources.getId()).orElseGet(CustomInfo::new);
        ValidationUtil.isNull( customInfo.getId(),"CustomInfo","id",resources.getId());
        customInfo.copy(resources);
        customInfoRepository.save(customInfo);
    }

    @Override
    public void deleteAll(String[] ids) {
        for (String id : ids) {
            customInfoRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<CustomInfoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (CustomInfoDto customInfo : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("创建人", customInfo.getCreateBy());
            map.put("创建日期", customInfo.getCreateTime());
            map.put("更新人", customInfo.getUpdateBy());
            map.put("更新日期", customInfo.getUpdateTime());
            map.put("所属部门", customInfo.getOrgCode());
            map.put("客户名称", customInfo.getCustomName());
            map.put("客户联系方式", customInfo.getCustomTel());
            map.put("客户地址", customInfo.getCustomAddr());
            map.put("业务员名称", customInfo.getBusinessName());
            map.put("类别/系别", customInfo.getType());
            map.put("状态", customInfo.getStatus());
            map.put("检查项", customInfo.getCheckItem());
            map.put("是否建群", customInfo.getCreateGroup());
            map.put("清洗情况", customInfo.getClearType());
            map.put("维修情况", customInfo.getRepairType());
            map.put("安装情况", customInfo.getInstallType());
            map.put("安装单位", customInfo.getInstallCompany());
            map.put("项目经理", customInfo.getProjectManager());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}