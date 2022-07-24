package com.ldy.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author ldy
 * @Date 2022/7/24 17:08
 * @ClassName TokenVo
 * @Description TODO
 * @Version v1.0
 */
@Data
public class TokenVo implements Serializable {

    //tokenId
    private Long id;

    private Long userId;

    private String name;

    private BigDecimal currentToken;

    private BigDecimal blockToken;

    private LocalDateTime updateTime;

    private Integer status;

}
