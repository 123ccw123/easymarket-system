package com.ccw.sellersgoods.pojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
/****
 * @Author:ujiuye
 * @Description:GoodsDesc构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "GoodsDesc",value = "GoodsDesc")
@TableName(value="tb_goods_desc")
public class GoodsDesc implements Serializable{

	@ApiModelProperty(value = "SPU_ID",required = false)
    @TableId(type = IdType.INPUT)
    @TableField(value = "goods_id")
	private Long goodsId;//SPU_ID

	@ApiModelProperty(value = "描述",required = false)
    @TableField(value = "introduction")
	private String introduction;//描述

	@ApiModelProperty(value = "规格结果集，所有规格，包含isSelected",required = false)
    @TableField(value = "specification_items")
	private String specificationItems;//规格结果集，所有规格，包含isSelected

	@ApiModelProperty(value = "自定义属性（参数结果）",required = false)
    @TableField(value = "custom_attribute_items")
	private String customAttributeItems;//自定义属性（参数结果）

	@ApiModelProperty(value = "",required = false)
    @TableField(value = "item_images")
	private String itemImages;//

	@ApiModelProperty(value = "包装列表",required = false)
    @TableField(value = "package_list")
	private String packageList;//包装列表

	@ApiModelProperty(value = "售后服务",required = false)
    @TableField(value = "sale_service")
	private String saleService;//售后服务



	//get方法
	public Long getGoodsId() {
		return goodsId;
	}

	//set方法
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	//get方法
	public String getIntroduction() {
		return introduction;
	}

	//set方法
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	//get方法
	public String getSpecificationItems() {
		return specificationItems;
	}

	//set方法
	public void setSpecificationItems(String specificationItems) {
		this.specificationItems = specificationItems;
	}
	//get方法
	public String getCustomAttributeItems() {
		return customAttributeItems;
	}

	//set方法
	public void setCustomAttributeItems(String customAttributeItems) {
		this.customAttributeItems = customAttributeItems;
	}
	//get方法
	public String getItemImages() {
		return itemImages;
	}

	//set方法
	public void setItemImages(String itemImages) {
		this.itemImages = itemImages;
	}
	//get方法
	public String getPackageList() {
		return packageList;
	}

	//set方法
	public void setPackageList(String packageList) {
		this.packageList = packageList;
	}
	//get方法
	public String getSaleService() {
		return saleService;
	}

	//set方法
	public void setSaleService(String saleService) {
		this.saleService = saleService;
	}


}
