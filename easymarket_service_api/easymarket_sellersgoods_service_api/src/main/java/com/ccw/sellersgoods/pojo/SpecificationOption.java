package com.ccw.sellersgoods.pojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.lang.Integer;
/****
 * @Author:ujiuye
 * @Description:SpecificationOption构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "SpecificationOption",value = "SpecificationOption")
@TableName(value="tb_specification_option")
public class SpecificationOption implements Serializable{

	@ApiModelProperty(value = "规格项ID",required = false)
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
	private Long id;//规格项ID

	@ApiModelProperty(value = "规格项名称",required = false)
    @TableField(value = "option_name")
	private String optionName;//规格项名称

	@ApiModelProperty(value = "规格ID",required = false)
    @TableField(value = "spec_id")
	private Long specId;//规格ID

	@ApiModelProperty(value = "排序值",required = false)
    @TableField(value = "orders")
	private Integer orders;//排序值



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
	}
	//get方法
	public String getOptionName() {
		return optionName;
	}

	//set方法
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	//get方法
	public Long getSpecId() {
		return specId;
	}

	//set方法
	public void setSpecId(Long specId) {
		this.specId = specId;
	}
	//get方法
	public Integer getOrders() {
		return orders;
	}

	//set方法
	public void setOrders(Integer orders) {
		this.orders = orders;
	}


}
