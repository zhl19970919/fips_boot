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
package me.zhengjie.modules.fips.service.impl;

import me.zhengjie.modules.fips.domain.Land;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.fips.repository.LandRepository;
import me.zhengjie.modules.fips.service.LandService;
import me.zhengjie.modules.fips.service.dto.LandDto;
import me.zhengjie.modules.fips.service.dto.LandQueryCriteria;
import me.zhengjie.modules.fips.service.mapstruct.LandMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

/**
* @website https://el-admin.vip
* @description 服务实现
* @author zhl
* @date 2020-11-12
**/
@Service
@RequiredArgsConstructor
public class LandServiceImpl implements LandService {

    private final LandRepository LandRepository;
    private final LandMapper LandMapper;

    @Override
    public Map<String,Object> queryAll(LandQueryCriteria criteria, Pageable pageable){
        Page<Land> page = LandRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(LandMapper::toDto));
    }

    @Override
    public List<LandDto> queryAll(LandQueryCriteria criteria){
        return LandMapper.toDto(LandRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public LandDto findById(Long id) {
        Land Land = LandRepository.findById(id).orElseGet(Land::new);
        ValidationUtil.isNull(Land.getId(),"Land","id",id);
        return LandMapper.toDto(Land);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LandDto create(Land resources) {
        return LandMapper.toDto(LandRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Land resources) {
        Land Land = LandRepository.findById(resources.getId()).orElseGet(Land::new);
        ValidationUtil.isNull( Land.getId(),"Land","id",resources.getId());
        Land.copy(resources);
        LandRepository.save(Land);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            LandRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<LandDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (LandDto Land : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("土地名称", Land.getLandName());
            map.put("总量", Land.getTotal());
            map.put("剩余容量", Land.getSurplus());
            map.put("负责人", Land.getCharge());
            map.put("创建时间", Land.getCreated());
            map.put("创建人", Land.getCreator());
            map.put("修改时间", Land.getEdited());
            map.put("修改人", Land.getEditor());
            map.put("逻辑删除:0=未删除,1=已删除", Land.getDeleted());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}