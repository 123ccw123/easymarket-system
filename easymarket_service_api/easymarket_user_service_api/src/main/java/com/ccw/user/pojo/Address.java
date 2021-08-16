package com.ccw.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/****
 * @Author:ujiuye
 * @Description:Address构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "Address",value = "Address")
@TableName(value="tb_address")
public class Address implements Serializable{

	@ApiModelProperty(value = "",required = false)
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
	private Long id;//

	@ApiModelProperty(value = "用户ID",required = false)
    @TableField(value = "user_id")
	private String userId;//用户ID

	@ApiModelProperty(value = "省",required = false)
    @TableField(value = "province_id")
	private String provinceId;//省

	@ApiModelProperty(value = "市",required = false)
    @TableField(value = "city_id")
	private String cityId;//市

	@ApiModelProperty(value = "县/区",required = false)
    @TableField(value = "town_id")
	private String townId;//县/区

	@ApiModelProperty(value = "手机",required = false)
    @TableField(value = "mobile")
	private String mobile;//手机

	@ApiModelProperty(value = "详细地址",required = false)
    @TableField(value = "address")
	private String address;//详细地址

	@ApiModelProperty(value = "联系人",required = false)
    @TableField(value = "contact")
	private String contact;//联系人

	@ApiModelProperty(value = "是否是默认 1默认 0否",required = false)
    @TableField(value = "is_default")
	private String isDefault;//是否是默认 1默认 0否

	@ApiModelProperty(value = "备注",required = false)
    @TableField(value = "notes")
	private String notes;//备注

	@ApiModelProperty(value = "创建日期",required = false)
    @TableField(value = "create_date")
	private Date createDate;//创建日期

	@ApiModelProperty(value = "别名",required = false)
    @TableField(value = "alias")
	private String alias;//别名



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
	}
	//get方法
	public String getUserId() {
		return userId;
	}

	//set方法
	public void setUserId(String userId) {
		this.userId = userId;
	}
	//get方法
	public String getProvinceId() {
		return provinceId;
	}

	//set方法
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	//get方法
	public String getCityId() {
		return cityId;
	}

	//set方法
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	//get方法
	public String getTownId() {
		return townId;
	}

	//set方法
	public void setTownId(String townId) {
		this.townId = townId;
	}
	//get方法
	public String getMobile() {
		return mobile;
	}

	//set方法
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	//get方法
	public String getAddress() {
		return address;
	}

	//set方法
	public void setAddress(String address) {
		this.address = address;
	}
	//get方法
	public String getContact() {
		return contact;
	}

	//set方法
	public void setContact(String contact) {
		this.contact = contact;
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
	public String getNotes() {
		return notes;
	}

	//set方法
	public void setNotes(String notes) {
		this.notes = notes;
	}
	//get方法
	public Date getCreateDate() {
		return createDate;
	}

	//set方法
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	//get方法
	public String getAlias() {
		return alias;
	}

	//set方法
	public void setAlias(String alias) {
		this.alias = alias;
	}


}
