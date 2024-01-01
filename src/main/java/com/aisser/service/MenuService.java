package com.aisser.service;

import com.aisser.model.dto.LabelOptionDTO;
import com.aisser.model.dto.MenuDTO;
import com.aisser.model.dto.UserMenuDTO;
import com.aisser.model.entity.Menu;
import com.aisser.model.vo.ConditionVO;
import com.aisser.model.vo.IsHiddenVO;
import com.aisser.model.vo.MenuVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface MenuService extends IService<Menu> {

    List<MenuDTO> listMenus(ConditionVO conditionVO);

    void saveOrUpdateMenu(MenuVO menuVO);

    void updateMenuIsHidden(IsHiddenVO isHiddenVO);

    void deleteMenu(Integer menuId);

    List<LabelOptionDTO> listMenuOptions();

    List<UserMenuDTO> listUserMenus();

}
