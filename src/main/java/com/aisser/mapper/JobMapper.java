package com.aisser.mapper;

import com.aisser.model.dto.JobDTO;
import com.aisser.model.entity.Job;
import com.aisser.model.vo.JobSearchVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobMapper extends BaseMapper<Job> {

    Integer countJobs(@Param("jobSearchVO") JobSearchVO jobSearchVO);

    List<JobDTO> listJobs(@Param("current") Long current, @Param("size") Long size, @Param("jobSearchVO")JobSearchVO jobSearchVO);

    List<String> listJobGroups();

}
