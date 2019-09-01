package com.memo.dao;

import com.memo.entity.Area;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface AreaDao {

    /**
     * 列出区域列表
     * @return areaList
     */
    List<Area> queryArea();
}
