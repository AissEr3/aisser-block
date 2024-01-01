package com.aisser.mapper;

import com.aisser.model.entity.JobLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobLogMapper extends BaseMapper<JobLog> {

    List<String> listJobLogGroups();

}
