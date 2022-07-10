package com.ldy.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * xie tong task
 */
@Data
@ToString
@NoArgsConstructor
public class Cotasking {
    private Integer cotaskingId;
    private List<Integer> intlligenceIds;
    private List<Integer> taskIds;

    public Cotasking(Integer cotaskingId) {
        this.cotaskingId = cotaskingId;
        this.intlligenceIds = new ArrayList<>();;
        this.taskIds = new ArrayList<>();;
    }
}
