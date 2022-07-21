package com.ldy.dto;

import com.ldy.entity.Intelligence;
import lombok.Data;

/**
 * @Author ldy
 * @Date 2022/7/4 16:43
 * @ClassName IntelligenceDto
 * @Description TODO
 * @Version v1.0
 */
@Data
public class IntelligenceDto extends Intelligence {
    private String categoryName;
    private String origin;
    private String userName;
}
