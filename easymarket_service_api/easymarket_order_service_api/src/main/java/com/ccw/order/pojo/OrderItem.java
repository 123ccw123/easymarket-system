package com.ccw.order.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/****
 * @Author:ujiuye
 * @Description:OrderItem构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "OrderItem",value = "OrderItem")
@TableName(value="tb_order_item")
public class OrderItem implements Serializable{

	@ApiModelProperty(value = "",required = false)
    @TableId(type = IdType.INPUT)
    @TableField(value = "id")
	private Long id;//

	@ApiModelProperty(value = "商品id",required = false)
    @TableField(value = "item_id")
	private Long itemId;//商品id

	@ApiModelProperty(value = "SPU_ID",required = false)
    @TableField(value = "goods_id")
	private Long goodsId;//SPU_ID

	@ApiModelProperty(value = "订单id",required = false)
    @TableField(value = "order_id")
	private Long orderId;//订单id

	@ApiModelProperty(value = "商品标题",required = false)
    @TableField(value = "title")
	private String title;//商品标题

	@ApiModelProperty(value = "商品单价",required = false)
    @TableField(value = "price")
	private BigDecimal price;//商品单价

	@ApiModelProperty(value = "商品购买数量",required = false)
    @TableField(value = "num")
	private Integer num;//商品购买数量

	@ApiModelProperty(value = "商品总金额",required = false)
    @TableField(value = "total_fee")
	private BigDecimal totalFee;//商品总金额

	@ApiModelProperty(value = "商品图片地址",required = false)
    @TableField(value = "pic_path")
	private String picPath;//商品图片地址

	@ApiModelProperty(value = "",required = false)
    @TableField(value = "seller_id")
	private String sellerId;//



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
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
	public Long getGoodsId() {
		return goodsId;
	}

	//set方法
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	//get方法
	public Long getOrderId() {
		return orderId;
	}

	//set方法
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
	public BigDecimal getPrice() {
		return price;
	}

	//set方法
	public void setPrice(BigDecimal price) {
		this.price = price;
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
	public BigDecimal getTotalFee() {
		return totalFee;
	}

	//set方法
	public void setTotalFee(BigDecimal totalFee) {
		this.totalFee = totalFee;
	}
	//get方法
	public String getPicPath() {
		return picPath;
	}

	//set方法
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	//get方法
	public String getSellerId() {
		return sellerId;
	}

	//set方法
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}


}
