package com.aisser.service.impl;

import com.aisser.constant.CommonConstant;
import com.aisser.model.dto.RoleDTO;
import com.aisser.model.dto.UserRoleDTO;
import com.aisser.model.entity.Role;
import com.aisser.model.entity.RoleMenu;
import com.aisser.model.entity.RoleResource;
import com.aisser.model.entity.UserRole;
import com.aisser.exception.BizException;
import com.aisser.handler.FilterInvocationSecurityMetadataSourceImpl;
import com.aisser.mapper.RoleMapper;
import com.aisser.mapper.UserRoleMapper;
import com.aisser.service.RoleMenuService;
import com.aisser.service.RoleResourceService;
import com.aisser.service.RoleService;
import com.aisser.util.BeanCopyUtil;
import com.aisser.util.PageUtil;
import com.aisser.model.vo.ConditionVO;
import com.aisser.model.dto.PageResultDTO;
import com.aisser.model.vo.RoleVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleResourceService roleResourceService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private FilterInvocationSecurityMetadataSourceImpl filterInvocationSecurityMetadataSource;

    @Override
    public List<UserRoleDTO> listUserRoles() {
        List<Role> roleList = roleMapper.selectList(new LambdaQueryWrapper<Role>()
                .select(Role::getId, Role::getRoleName));
        return BeanCopyUtil.copyList(roleList, UserRoleDTO.class);
    }

    @SneakyThrows
    @Override
    public PageResultDTO<RoleDTO> listRoles(ConditionVO conditionVO) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<Role>()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), Role::getRoleName, conditionVO.getKeywords());
        CompletableFuture<Integer> asyncCount = CompletableFuture.supplyAsync(() -> roleMapper.selectCount(queryWrapper));
        List<RoleDTO> roleDTOs = roleMapper.listRoles(PageUtil.getLimitCurrent(), PageUtil.getSize(), conditionVO);
        return new PageResultDTO<>(roleDTOs, asyncCount.get());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdateRole(RoleVO roleVO) {
        Role roleCheck = roleMapper.selectOne(new LambdaQueryWrapper<Role>()
                .select(Role::getId)
                .eq(Role::getRoleName, roleVO.getRoleName()));
        if (Objects.nonNull(roleCheck) && !(roleCheck.getId().equals(roleVO.getId()))) {
            throw new BizException("该角色存在");
        }
        Role role = Role.builder()
                .id(roleVO.getId())
                .roleName(roleVO.getRoleName())
                .isDisable(CommonConstant.FALSE)
                .build();
        this.saveOrUpdate(role);
        if (Objects.nonNull(roleVO.getResourceIds())) {
            if (Objects.nonNull(roleVO.getId())) {
                roleResourceService.remove(new LambdaQueryWrapper<RoleResource>()
                        .eq(RoleResource::getRoleId, roleVO.getId()));
            }
            List<RoleResource> roleResourceList = roleVO.getResourceIds().stream()
                    .map(resourceId -> RoleResource.builder()
                            .roleId(role.getId())
                            .resourceId(resourceId)
                            .build())
                    .collect(Collectors.toList());
            roleResourceService.saveBatch(roleResourceList);
            filterInvocationSecurityMetadataSource.clearDataSource();
        }
        if (Objects.nonNull(roleVO.getMenuIds())) {
            if (Objects.nonNull(roleVO.getId())) {
                roleMenuService.remove(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, roleVO.getId()));
            }
            List<RoleMenu> roleMenuList = roleVO.getMenuIds().stream()
                    .map(menuId -> RoleMenu.builder()
                            .roleId(role.getId())
                            .menuId(menuId)
                            .build())
                    .collect(Collectors.toList());
            roleMenuService.saveBatch(roleMenuList);
        }
    }

    @Override
    public void deleteRoles(List<Integer> roleIdList) {
        Integer count = userRoleMapper.selectCount(new LambdaQueryWrapper<UserRole>()
                .in(UserRole::getRoleId, roleIdList));
        if (count > 0) {
            throw new BizException("该角色下存在用户");
        }
        roleMapper.deleteBatchIds(roleIdList);
    }

}
