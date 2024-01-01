package com.aisser.service;

import com.aisser.model.dto.FriendLinkAdminDTO;
import com.aisser.model.dto.FriendLinkDTO;
import com.aisser.model.entity.FriendLink;
import com.aisser.model.vo.ConditionVO;
import com.aisser.model.vo.FriendLinkVO;
import com.aisser.model.dto.PageResultDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface FriendLinkService extends IService<FriendLink> {

    List<FriendLinkDTO> listFriendLinks();

    PageResultDTO<FriendLinkAdminDTO> listFriendLinksAdmin(ConditionVO conditionVO);

    void saveOrUpdateFriendLink(FriendLinkVO friendLinkVO);

}
