package com.ldy.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ldy.entity.CotaskingIntelligence;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ToString(callSuper = true)
public class CotaskIntelligenceVo extends CotaskingIntelligence {

    private Long cotaskingId;
    private Long intelligenceId;
    private String intelligenceName;
    private String intelligenceDesc;
    private String intelligenceCategoryName;
    private String fileHash;
    private BigDecimal token;
    private Integer status;
    private String username;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
