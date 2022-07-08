package com.ldy.pojo;

import lombok.Data;

/**
 * @Author ldy
 * @Date 2022/7/8 22:21
 * @ClassName UserIntelligence
 * @Description 用户拥有情报
 * @Version v1.0
 */
@Data
public class UserIntelligence {
    private Long id;
    private Long userId;
    private Long intelligenceId;
}
