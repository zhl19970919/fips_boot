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
package me.zhengjie.modules.fips.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.fips.domain.Crops;
import me.zhengjie.modules.fips.service.CropsService;
import me.zhengjie.modules.fips.service.dto.CropsQueryCriteria;
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

/**
* @website https://el-admin.vip
* @author ZHL
* @date 2020-12-01
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "crops管理")
@RequestMapping("/api/Crops")
public class CropsController {

    private final CropsService CropsService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('crops:list')")
    public void download(HttpServletResponse response, CropsQueryCriteria criteria) throws IOException {
        CropsService.download(CropsService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询crops")
    @ApiOperation("查询crops")
    @PreAuthorize("@el.check('crops:list')")
    public ResponseEntity<Object> query(CropsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(CropsService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增crops")
    @ApiOperation("新增crops")
    @PreAuthorize("@el.check('crops:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Crops resources){
        return new ResponseEntity<>(CropsService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改crops")
    @ApiOperation("修改crops")
    @PreAuthorize("@el.check('crops:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Crops resources){
        CropsService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除crops")
    @ApiOperation("删除crops")
    @PreAuthorize("@el.check('crops:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        CropsService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}