package com.ldy.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;

@Data
@ToString
@NoArgsConstructor
public class RewardTask {
    private Integer rewardId;
    private List<Object> contents;
    private Integer publisherId;
    private String rewardUnit;
    private Date begin;
    private Date end;
    private Double miToken;
    private Boolean isUrgency;
    private Map<Integer,Object> acceptUsersAndResults;
    private Integer acceptRusultId;
    private Integer taskTypeId;
    private Boolean isSuccess;
    private String evaluate;
    private Double weight;

    public RewardTask(Integer rewardId, Integer publisherId, String rewardUnit, Date begin, Date end,
                      Integer acceptRusultId) {
        this.rewardId = rewardId;
        this.contents = new ArrayList<>();
        this.publisherId = publisherId;
        this.rewardUnit = rewardUnit;
        this.begin = begin;
        this.end = end;
        this.miToken = 1.0;
        this.isUrgency = false;
        this.acceptUsersAndResults = new HashMap<>();
        this.acceptRusultId = acceptRusultId;
        this.isSuccess = false;
    }
}
