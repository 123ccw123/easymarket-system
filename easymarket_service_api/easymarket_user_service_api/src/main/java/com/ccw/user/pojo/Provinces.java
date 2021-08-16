package com.ccw.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/****
 * @Author:ujiuye
 * @Description:Provinces构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "Provinces",value = "Provinces")
@TableName(value="tb_provinces")
public class Provinces implements Serializable{

	@ApiModelProperty(value = "唯一ID",required = false)
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
	private Integer id;//唯一ID

	@ApiModelProperty(value = "省份ID",required = false)
    @TableField(value = "provinceid")
	private String provinceid;//省份ID

	@ApiModelProperty(value = "省份名称",required = false)
    @TableField(value = "province")
	private String province;//省份名称



	//get方法
	public Integer getId() {
		return id;
	}

	//set方法
	public void setId(Integer id) {
		this.id = id;
	}
	//get方法
	public String getProvinceid() {
		return provinceid;
	}

	//set方法
	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}
	//get方法
	public String getProvince() {
		return province;
	}

	//set方法
	public void setProvince(String province) {
		this.province = province;
	}


}
