package com.ccw.sellersgoods.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ccw.sellersgoods.pojo.Specification;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/****
 * @Author:ujiuye
 * @Description:Specification的Dao
 * @Date 2021/2/1 14:19
 *****/
public interface SpecificationMapper extends BaseMapper<Specification> {
    @Select("select id,spec_name as text from tb_specification")
    public List<Map> selectOption();
}
