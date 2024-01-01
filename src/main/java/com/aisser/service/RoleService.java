package com.aisser.service;

import com.aisser.model.dto.RoleDTO;
import com.aisser.model.dto.UserRoleDTO;
import com.aisser.model.entity.Role;
import com.aisser.model.vo.ConditionVO;
import com.aisser.model.dto.PageResultDTO;
import com.aisser.model.vo.RoleVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface RoleService extends IService<Role> {

    List<UserRoleDTO> listUserRoles();

    PageResultDTO<RoleDTO> listRoles(ConditionVO conditionVO);

    void saveOrUpdateRole(RoleVO roleVO);

    void deleteRoles(List<Integer> ids);

}
