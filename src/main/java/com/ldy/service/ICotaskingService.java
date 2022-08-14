package com.ldy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ldy.common.R;
import com.ldy.dto.CotaskingDto;
import com.ldy.entity.Cotasking;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sunqing
 * @since 2022-07-24
 */
public interface ICotaskingService extends IService<Cotasking> {

    R<String> addCotakAndBindIntelligences(CotaskingDto cotaskingDto);

    R<String> removeCotaskByIds(Long[] ids);

    R<String> updateStatus(Long[] ids, Integer status);
}
