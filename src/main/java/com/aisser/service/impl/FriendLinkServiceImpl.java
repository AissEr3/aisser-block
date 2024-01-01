package com.aisser.service.impl;

import com.aisser.model.entity.FriendLink;
import com.aisser.mapper.FriendLinkMapper;
import com.aisser.model.dto.FriendLinkAdminDTO;
import com.aisser.model.dto.FriendLinkDTO;
import com.aisser.model.dto.PageResultDTO;
import com.aisser.model.vo.ConditionVO;
import com.aisser.model.vo.FriendLinkVO;
import com.aisser.service.FriendLinkService;
import com.aisser.util.BeanCopyUtil;
import com.aisser.util.PageUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendLinkServiceImpl extends ServiceImpl<FriendLinkMapper, FriendLink> implements FriendLinkService {

    @Autowired
    private FriendLinkMapper friendLinkMapper;

    @Override
    public List<FriendLinkDTO> listFriendLinks() {
        List<FriendLink> friendLinks = friendLinkMapper.selectList(null);
        return BeanCopyUtil.copyList(friendLinks, FriendLinkDTO.class);
    }

    @Override
    public PageResultDTO<FriendLinkAdminDTO> listFriendLinksAdmin(ConditionVO conditionVO) {
        Page<FriendLink> page = new Page<>(PageUtil.getCurrent(), PageUtil.getSize());
        Page<FriendLink> friendLinkPage = friendLinkMapper.selectPage(page, new LambdaQueryWrapper<FriendLink>()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), FriendLink::getLinkName, conditionVO.getKeywords()));
        List<FriendLinkAdminDTO> friendLinkBackDTOs = BeanCopyUtil.copyList(friendLinkPage.getRecords(), FriendLinkAdminDTO.class);
        return new PageResultDTO<>(friendLinkBackDTOs, (int) friendLinkPage.getTotal());
    }

    @Override
    public void saveOrUpdateFriendLink(FriendLinkVO friendLinkVO) {
        FriendLink friendLink = BeanCopyUtil.copyObject(friendLinkVO, FriendLink.class);
        this.saveOrUpdate(friendLink);
    }

}
