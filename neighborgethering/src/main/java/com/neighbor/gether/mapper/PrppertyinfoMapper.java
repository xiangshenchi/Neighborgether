package com.neighbor.gether.mapper;

import com.neighbor.gether.ing.Propertyinfo;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface PrppertyinfoMapper {
   List<Propertyinfo> getPropertyinfoList() ;

   List<Propertyinfo> getPropertyinfoLike(Map<String,Object> map);
   Propertyinfo getPropertyinfoById(int PropertyID);
   Propertyinfo getPropertyinfoByIdMap(Map<String,Object> map);

   int addPropertyinfo(Propertyinfo propertyinfo);

   int addPropertyinfoMap(Map<String,Object> map);
   int updatePropertyinfo(Propertyinfo propertyinfo);

   int updatePropertyinfoMap(Map<String,Object> map);

   int deletePropertyinfo(int PropertyID);

   int deletePropertyinfoMap(Map<String,Object> map);
}
