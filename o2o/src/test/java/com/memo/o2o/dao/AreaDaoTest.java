package com.memo.o2o.dao;

import com.memo.dao.AreaDao;
import com.memo.entity.Area;
import com.memo.o2o.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaDaoTest extends BaseTest {
    @Autowired
    private AreaDao areaDao;


    @Test
    public void testQueryArea(){
        List<Area> areaList = areaDao.queryArea();
        Iterator<Area> iterator = areaList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }




    }


}
