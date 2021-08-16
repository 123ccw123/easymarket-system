package com.ccw.sellersgoods.pojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.lang.Long;
import java.lang.String;
/****
 * @Author:ujiuye
 * @Description:Goods构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "Goods",value = "Goods")
@TableName(value="tb_goods")
public class Goods implements Serializable{

	@ApiModelProperty(value = "主键",required = false)
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
	private Long id;//主键

	@ApiModelProperty(value = "商家ID",required = false)
    @TableField(value = "seller_id")
	private String sellerId;//商家ID

	@ApiModelProperty(value = "SPU名",required = false)
    @TableField(value = "goods_name")
	private String goodsName;//SPU名

	@ApiModelProperty(value = "默认SKU",required = false)
    @TableField(value = "default_item_id")
	private Long defaultItemId;//默认SKU

	@ApiModelProperty(value = "状态",required = false)
    @TableField(value = "audit_status")
	private String auditStatus;//状态

	@ApiModelProperty(value = "是否上架",required = false)
    @TableField(value = "is_marketable")
	private String isMarketable;//是否上架

	@ApiModelProperty(value = "品牌",required = false)
    @TableField(value = "brand_id")
	private Long brandId;//品牌

	@ApiModelProperty(value = "副标题",required = false)
    @TableField(value = "caption")
	private String caption;//副标题

	@ApiModelProperty(value = "一级类目",required = false)
    @TableField(value = "category1_id")
	private Long category1Id;//一级类目

	@ApiModelProperty(value = "二级类目",required = false)
    @TableField(value = "category2_id")
	private Long category2Id;//二级类目

	@ApiModelProperty(value = "三级类目",required = false)
    @TableField(value = "category3_id")
	private Long category3Id;//三级类目

	@ApiModelProperty(value = "小图",required = false)
    @TableField(value = "small_pic")
	private String smallPic;//小图

	@ApiModelProperty(value = "商城价",required = false)
    @TableField(value = "price")
	private BigDecimal price;//商城价

	@ApiModelProperty(value = "分类模板ID",required = false)
    @TableField(value = "type_template_id")
	private Long typeTemplateId;//分类模板ID

	@ApiModelProperty(value = "是否启用规格",required = false)
    @TableField(value = "is_enable_spec")
	private String isEnableSpec;//是否启用规格

	@ApiModelProperty(value = "是否删除",required = false)
    @TableField(value = "is_delete")
	private String isDelete;//是否删除



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
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
	public String getGoodsName() {
		return goodsName;
	}

	//set方法
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	//get方法
	public Long getDefaultItemId() {
		return defaultItemId;
	}

	//set方法
	public void setDefaultItemId(Long defaultItemId) {
		this.defaultItemId = defaultItemId;
	}
	//get方法
	public String getAuditStatus() {
		return auditStatus;
	}

	//set方法
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	//get方法
	public String getIsMarketable() {
		return isMarketable;
	}

	//set方法
	public void setIsMarketable(String isMarketable) {
		this.isMarketable = isMarketable;
	}
	//get方法
	public Long getBrandId() {
		return brandId;
	}

	//set方法
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	//get方法
	public String getCaption() {
		return caption;
	}

	//set方法
	public void setCaption(String caption) {
		this.caption = caption;
	}
	//get方法
	public Long getCategory1Id() {
		return category1Id;
	}

	//set方法
	public void setCategory1Id(Long category1Id) {
		this.category1Id = category1Id;
	}
	//get方法
	public Long getCategory2Id() {
		return category2Id;
	}

	//set方法
	public void setCategory2Id(Long category2Id) {
		this.category2Id = category2Id;
	}
	//get方法
	public Long getCategory3Id() {
		return category3Id;
	}

	//set方法
	public void setCategory3Id(Long category3Id) {
		this.category3Id = category3Id;
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
	public Long getTypeTemplateId() {
		return typeTemplateId;
	}

	//set方法
	public void setTypeTemplateId(Long typeTemplateId) {
		this.typeTemplateId = typeTemplateId;
	}
	//get方法
	public String getIsEnableSpec() {
		return isEnableSpec;
	}

	//set方法
	public void setIsEnableSpec(String isEnableSpec) {
		this.isEnableSpec = isEnableSpec;
	}
	//get方法
	public String getIsDelete() {
		return isDelete;
	}

	//set方法
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}


}
