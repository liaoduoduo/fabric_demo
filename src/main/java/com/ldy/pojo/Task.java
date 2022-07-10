package com.ldy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task {
    private Integer taskId;
    private List<Object> contents;
    private String taskTpye;
    private Integer assignerId;
    private Integer cotaskingId;
    private Double miToken;
    private List<Integer> acceptUsersIds;
    private Date begin;
    private Date end;
    private String result;
    private String evaluate;
    private Double weight;
}
