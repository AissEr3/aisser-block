package com.aurora.event;

import com.aurora.model.entity.OperationLog;
import org.springframework.context.ApplicationEvent;

public class OperationLogEvent extends ApplicationEvent {

    public OperationLogEvent(OperationLog operationLog) {
        super(operationLog);
    }
}
