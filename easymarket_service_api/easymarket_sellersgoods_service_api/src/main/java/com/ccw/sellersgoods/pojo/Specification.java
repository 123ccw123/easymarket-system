package com.ccw.sellersgoods.pojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
/****
 * @Author:ujiuye
 * @Description:Specification构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "Specification",value = "Specification")
@TableName(value="tb_specification")
public class Specification implements Serializable{

	@ApiModelProperty(value = "主键",required = false)
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
	private Long id;//主键

	@ApiModelProperty(value = "名称",required = false)
    @TableField(value = "spec_name")
	private String specName;//名称



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
	}
	//get方法
	public String getSpecName() {
		return specName;
	}

	//set方法
	public void setSpecName(String specName) {
		this.specName = specName;
	}


}
