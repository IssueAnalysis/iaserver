package com.issue.iaserver.data.service;

import com.issue.iaserver.data.mysql.entity.FocusDO;

import java.util.*;

/**
 * 操作关注点的接口
 * User: 钟镇鸿
 * Date: 2020/4/20
 * Time: 19:41
 * Description:
 */
public interface FocusService {

    /**获取全部的Focus*/
    List<FocusDO> getAllFocus();
}
