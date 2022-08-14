package com.ldy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ldy.entity.DecideInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
public interface IDecideInfoService extends IService<DecideInfo> {

    List<String> getTaskDetailFiledByTaskId(Long id);
}
