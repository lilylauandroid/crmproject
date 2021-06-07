package com.fang.net;

import java.util.Map;

/**
 * header 管理的接口类，便于继承变化
 * Created by User on 2019/3/14.
 */

public interface HeaderManager {
    /**
     * 实时获取header，各云自己实现
     * @return header的map
     */
    Map<String,String> getHerder();
}
