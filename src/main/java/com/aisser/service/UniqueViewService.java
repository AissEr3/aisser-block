package com.aisser.service;

import com.aisser.model.dto.UniqueViewDTO;
import com.aisser.model.entity.UniqueView;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UniqueViewService extends IService<UniqueView> {

    List<UniqueViewDTO> listUniqueViews();

}
