package com.neighbor.gether.test;

import com.neighbor.gether.ing.Propertyinfo;
import com.neighbor.gether.mapper.PrppertyinfoMapper;
import com.neighbor.gether.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import com.neighbor.gether.Function.FuncProPertyinfo;

import java.util.List;
import java.util.Scanner;

public class TestPropertyinfo {


@Test
    public void testSelectAllPropertyinfo(){
        FuncProPertyinfo funcProPertyinfo=new FuncProPertyinfo();
        funcProPertyinfo.FuncSelectAllPropertyinfo();
    }
    @Test
    public void testFuncgetPropertyinfoLike(){
        FuncProPertyinfo funcProPertyinfo=new FuncProPertyinfo();
        funcProPertyinfo.FuncgetPropertyinfoLike("0");
    }

    @Test
    public void testgetPropertyinfoById(){
       int id=1;
        FuncProPertyinfo funcProPertyinfo=new FuncProPertyinfo();
        funcProPertyinfo.FuncgetPropertyinfoById(id);
    }
    @Test
    public void testgetPropertyinfoByIdMap(){

        int id=1;
        FuncProPertyinfo funcProPertyinfo=new FuncProPertyinfo();
        funcProPertyinfo.FuncgetPropertyinfoByIdMap(id);
    }


    @Test
    public void testaddPropertyinfo(){
        int propertyid=3;
         int userid=3;
        String roomnumber="303";
        String buildingnumber="C";
        String unitnumber="3";
         float area= 12.36F;
        FuncProPertyinfo funcProPertyinfo=new FuncProPertyinfo();
        funcProPertyinfo.FuncaddPropertyinfo(propertyid,userid,roomnumber,buildingnumber,unitnumber,area);
    }


    @Test
    public void testFuncaddPropertyinfoMap(){
        int propertyid=3;
        int userid=3;
        String roomnumber="303";
        String buildingnumber="C";
        String unitnumber="3";
        float area= 12.36F;
        FuncProPertyinfo funcProPertyinfo=new FuncProPertyinfo();
        funcProPertyinfo.FuncaddPropertyinfoMap(propertyid,userid,roomnumber,buildingnumber,unitnumber,area);
    }

    @Test
    public void testupdatePropertyinfo(){
        int propertyid=3;
        int userid=3;
        String roomnumber="303";
        String buildingnumber="C";
        String unitnumber="3";
        float area= 12.34F;
        FuncProPertyinfo funcProPertyinfo=new FuncProPertyinfo();
        funcProPertyinfo.FuncupdatePropertyinfo(propertyid,userid,roomnumber,buildingnumber,unitnumber,area);
    }

    @Test
    public void testupdatePropertyinfoMap(){
        int propertyid=3;
        int userid=3;
        String roomnumber="303";
        String buildingnumber="C";
        String unitnumber="3";
        float area= 12.33F;
        FuncProPertyinfo funcProPertyinfo=new FuncProPertyinfo();
        funcProPertyinfo.FuncupdatePropertyinfoMap(propertyid,userid,roomnumber,buildingnumber,unitnumber,area);
    }

    @Test
    public void testdeletePropertyinfo(){
    int id=3;
    FuncProPertyinfo funcProPertyinfo=new FuncProPertyinfo();
    funcProPertyinfo.FuncdeletePropertyinfo(id);
    }
    @Test
    public void testdeletePropertyinfoMap(){
        int id=3;
        FuncProPertyinfo funcProPertyinfo=new FuncProPertyinfo();
        funcProPertyinfo.FuncdeletePropertyinfo(id);
    }


}
