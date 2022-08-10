package com.ldy.dto;

import com.ldy.entity.CotaskingIntelligence;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class CotaskingIntelligenceDto extends CotaskingIntelligence {
    private String intelligenceIds;
}
