package com.ldy.dto;

import com.ldy.entity.Cotasking;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CotaskingDto extends Cotasking implements Serializable {
    private String ids;
    private String intelligenceIds;
}
