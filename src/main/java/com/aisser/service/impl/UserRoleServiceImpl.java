package com.aisser.service.impl;

import com.aisser.model.entity.UserRole;
import com.aisser.mapper.UserRoleMapper;
import com.aisser.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
