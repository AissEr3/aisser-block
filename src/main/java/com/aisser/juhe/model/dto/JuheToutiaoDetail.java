package com.aisser.juhe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data  
@Builder  
@NoArgsConstructor  
@AllArgsConstructor  
public class JuheToutiaoDetail {
private String Uniquekey;
private JuheToutiaoDetailDetail Detail;
private String Content;
}