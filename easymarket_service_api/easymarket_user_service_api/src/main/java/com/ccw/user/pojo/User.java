package com.ccw.user.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/****
 * @Author:ujiuye
 * @Description:User构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "User",value = "User")
@TableName(value="tb_user")
public class User implements Serializable{

	@ApiModelProperty(value = "",required = false)
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")
	private Long id;//

	@ApiModelProperty(value = "用户名",required = false)
    @TableField(value = "username")
	private String username;//用户名

	@ApiModelProperty(value = "密码，加密存储",required = false)
    @TableField(value = "password")
	private String password;//密码，加密存储

	@ApiModelProperty(value = "注册手机号",required = false)
    @TableField(value = "phone")
	private String phone;//注册手机号

	@ApiModelProperty(value = "注册邮箱",required = false)
    @TableField(value = "email")
	private String email;//注册邮箱

	@ApiModelProperty(value = "创建时间",required = false)
    @TableField(value = "created")
	private Date created;//创建时间

	@ApiModelProperty(value = "",required = false)
    @TableField(value = "updated")
	private Date updated;//

	@ApiModelProperty(value = "会员来源：1:PC，2：H5，3：Android，4：IOS，5：WeChat",required = false)
    @TableField(value = "source_type")
	private String sourceType;//会员来源：1:PC，2：H5，3：Android，4：IOS，5：WeChat

	@ApiModelProperty(value = "昵称",required = false)
    @TableField(value = "nick_name")
	private String nickName;//昵称

	@ApiModelProperty(value = "真实姓名",required = false)
    @TableField(value = "name")
	private String name;//真实姓名

	@ApiModelProperty(value = "使用状态（Y正常 N非正常）",required = false)
    @TableField(value = "status")
	private String status;//使用状态（Y正常 N非正常）

	@ApiModelProperty(value = "头像地址",required = false)
    @TableField(value = "head_pic")
	private String headPic;//头像地址

	@ApiModelProperty(value = "QQ号码",required = false)
    @TableField(value = "qq")
	private String qq;//QQ号码

	@ApiModelProperty(value = "账户余额",required = false)
    @TableField(value = "account_balance")
	private BigDecimal accountBalance;//账户余额

	@ApiModelProperty(value = "手机是否验证 （0否  1是）",required = false)
    @TableField(value = "is_mobile_check")
	private String isMobileCheck;//手机是否验证 （0否  1是）

	@ApiModelProperty(value = "邮箱是否检测（0否  1是）",required = false)
    @TableField(value = "is_email_check")
	private String isEmailCheck;//邮箱是否检测（0否  1是）

	@ApiModelProperty(value = "性别，1男，2女",required = false)
    @TableField(value = "sex")
	private String sex;//性别，1男，2女

	@ApiModelProperty(value = "会员等级",required = false)
    @TableField(value = "user_level")
	private Integer userLevel;//会员等级

	@ApiModelProperty(value = "积分",required = false)
    @TableField(value = "points")
	private Integer points;//积分

	@ApiModelProperty(value = "经验值",required = false)
    @TableField(value = "experience_value")
	private Integer experienceValue;//经验值

	@ApiModelProperty(value = "生日",required = false)
    @TableField(value = "birthday")
	private Date birthday;//生日

	@ApiModelProperty(value = "最后登录时间",required = false)
    @TableField(value = "last_login_time")
	private Date lastLoginTime;//最后登录时间



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
	}
	//get方法
	public String getUsername() {
		return username;
	}

	//set方法
	public void setUsername(String username) {
		this.username = username;
	}
	//get方法
	public String getPassword() {
		return password;
	}

	//set方法
	public void setPassword(String password) {
		this.password = password;
	}
	//get方法
	public String getPhone() {
		return phone;
	}

	//set方法
	public void setPhone(String phone) {
		this.phone = phone;
	}
	//get方法
	public String getEmail() {
		return email;
	}

	//set方法
	public void setEmail(String email) {
		this.email = email;
	}
	//get方法
	public Date getCreated() {
		return created;
	}

	//set方法
	public void setCreated(Date created) {
		this.created = created;
	}
	//get方法
	public Date getUpdated() {
		return updated;
	}

	//set方法
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	//get方法
	public String getSourceType() {
		return sourceType;
	}

	//set方法
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	//get方法
	public String getNickName() {
		return nickName;
	}

	//set方法
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	public String getStatus() {
		return status;
	}

	//set方法
	public void setStatus(String status) {
		this.status = status;
	}
	//get方法
	public String getHeadPic() {
		return headPic;
	}

	//set方法
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	//get方法
	public String getQq() {
		return qq;
	}

	//set方法
	public void setQq(String qq) {
		this.qq = qq;
	}
	//get方法
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	//set方法
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	//get方法
	public String getIsMobileCheck() {
		return isMobileCheck;
	}

	//set方法
	public void setIsMobileCheck(String isMobileCheck) {
		this.isMobileCheck = isMobileCheck;
	}
	//get方法
	public String getIsEmailCheck() {
		return isEmailCheck;
	}

	//set方法
	public void setIsEmailCheck(String isEmailCheck) {
		this.isEmailCheck = isEmailCheck;
	}
	//get方法
	public String getSex() {
		return sex;
	}

	//set方法
	public void setSex(String sex) {
		this.sex = sex;
	}
	//get方法
	public Integer getUserLevel() {
		return userLevel;
	}

	//set方法
	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}
	//get方法
	public Integer getPoints() {
		return points;
	}

	//set方法
	public void setPoints(Integer points) {
		this.points = points;
	}
	//get方法
	public Integer getExperienceValue() {
		return experienceValue;
	}

	//set方法
	public void setExperienceValue(Integer experienceValue) {
		this.experienceValue = experienceValue;
	}
	//get方法
	public Date getBirthday() {
		return birthday;
	}

	//set方法
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	//get方法
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	//set方法
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}


}
