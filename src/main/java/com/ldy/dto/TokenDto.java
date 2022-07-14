package com.ldy.dto;

import com.ldy.entity.Token;
import lombok.Data;

/**
 * @Author ldy
 * @Date 2022/7/14 11:27
 * @ClassName TokenDto
 * @Description TODO
 * @Version v1.0
 */
@Data
public class TokenDto extends Token {
    //用户名
    private String name;
}
