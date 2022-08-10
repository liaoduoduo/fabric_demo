package com.ldy.dto;

import com.ldy.entity.Cotasking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CotaskingDto extends Cotasking implements Serializable {
    private String intelligenceIds;
}
