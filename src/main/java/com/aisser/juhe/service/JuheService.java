package com.aisser.juhe.service;

import com.aisser.juhe.model.dto.JuheNetworkhotResult;
import com.aisser.juhe.model.dto.JuheSoupResult;

/**
 * @author jonk
 * @Description:
 * @date 24/5/2023 下午2:05
 */
public interface JuheService {

    public JuheNetworkhotResult queryNetworkhot();

    JuheSoupResult soup();
}
