package com.aisser.service;


import com.aisser.model.dto.JobLogDTO;
import com.aisser.model.entity.JobLog;
import com.aisser.model.vo.JobLogSearchVO;
import com.aisser.model.dto.PageResultDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface JobLogService extends IService<JobLog> {

    PageResultDTO<JobLogDTO> listJobLogs(JobLogSearchVO jobLogSearchVO);

    void deleteJobLogs(List<Integer> ids);

    void cleanJobLogs();

    List<String> listJobLogGroups();

}
