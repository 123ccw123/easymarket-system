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
 * @Description:Cities构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "Cities",value = "Cities")
@TableName(value="tb_cities")
public class Cities implements Serializable{

	@ApiModelProperty(value = "唯一ID",required = false)
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
	private Integer id;//唯一ID

	@ApiModelProperty(value = "城市ID",required = false)
    @TableField(value = "cityid")
	private String cityid;//城市ID

	@ApiModelProperty(value = "城市名称",required = false)
    @TableField(value = "city")
	private String city;//城市名称

	@ApiModelProperty(value = "省份ID",required = false)
    @TableField(value = "provinceid")
	private String provinceid;//省份ID



	//get方法
	public Integer getId() {
		return id;
	}

	//set方法
	public void setId(Integer id) {
		this.id = id;
	}
	//get方法
	public String getCityid() {
		return cityid;
	}

	//set方法
	public void setCityid(String cityid) {
		this.cityid = cityid;
	}
	//get方法
	public String getCity() {
		return city;
	}

	//set方法
	public void setCity(String city) {
		this.city = city;
	}
	//get方法
	public String getProvinceid() {
		return provinceid;
	}

	//set方法
	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}


}
