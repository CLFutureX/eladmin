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
package com.cl.dj.rest;

import me.zhengjie.annotation.Log;
import com.cl.dj.domain.CustomInfo;
import com.cl.dj.service.CustomInfoService;
import com.cl.dj.service.dto.CustomInfoQueryCriteria;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import me.zhengjie.utils.PageResult;
import com.cl.dj.service.dto.CustomInfoDto;

/**
* @website https://eladmin.vip
* @author cl
* @date 2024-07-21
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "客户信息管理管理")
@RequestMapping("/api/customInfo")
public class CustomInfoController {

    private final CustomInfoService customInfoService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('customInfo:list')")
    public void exportCustomInfo(HttpServletResponse response, CustomInfoQueryCriteria criteria) throws IOException {
        customInfoService.download(customInfoService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询客户信息管理")
    @ApiOperation("查询客户信息管理")
    @PreAuthorize("@el.check('customInfo:list')")
    public ResponseEntity<PageResult<CustomInfoDto>> queryCustomInfo(CustomInfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(customInfoService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增客户信息管理")
    @ApiOperation("新增客户信息管理")
    @PreAuthorize("@el.check('customInfo:add')")
    public ResponseEntity<Object> createCustomInfo(@Validated @RequestBody CustomInfo resources){
        customInfoService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改客户信息管理")
    @ApiOperation("修改客户信息管理")
    @PreAuthorize("@el.check('customInfo:edit')")
    public ResponseEntity<Object> updateCustomInfo(@Validated @RequestBody CustomInfo resources){
        customInfoService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除客户信息管理")
    @ApiOperation("删除客户信息管理")
    @PreAuthorize("@el.check('customInfo:del')")
    public ResponseEntity<Object> deleteCustomInfo(@RequestBody String[] ids) {
        customInfoService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}