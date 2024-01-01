package com.aisser.service;

import com.aisser.model.dto.LabelOptionDTO;
import com.aisser.model.dto.ResourceDTO;
import com.aisser.model.entity.Resource;
import com.aisser.model.vo.ConditionVO;
import com.aisser.model.vo.ResourceVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ResourceService extends IService<Resource> {

    void importSwagger();

    void saveOrUpdateResource(ResourceVO resourceVO);

    void deleteResource(Integer resourceId);

    List<ResourceDTO> listResources(ConditionVO conditionVO);

    List<LabelOptionDTO> listResourceOption();

}
