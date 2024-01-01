package com.aisser.event;

import com.aisser.model.entity.OperationLog;
import org.springframework.context.ApplicationEvent;

public class OperationLogEvent extends ApplicationEvent {

    public OperationLogEvent(OperationLog operationLog) {
        super(operationLog);
    }
}
