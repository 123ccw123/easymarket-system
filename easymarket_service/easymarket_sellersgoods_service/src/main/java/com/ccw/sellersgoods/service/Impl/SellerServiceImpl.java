package com.ccw.sellersgoods.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccw.sellersgoods.dao.SellerMapper;
import com.ccw.sellersgoods.pojo.Seller;
import com.ccw.sellersgoods.service.SellerService;
import com.ccw.entity.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/****
 * @Author:ujiuye
 * @Description:Seller业务层接口实现类
 * @Date 2021/2/1 14:19
 *****/
@Service
public class SellerServiceImpl extends ServiceImpl<SellerMapper, Seller> implements SellerService {


    /**
     * Seller条件+分页查询
     * @param seller 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageResult<Seller> findPage(Seller seller, int page, int size){
         Page<Seller> mypage = new Page<>(page, size);
        QueryWrapper<Seller> queryWrapper = this.createQueryWrapper(seller);
        IPage<Seller> iPage = this.page(mypage, queryWrapper);
        return new PageResult<Seller>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * Seller分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageResult<Seller> findPage(int page, int size){
        Page<Seller> mypage = new Page<>(page, size);
        IPage<Seller> iPage = this.page(mypage, new QueryWrapper<Seller>());

        return new PageResult<Seller>(iPage.getTotal(),iPage.getRecords());
    }

    /**
     * Seller条件查询
     * @param seller
     * @return
     */
    @Override
    public List<Seller> findList(Seller seller){
        //构建查询条件
        QueryWrapper<Seller> queryWrapper = this.createQueryWrapper(seller);
        //根据构建的条件查询数据
        return this.list(queryWrapper);
    }


    /**
     * Seller构建查询对象
     * @param seller
     * @return
     */
    public QueryWrapper<Seller> createQueryWrapper(Seller seller){
        QueryWrapper<Seller> queryWrapper = new QueryWrapper<>();
        if(seller!=null){
            // 用户ID
            if(!StringUtils.isEmpty(seller.getSellerId())){
                 queryWrapper.eq("seller_id",seller.getSellerId());
            }
            // 公司名
            if(!StringUtils.isEmpty(seller.getName())){
                queryWrapper.like("name",seller.getName());
            }
            // 店铺名称
            if(!StringUtils.isEmpty(seller.getNickName())){
                 queryWrapper.eq("nick_name",seller.getNickName());
            }
            // 密码
            if(!StringUtils.isEmpty(seller.getPassword())){
                 queryWrapper.eq("password",seller.getPassword());
            }
            // EMAIL
            if(!StringUtils.isEmpty(seller.getEmail())){
                 queryWrapper.eq("email",seller.getEmail());
            }
            // 公司手机
            if(!StringUtils.isEmpty(seller.getMobile())){
                 queryWrapper.eq("mobile",seller.getMobile());
            }
            // 公司电话
            if(!StringUtils.isEmpty(seller.getTelephone())){
                 queryWrapper.eq("telephone",seller.getTelephone());
            }
            // 状态
            if(!StringUtils.isEmpty(seller.getStatus())){
                 queryWrapper.eq("status",seller.getStatus());
            }
            // 详细地址
            if(!StringUtils.isEmpty(seller.getAddressDetail())){
                queryWrapper.like("address_detail",seller.getAddressDetail());
            }
            // 联系人姓名
            if(!StringUtils.isEmpty(seller.getLinkmanName())){
                 queryWrapper.eq("linkman_name",seller.getLinkmanName());
            }
            // 联系人QQ
            if(!StringUtils.isEmpty(seller.getLinkmanQq())){
                 queryWrapper.eq("linkman_qq",seller.getLinkmanQq());
            }
            // 联系人电话
            if(!StringUtils.isEmpty(seller.getLinkmanMobile())){
                 queryWrapper.eq("linkman_mobile",seller.getLinkmanMobile());
            }
            // 联系人EMAIL
            if(!StringUtils.isEmpty(seller.getLinkmanEmail())){
                 queryWrapper.eq("linkman_email",seller.getLinkmanEmail());
            }
            // 营业执照号
            if(!StringUtils.isEmpty(seller.getLicenseNumber())){
                 queryWrapper.eq("license_number",seller.getLicenseNumber());
            }
            // 税务登记证号
            if(!StringUtils.isEmpty(seller.getTaxNumber())){
                 queryWrapper.eq("tax_number",seller.getTaxNumber());
            }
            // 组织机构代码
            if(!StringUtils.isEmpty(seller.getOrgNumber())){
                 queryWrapper.eq("org_number",seller.getOrgNumber());
            }
            // 公司地址
            if(!StringUtils.isEmpty(seller.getAddress())){
                queryWrapper.like("address",seller.getAddress());
            }
            // 公司LOGO图
            if(!StringUtils.isEmpty(seller.getLogoPic())){
                 queryWrapper.eq("logo_pic",seller.getLogoPic());
            }
            // 简介
            if(!StringUtils.isEmpty(seller.getBrief())){
                 queryWrapper.eq("brief",seller.getBrief());
            }
            // 创建日期
            if(!StringUtils.isEmpty(seller.getCreateTime())){
                 queryWrapper.eq("create_time",seller.getCreateTime());
            }
            // 法定代表人
            if(!StringUtils.isEmpty(seller.getLegalPerson())){
                 queryWrapper.eq("legal_person",seller.getLegalPerson());
            }
            // 法定代表人身份证
            if(!StringUtils.isEmpty(seller.getLegalPersonCardId())){
                 queryWrapper.eq("legal_person_card_id",seller.getLegalPersonCardId());
            }
            // 开户行账号名称
            if(!StringUtils.isEmpty(seller.getBankUser())){
                 queryWrapper.eq("bank_user",seller.getBankUser());
            }
            // 开户行
            if(!StringUtils.isEmpty(seller.getBankName())){
                 queryWrapper.eq("bank_name",seller.getBankName());
            }
        }
        return queryWrapper;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(String id){
        this.removeById(id);
    }

    /**
     * 修改Seller
     * @param seller
     */
    @Override
    public void update(Seller seller){
        this.updateById(seller);
    }

    /**
     * 增加Seller
     * @param seller
     */
    @Override
    public void add(Seller seller){
        this.save(seller);
    }

    /**
     * 根据ID查询Seller
     * @param id
     * @return
     */
    @Override
    public Seller findById(String id){
        return  this.getById(id);
    }

    /**
     * 查询Seller全部数据
     * @return
     */
    @Override
    public List<Seller> findAll() {
        return this.list(new QueryWrapper<Seller>());
    }
}
