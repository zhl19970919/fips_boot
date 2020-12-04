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
import me.zhengjie.modules.fips.domain.Land;
import me.zhengjie.modules.fips.service.LandService;
import me.zhengjie.modules.fips.service.dto.LandQueryCriteria;
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
* @author zhl
* @date 2020-11-12
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "land管理")
@RequestMapping("/api/land")
public class LandController {

    private final LandService LandService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('Land:list')")
    public void download(HttpServletResponse response, LandQueryCriteria criteria) throws IOException {
        LandService.download(LandService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询land")
    @ApiOperation("查询land")
    @PreAuthorize("@el.check('Land:list')")
    public ResponseEntity<Object> query(LandQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(LandService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增land")
    @ApiOperation("新增land")
    @PreAuthorize("@el.check('Land:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody Land resources){
        resources.setSurplus(resources.getTotal());
        return new ResponseEntity<>(LandService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改land")
    @ApiOperation("修改land")
    @PreAuthorize("@el.check('Land:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody Land resources){
        LandService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除land")
    @ApiOperation("删除land")
    @PreAuthorize("@el.check('Land:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Long[] ids) {
        LandService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}