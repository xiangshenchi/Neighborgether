package com.neighbor.gether.Function;

import com.neighbor.gether.ing.Propertyinfo;
import com.neighbor.gether.mapper.PrppertyinfoMapper;
import com.neighbor.gether.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class FuncProPertyinfo {

    public void FuncSelectAllPropertyinfo() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        PrppertyinfoMapper PrppertyinfoMapper = sqlSession.getMapper(PrppertyinfoMapper.class);
        List<Propertyinfo> propertyinfoList = PrppertyinfoMapper.getPropertyinfoList();
        for (Propertyinfo propertyinfo : propertyinfoList) {
            System.out.println(propertyinfo);
        }
        sqlSession.close();
    }

    public void FuncgetPropertyinfoLike(String roomnumber) {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        PrppertyinfoMapper PrppertyinfoMapper = sqlSession.getMapper(PrppertyinfoMapper.class);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("roomnumber", roomnumber);
        List<Propertyinfo> propertyinfoList = PrppertyinfoMapper.getPropertyinfoLike(map);
        for (Propertyinfo propertyinfo : propertyinfoList) {
            System.out.println(propertyinfo);
        }
        sqlSession.close();
    }

    public void FuncgetPropertyinfoById(int id) {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        PrppertyinfoMapper Mapper = sqlSession.getMapper(PrppertyinfoMapper.class);
        Propertyinfo propertyinfo = Mapper.getPropertyinfoById(id);
        System.out.println(propertyinfo);
        sqlSession.close();
    }

    public void FuncgetPropertyinfoByIdMap(int id) {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        PrppertyinfoMapper Mapper = sqlSession.getMapper(PrppertyinfoMapper.class);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("propertyid", id);
        Propertyinfo propertyinfo = Mapper.getPropertyinfoByIdMap(map);
        System.out.println(propertyinfo);
        sqlSession.close();
    }

    public void FuncaddPropertyinfo(int propertyid, int userid, String roomnumber, String buildingnumber, String unitnumber, float area) {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        PrppertyinfoMapper PrppertyinfoMapper = sqlSession.getMapper(PrppertyinfoMapper.class);
        int res = PrppertyinfoMapper.addPropertyinfo(new Propertyinfo(propertyid, userid, roomnumber, buildingnumber, unitnumber, area));
        if (res > 0) {
            System.out.println("插入成功");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    public void FuncaddPropertyinfoMap(int propertyid, int userid, String roomnumber, String buildingnumber, String unitnumber, float area) {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        PrppertyinfoMapper PrppertyinfoMapper = sqlSession.getMapper(PrppertyinfoMapper.class);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("propertyid", propertyid);
        map.put("userid", userid);
        map.put("roomnumber", roomnumber);
        map.put("buildingnumber", buildingnumber);
        map.put("unitnumber", unitnumber);
        map.put("area", area);
        int res = PrppertyinfoMapper.addPropertyinfoMap(map);
        if (res > 0) {
            System.out.println("插入成功");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    public void FuncupdatePropertyinfoMap(int propertyid, int userid, String roomnumber, String buildingnumber, String unitnumber, float area) {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        PrppertyinfoMapper PrppertyinfoMapper = sqlSession.getMapper(PrppertyinfoMapper.class);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("propertyid", propertyid);
        map.put("userid", userid);
        map.put("roomnumber", roomnumber);
        map.put("buildingnumber", buildingnumber);
        map.put("unitnumber", unitnumber);
        map.put("area", area);
        int res = PrppertyinfoMapper.updatePropertyinfoMap(map);
        if (res > 0) {
            System.out.println("更新成功");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    public void FuncupdatePropertyinfo(int propertyid, int userid, String roomnumber, String buildingnumber, String unitnumber, float area) {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        PrppertyinfoMapper PrppertyinfoMapper = sqlSession.getMapper(PrppertyinfoMapper.class);
        int res = PrppertyinfoMapper.updatePropertyinfo(new Propertyinfo(propertyid, userid, roomnumber, buildingnumber, unitnumber, area));
        if (res > 0) {
            System.out.println("更新成功");
        }
        sqlSession.commit();
        sqlSession.close();
    }


    public void FuncdeletePropertyinfo(int id) {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        PrppertyinfoMapper PrppertyinfoMapper = sqlSession.getMapper(PrppertyinfoMapper.class);
        int res = PrppertyinfoMapper.deletePropertyinfo(id);
        if (res > 0) {
            System.out.println("删除成功");
        }
        sqlSession.commit();
        sqlSession.close();
    }
    public void FuncdeletePropertyinfoMap(int id) {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        PrppertyinfoMapper PrppertyinfoMapper = sqlSession.getMapper(PrppertyinfoMapper.class);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("propertyid", id);
        int res = PrppertyinfoMapper.deletePropertyinfoMap(map);
        if (res > 0) {
            System.out.println("删除成功");
        }
        sqlSession.commit();
        sqlSession.close();
    }

}
