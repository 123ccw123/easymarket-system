package com.ccw.seckill.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/****
 * @Author:ujiuye
 * @Description:SeckillGoods构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "SeckillGoods",value = "SeckillGoods")
@TableName(value="tb_seckill_goods")
public class SeckillGoods implements Serializable{

	@ApiModelProperty(value = "",required = false)
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
	private Long id;//

	@ApiModelProperty(value = "spu ID",required = false)
    @TableField(value = "goods_id")
	private Long goodsId;//spu ID

	@ApiModelProperty(value = "sku ID",required = false)
    @TableField(value = "item_id")
	private Long itemId;//sku ID

	@ApiModelProperty(value = "标题",required = false)
    @TableField(value = "title")
	private String title;//标题

	@ApiModelProperty(value = "商品图片",required = false)
    @TableField(value = "small_pic")
	private String smallPic;//商品图片

	@ApiModelProperty(value = "原价格",required = false)
    @TableField(value = "price")
	private BigDecimal price;//原价格

	@ApiModelProperty(value = "秒杀价格",required = false)
    @TableField(value = "cost_price")
	private BigDecimal costPrice;//秒杀价格

	@ApiModelProperty(value = "商家ID",required = false)
    @TableField(value = "seller_id")
	private String sellerId;//商家ID

	@ApiModelProperty(value = "添加日期",required = false)
    @TableField(value = "create_time")
	private Date createTime;//添加日期

	@ApiModelProperty(value = "审核日期",required = false)
    @TableField(value = "check_time")
	private Date checkTime;//审核日期

	@ApiModelProperty(value = "审核状态",required = false)
    @TableField(value = "status")
	private String status;//审核状态

	@ApiModelProperty(value = "开始时间",required = false)
    @TableField(value = "start_time")
	private Date startTime;//开始时间

	@ApiModelProperty(value = "结束时间",required = false)
    @TableField(value = "end_time")
	private Date endTime;//结束时间

	@ApiModelProperty(value = "秒杀商品数",required = false)
    @TableField(value = "num")
	private Integer num;//秒杀商品数

	@ApiModelProperty(value = "剩余库存数",required = false)
    @TableField(value = "stock_count")
	private Integer stockCount;//剩余库存数

	@ApiModelProperty(value = "描述",required = false)
    @TableField(value = "introduction")
	private String introduction;//描述



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
	}
	//get方法
	public Long getGoodsId() {
		return goodsId;
	}

	//set方法
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	//get方法
	public Long getItemId() {
		return itemId;
	}

	//set方法
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	//get方法
	public String getTitle() {
		return title;
	}

	//set方法
	public void setTitle(String title) {
		this.title = title;
	}
	//get方法
	public String getSmallPic() {
		return smallPic;
	}

	//set方法
	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}
	//get方法
	public BigDecimal getPrice() {
		return price;
	}

	//set方法
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	//get方法
	public BigDecimal getCostPrice() {
		return costPrice;
	}

	//set方法
	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}
	//get方法
	public String getSellerId() {
		return sellerId;
	}

	//set方法
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	//get方法
	public Date getCreateTime() {
		return createTime;
	}

	//set方法
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	//get方法
	public Date getCheckTime() {
		return checkTime;
	}

	//set方法
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	//get方法
	public String getStatus() {
		return status;
	}

	//set方法
	public void setStatus(String status) {
		this.status = status;
	}
	//get方法
	public Date getStartTime() {
		return startTime;
	}

	//set方法
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	//get方法
	public Date getEndTime() {
		return endTime;
	}

	//set方法
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	//get方法
	public Integer getNum() {
		return num;
	}

	//set方法
	public void setNum(Integer num) {
		this.num = num;
	}
	//get方法
	public Integer getStockCount() {
		return stockCount;
	}

	//set方法
	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}
	//get方法
	public String getIntroduction() {
		return introduction;
	}

	//set方法
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}


}
