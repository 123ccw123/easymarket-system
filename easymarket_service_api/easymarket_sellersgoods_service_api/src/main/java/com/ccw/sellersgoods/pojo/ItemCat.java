package com.ccw.sellersgoods.pojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
/****
 * @Author:ujiuye
 * @Description:ItemCat构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "ItemCat",value = "ItemCat")
@TableName(value="tb_item_cat")
public class ItemCat implements Serializable{

	@ApiModelProperty(value = "类目ID",required = false)
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
	private Long id;//类目ID

	@ApiModelProperty(value = "父类目ID=0时，代表的是一级的类目",required = false)
    @TableField(value = "parent_id")
	private Long parentId;//父类目ID=0时，代表的是一级的类目

	@ApiModelProperty(value = "类目名称",required = false)
    @TableField(value = "name")
	private String name;//类目名称

	@ApiModelProperty(value = "类型id",required = false)
    @TableField(value = "type_id")
	private Long typeId;//类型id



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
	}
	//get方法
	public Long getParentId() {
		return parentId;
	}

	//set方法
	public void setParentId(Long parentId) {
		this.parentId = parentId;
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
	public Long getTypeId() {
		return typeId;
	}

	//set方法
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}


}
