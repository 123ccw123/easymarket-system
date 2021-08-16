package com.ccw.sellersgoods.pojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
/****
 * @Author:ujiuye
 * @Description:TypeTemplate构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "TypeTemplate",value = "TypeTemplate")
@TableName(value="tb_type_template")
public class TypeTemplate implements Serializable{

	@ApiModelProperty(value = "",required = false)
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
	private Long id;//

	@ApiModelProperty(value = "模板名称",required = false)
    @TableField(value = "name")
	private String name;//模板名称

	@ApiModelProperty(value = "关联规格",required = false)
    @TableField(value = "spec_ids")
	private String specIds;//关联规格

	@ApiModelProperty(value = "关联品牌",required = false)
    @TableField(value = "brand_ids")
	private String brandIds;//关联品牌

	@ApiModelProperty(value = "自定义属性",required = false)
    @TableField(value = "custom_attribute_items")
	private String customAttributeItems;//自定义属性



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
	}
	//get方法
	public String getName() {
		return name;
	}

	//set方法
	public void setName(String name) {
		this.name = name;
	}
	//get方法
	public String getSpecIds() {
		return specIds;
	}

	//set方法
	public void setSpecIds(String specIds) {
		this.specIds = specIds;
	}
	//get方法
	public String getBrandIds() {
		return brandIds;
	}

	//set方法
	public void setBrandIds(String brandIds) {
		this.brandIds = brandIds;
	}
	//get方法
	public String getCustomAttributeItems() {
		return customAttributeItems;
	}

	//set方法
	public void setCustomAttributeItems(String customAttributeItems) {
		this.customAttributeItems = customAttributeItems;
	}


}
