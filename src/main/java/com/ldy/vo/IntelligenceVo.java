package com.ldy.vo;

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
public class IntelligenceVo extends Intelligence {

    private String categoryName;
    private String userName;
    private String unit;

}
