package com.aisser.event;

import com.aisser.model.entity.ExceptionLog;
import org.springframework.context.ApplicationEvent;

public class ExceptionLogEvent extends ApplicationEvent {
    public ExceptionLogEvent(ExceptionLog exceptionLog) {
        super(exceptionLog);
    }
}
