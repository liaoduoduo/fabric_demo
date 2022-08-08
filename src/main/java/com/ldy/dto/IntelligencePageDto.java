package com.ldy.dto;

import com.ldy.entity.Intelligence;
import com.ldy.entity.IntelligenceCategory;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author ldy
 * @Description TODO
 * @Version v1.0
 * @Date 2022/8/8 11:20
 */
@Data
@Builder
public class IntelligencePageDto implements Serializable {
    private int page;
    private int pageSize;
    private Integer status;
    private String userName;
    private Long intelligenceCategoryId;
    private String unit;
    private String name;
}
