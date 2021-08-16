package com.ccw.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccw.dao.AreasMapper;
import com.ccw.user.pojo.Areas;
import com.ccw.service.AreasService;
import com.ccw.entity.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:Areas业务层接口实现类
 * @Date 2021/2/1 14:19
 *****/
@Service
public class AreasServiceImpl extends ServiceImpl<AreasMapper, Areas> implements AreasService {


    /**
     * Areas条件+分页查询
     * @param areas 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult<Areas> findPage(Areas areas, int page, int size){
         Page<Areas> mypage = new Page<>(page, size);
        QueryWrapper<Areas> queryWrapper = this.createQueryWrapper(areas);
        IPage<Areas> iPage = this.page(mypage, queryWrapper);
        return new PageResult<Areas>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * Areas分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Areas> findPage(int page, int size){
        Page<Areas> mypage = new Page<>(page, size);
        IPage<Areas> iPage = this.page(mypage, new QueryWrapper<Areas>());

        return new PageResult<Areas>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * Areas条件查询
     * @param areas
     * @return
     */
    @Override
    public List<Areas> findList(Areas areas){
        //构建查询条件
        QueryWrapper<Areas> queryWrapper = this.createQueryWrapper(areas);
        //根据构建的条件查询数据
        return this.list(queryWrapper);
    }


    /**
     * Areas构建查询对象
     * @param areas
     * @return
     */
    public QueryWrapper<Areas> createQueryWrapper(Areas areas){
        QueryWrapper<Areas> queryWrapper = new QueryWrapper<>();
        if(areas!=null){
            // 唯一ID
            if(!StringUtils.isEmpty(areas.getId())){
                 queryWrapper.eq("id",areas.getId());
            }
            // 区域ID
            if(!StringUtils.isEmpty(areas.getAreaid())){
                 queryWrapper.eq("areaid",areas.getAreaid());
            }
            // 区域名称
            if(!StringUtils.isEmpty(areas.getArea())){
                 queryWrapper.eq("area",areas.getArea());
            }
            // 城市ID
            if(!StringUtils.isEmpty(areas.getCityid())){
                 queryWrapper.eq("cityid",areas.getCityid());
            }
        }
        return queryWrapper;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Integer id){
        this.removeById(id);
    }

    /**
     * 修改Areas
     * @param areas
     */
    @Override
    public void update(Areas areas){
        this.updateById(areas);
    }

    /**
     * 增加Areas
     * @param areas
     */
    @Override
    public void add(Areas areas){
        this.save(areas);
    }

    /**
     * 根据ID查询Areas
     * @param id
     * @return
     */
    @Override
    public Areas findById(Integer id){
        return  this.getById(id);
    }

    /**
     * 查询Areas全部数据
     * @return
     */
    @Override
    public List<Areas> findAll() {
        return this.list(new QueryWrapper<Areas>());
    }
}
