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
 * @Description:Areas构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "Areas",value = "Areas")
@TableName(value="tb_areas")
public class Areas implements Serializable{

	@ApiModelProperty(value = "唯一ID",required = false)
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
	private Integer id;//唯一ID

	@ApiModelProperty(value = "区域ID",required = false)
    @TableField(value = "areaid")
	private String areaid;//区域ID

	@ApiModelProperty(value = "区域名称",required = false)
    @TableField(value = "area")
	private String area;//区域名称

	@ApiModelProperty(value = "城市ID",required = false)
    @TableField(value = "cityid")
	private String cityid;//城市ID



	//get方法
	public Integer getId() {
		return id;
	}

	//set方法
	public void setId(Integer id) {
		this.id = id;
	}
	//get方法
	public String getAreaid() {
		return areaid;
	}

	//set方法
	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	//get方法
	public String getArea() {
		return area;
	}

	//set方法
	public void setArea(String area) {
		this.area = area;
	}
	//get方法
	public String getCityid() {
		return cityid;
	}

	//set方法
	public void setCityid(String cityid) {
		this.cityid = cityid;
	}


}
