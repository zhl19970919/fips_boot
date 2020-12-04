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

import me.zhengjie.modules.fips.domain.Crops;
import me.zhengjie.utils.ValidationUtil;
import me.zhengjie.utils.FileUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.fips.repository.CropsRepository;
import me.zhengjie.modules.fips.service.CropsService;
import me.zhengjie.modules.fips.service.dto.CropsDto;
import me.zhengjie.modules.fips.service.dto.CropsQueryCriteria;
import me.zhengjie.modules.fips.service.mapstruct.CropsMapper;
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
* @author ZHL
* @date 2020-12-01
**/
@Service
@RequiredArgsConstructor
public class CropsServiceImpl implements CropsService {

    private final CropsRepository CropsRepository;
    private final CropsMapper CropsMapper;

    @Override
    public Map<String,Object> queryAll(CropsQueryCriteria criteria, Pageable pageable){
        Page<Crops> page = CropsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(CropsMapper::toDto));
    }

    @Override
    public List<CropsDto> queryAll(CropsQueryCriteria criteria){
        return CropsMapper.toDto(CropsRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public CropsDto findById(Long id) {
        Crops Crops = CropsRepository.findById(id).orElseGet(Crops::new);
        ValidationUtil.isNull(Crops.getId(),"Crops","id",id);
        return CropsMapper.toDto(Crops);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CropsDto create(Crops resources) {
        return CropsMapper.toDto(CropsRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Crops resources) {
        Crops Crops = CropsRepository.findById(resources.getId()).orElseGet(Crops::new);
        ValidationUtil.isNull( Crops.getId(),"Crops","id",resources.getId());
        Crops.copy(resources);
        CropsRepository.save(Crops);
    }

    @Override
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {
            CropsRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<CropsDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (CropsDto Crops : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("农作物名称", Crops.getCropsName());
            map.put("施肥间隔", Crops.getFertilizationInterval());
            map.put("施肥量/次", Crops.getFertilizerAmount());
            map.put("浇水间隔", Crops.getWateringInterval());
            map.put("成熟期", Crops.getMaturity());
            map.put("枯萎期", Crops.getWithering());
            map.put("适宜种植开始日期", Crops.getSuitableSdate());
            map.put("适宜种植结束日期", Crops.getSuitableEdate());
            map.put("仓储总量", Crops.getTotalAmount());
            map.put("备注", Crops.getRemarks());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}