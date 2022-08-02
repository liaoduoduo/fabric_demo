package com.ldy.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author ldy
 * @Date 2022/7/25 1:34
 * @ClassName BuyIntelligenceDto
 * @Description TODO
 * @Version v1.0
 */
@Data
public class BuyIntelligenceDto implements Serializable {
    private Long id;
    private String password;
}
