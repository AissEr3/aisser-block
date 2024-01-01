package com.aisser.service;

import com.aisser.model.dto.TalkAdminDTO;
import com.aisser.model.dto.TalkDTO;
import com.aisser.model.entity.Talk;
import com.aisser.model.vo.ConditionVO;
import com.aisser.model.dto.PageResultDTO;
import com.aisser.model.vo.TalkVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface TalkService extends IService<Talk> {

    PageResultDTO<TalkDTO> listTalks();

    TalkDTO getTalkById(Integer talkId);

    void saveOrUpdateTalk(TalkVO talkVO);

    void deleteTalks(List<Integer> talkIdList);

    PageResultDTO<TalkAdminDTO> listBackTalks(ConditionVO conditionVO);

    TalkAdminDTO getBackTalkById(Integer talkId);

}
