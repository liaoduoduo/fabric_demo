package com.ldy.vo;

import com.ldy.entity.Cotasking;
import com.ldy.entity.Intelligence;
import lombok.Data;

import java.util.List;

/**
 * @Author ldy
 * @Description TODO
 * @Version v1.0
 * @Date 2022/8/2 16:11
 */
@Data
public class CotaskingVo extends Cotasking {
    private String user;
    private List<String> intelligenceNames;
}
