package com.ccw.sellersgoods.pojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.lang.Long;
import java.util.Date;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:ujiuye
 * @Description:Item构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "Item",value = "Item")
@TableName(value="tb_item")
public class Item implements Serializable{

	@ApiModelProperty(value = "商品id，同时也是商品编号",required = false)
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
	private Long id;//商品id，同时也是商品编号

	@ApiModelProperty(value = "商品标题",required = false)
    @TableField(value = "title")
	private String title;//商品标题

	@ApiModelProperty(value = "商品卖点",required = false)
    @TableField(value = "sell_point")
	private String sellPoint;//商品卖点

	@ApiModelProperty(value = "商品价格，单位为：元",required = false)
    @TableField(value = "price")
	private BigDecimal price;//商品价格，单位为：元

	@ApiModelProperty(value = "",required = false)
    @TableField(value = "stock_count")
	private Integer stockCount;//

	@ApiModelProperty(value = "库存数量",required = false)
    @TableField(value = "num")
	private Integer num;//库存数量

	@ApiModelProperty(value = "商品条形码",required = false)
    @TableField(value = "barcode")
	private String barcode;//商品条形码

	@ApiModelProperty(value = "商品图片",required = false)
    @TableField(value = "image")
	private String image;//商品图片

	@ApiModelProperty(value = "所属类目，叶子类目",required = false)
    @TableField(value = "categoryId")
	private Long categoryId;//所属类目，叶子类目

	@ApiModelProperty(value = "商品状态，1-正常，2-下架，3-删除",required = false)
    @TableField(value = "status")
	private String status;//商品状态，1-正常，2-下架，3-删除

	@ApiModelProperty(value = "创建时间",required = false)
    @TableField(value = "create_time")
	private Date createTime;//创建时间

	@ApiModelProperty(value = "更新时间",required = false)
    @TableField(value = "update_time")
	private Date updateTime;//更新时间

	@ApiModelProperty(value = "",required = false)
    @TableField(value = "item_sn")
	private String itemSn;//

	@ApiModelProperty(value = "",required = false)
    @TableField(value = "cost_pirce")
	private BigDecimal costPirce;//

	@ApiModelProperty(value = "",required = false)
    @TableField(value = "market_price")
	private BigDecimal marketPrice;//

	@ApiModelProperty(value = "",required = false)
    @TableField(value = "is_default")
	private String isDefault;//

	@ApiModelProperty(value = "",required = false)
    @TableField(value = "goods_id")
	private Long goodsId;//

	@ApiModelProperty(value = "",required = false)
    @TableField(value = "seller_id")
	private String sellerId;//

	@ApiModelProperty(value = "",required = false)
    @TableField(value = "cart_thumbnail")
	private String cartThumbnail;//

	@ApiModelProperty(value = "",required = false)
    @TableField(value = "category")
	private String category;//

	@ApiModelProperty(value = "",required = false)
    @TableField(value = "brand")
	private String brand;//

	@ApiModelProperty(value = "",required = false)
    @TableField(value = "spec")
	private String spec;//

	@ApiModelProperty(value = "",required = false)
    @TableField(value = "seller")
	private String seller;//



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
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
	public String getSellPoint() {
		return sellPoint;
	}

	//set方法
	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
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
	public Integer getStockCount() {
		return stockCount;
	}

	//set方法
	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
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
	public String getBarcode() {
		return barcode;
	}

	//set方法
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	//get方法
	public String getImage() {
		return image;
	}

	//set方法
	public void setImage(String image) {
		this.image = image;
	}
	//get方法
	public Long getCategoryId() {
		return categoryId;
	}

	//set方法
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
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
	public Date getCreateTime() {
		return createTime;
	}

	//set方法
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	//get方法
	public Date getUpdateTime() {
		return updateTime;
	}

	//set方法
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	//get方法
	public String getItemSn() {
		return itemSn;
	}

	//set方法
	public void setItemSn(String itemSn) {
		this.itemSn = itemSn;
	}
	//get方法
	public BigDecimal getCostPirce() {
		return costPirce;
	}

	//set方法
	public void setCostPirce(BigDecimal costPirce) {
		this.costPirce = costPirce;
	}
	//get方法
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	//set方法
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	//get方法
	public String getIsDefault() {
		return isDefault;
	}

	//set方法
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
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
	public String getSellerId() {
		return sellerId;
	}

	//set方法
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	//get方法
	public String getCartThumbnail() {
		return cartThumbnail;
	}

	//set方法
	public void setCartThumbnail(String cartThumbnail) {
		this.cartThumbnail = cartThumbnail;
	}
	//get方法
	public String getCategory() {
		return category;
	}

	//set方法
	public void setCategory(String category) {
		this.category = category;
	}
	//get方法
	public String getBrand() {
		return brand;
	}

	//set方法
	public void setBrand(String brand) {
		this.brand = brand;
	}
	//get方法
	public String getSpec() {
		return spec;
	}

	//set方法
	public void setSpec(String spec) {
		this.spec = spec;
	}
	//get方法
	public String getSeller() {
		return seller;
	}

	//set方法
	public void setSeller(String seller) {
		this.seller = seller;
	}


}
