package com.ldy.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author ldy
 * @Date 2022/7/24 20:46
 * @ClassName UserVo
 * @Description TODO
 * @Version v1.0
 */
@Data
public class UserVo implements Serializable {
    //userId
    private Long id;

    private String username;

    private String name;

    private String phone;

    private Long tokenId;

    //单位
    private String unit;

    private LocalDateTime updateTime;

    private Integer status;
}
