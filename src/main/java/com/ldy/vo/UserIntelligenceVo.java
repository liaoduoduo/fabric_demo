package com.ldy.vo;

import com.ldy.entity.Intelligence;
import lombok.Data;

/**
 * @Author ldy
 * @Date 2022/7/22 16:27
 * @ClassName UserIntelligenceVo
 * @Description TODO
 * @Version v1.0
 */
@Data
public class UserIntelligenceVo extends Intelligence {
    private Long intelligenceId;
    private Long formUserId;
    private Long toUserId;
    private String origin;
    private String intelligenceCategoryName;
    private String fromUserName;
    private String toUserName;
}
