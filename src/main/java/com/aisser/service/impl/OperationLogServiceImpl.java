package com.aisser.service.impl;

import com.aisser.model.dto.OperationLogDTO;
import com.aisser.model.entity.OperationLog;
import com.aisser.mapper.OperationLogMapper;
import com.aisser.service.OperationLogService;
import com.aisser.util.BeanCopyUtil;
import com.aisser.util.PageUtil;
import com.aisser.model.vo.ConditionVO;
import com.aisser.model.dto.PageResultDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements OperationLogService {

    @Override
    public PageResultDTO<OperationLogDTO> listOperationLogs(ConditionVO conditionVO) {
        Page<OperationLog> page = new Page<>(PageUtil.getCurrent(), PageUtil.getSize());
        Page<OperationLog> operationLogPage = this.page(page, new LambdaQueryWrapper<OperationLog>()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), OperationLog::getOptModule, conditionVO.getKeywords())
                .or()
                .like(StringUtils.isNotBlank(conditionVO.getKeywords()), OperationLog::getOptDesc, conditionVO.getKeywords())
                .orderByDesc(OperationLog::getId));
        List<OperationLogDTO> operationLogDTOs = BeanCopyUtil.copyList(operationLogPage.getRecords(), OperationLogDTO.class);
        return new PageResultDTO<>(operationLogDTOs, (int) operationLogPage.getTotal());
    }

}
