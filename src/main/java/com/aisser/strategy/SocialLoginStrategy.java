package com.aisser.strategy;


import com.aisser.model.dto.UserInfoDTO;

public interface SocialLoginStrategy {

    UserInfoDTO login(String data);

}
