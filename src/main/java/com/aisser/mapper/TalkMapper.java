package com.aisser.mapper;

import com.aisser.model.dto.TalkAdminDTO;
import com.aisser.model.dto.TalkDTO;
import com.aisser.model.entity.Talk;
import com.aisser.model.vo.ConditionVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalkMapper extends BaseMapper<Talk> {

    List<TalkDTO> listTalks(@Param("current") Long current, @Param("size") Long size);

    TalkDTO getTalkById(@Param("talkId") Integer talkId);

    List<TalkAdminDTO> listTalksAdmin(@Param("current") Long current, @Param("size") Long size, @Param("conditionVO") ConditionVO conditionVO);

    TalkAdminDTO getTalkByIdAdmin(@Param("talkId") Integer talkId);

}
