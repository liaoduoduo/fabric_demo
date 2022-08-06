package com.ldy.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author ldy
 * @Date 2022/7/24 22:17
 * @ClassName TokenDto
 * @Description TODO
 * @Version v1.0
 */
@Data
public class TokenDto implements Serializable {
    private Long id;

    private Long userId;

    private Integer status;

    private BigDecimal currentToken;

    private String password;
}
