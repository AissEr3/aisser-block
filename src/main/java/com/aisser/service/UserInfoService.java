package com.aisser.service;

import com.aisser.model.dto.PageResultDTO;
import com.aisser.model.dto.UserInfoDTO;
import com.aisser.model.dto.UserOnlineDTO;
import com.aisser.model.entity.UserInfo;
import com.aisser.model.vo.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

public interface UserInfoService extends IService<UserInfo> {

    void updateUserInfo(UserInfoVO userInfoVO);

    String updateUserAvatar(MultipartFile file);

    void saveUserEmail(EmailVO emailVO);

    void updateUserSubscribe(SubscribeVO subscribeVO);

    void updateUserRole(UserRoleVO userRoleVO);

    void updateUserDisable(UserDisableVO userDisableVO);

    PageResultDTO<UserOnlineDTO> listOnlineUsers(ConditionVO conditionVO);

    void removeOnlineUser(Integer userInfoId);

    UserInfoDTO getUserInfoById(Integer id);

}
