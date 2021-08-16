package com.ccw.sellersgoods.pojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.lang.Long;
import java.util.Date;
import java.lang.String;
/****
 * @Author:ujiuye
 * @Description:Seller构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "Seller",value = "Seller")
@TableName(value="tb_seller")
public class Seller implements Serializable{

	@ApiModelProperty(value = "用户ID",required = false)
    @TableId(type = IdType.AUTO)
    @TableField(value = "seller_id")
	private String sellerId;//用户ID

	@ApiModelProperty(value = "公司名",required = false)
    @TableField(value = "name")
	private String name;//公司名

	@ApiModelProperty(value = "店铺名称",required = false)
    @TableField(value = "nick_name")
	private String nickName;//店铺名称

	@ApiModelProperty(value = "密码",required = false)
    @TableField(value = "password")
	private String password;//密码

	@ApiModelProperty(value = "EMAIL",required = false)
    @TableField(value = "email")
	private String email;//EMAIL

	@ApiModelProperty(value = "公司手机",required = false)
    @TableField(value = "mobile")
	private String mobile;//公司手机

	@ApiModelProperty(value = "公司电话",required = false)
    @TableField(value = "telephone")
	private String telephone;//公司电话

	@ApiModelProperty(value = "状态",required = false)
    @TableField(value = "status")
	private String status;//状态

	@ApiModelProperty(value = "详细地址",required = false)
    @TableField(value = "address_detail")
	private String addressDetail;//详细地址

	@ApiModelProperty(value = "联系人姓名",required = false)
    @TableField(value = "linkman_name")
	private String linkmanName;//联系人姓名

	@ApiModelProperty(value = "联系人QQ",required = false)
    @TableField(value = "linkman_qq")
	private String linkmanQq;//联系人QQ

	@ApiModelProperty(value = "联系人电话",required = false)
    @TableField(value = "linkman_mobile")
	private String linkmanMobile;//联系人电话

	@ApiModelProperty(value = "联系人EMAIL",required = false)
    @TableField(value = "linkman_email")
	private String linkmanEmail;//联系人EMAIL

	@ApiModelProperty(value = "营业执照号",required = false)
    @TableField(value = "license_number")
	private String licenseNumber;//营业执照号

	@ApiModelProperty(value = "税务登记证号",required = false)
    @TableField(value = "tax_number")
	private String taxNumber;//税务登记证号

	@ApiModelProperty(value = "组织机构代码",required = false)
    @TableField(value = "org_number")
	private String orgNumber;//组织机构代码

	@ApiModelProperty(value = "公司地址",required = false)
    @TableField(value = "address")
	private Long address;//公司地址

	@ApiModelProperty(value = "公司LOGO图",required = false)
    @TableField(value = "logo_pic")
	private String logoPic;//公司LOGO图

	@ApiModelProperty(value = "简介",required = false)
    @TableField(value = "brief")
	private String brief;//简介

	@ApiModelProperty(value = "创建日期",required = false)
    @TableField(value = "create_time")
	private Date createTime;//创建日期

	@ApiModelProperty(value = "法定代表人",required = false)
    @TableField(value = "legal_person")
	private String legalPerson;//法定代表人

	@ApiModelProperty(value = "法定代表人身份证",required = false)
    @TableField(value = "legal_person_card_id")
	private String legalPersonCardId;//法定代表人身份证

	@ApiModelProperty(value = "开户行账号名称",required = false)
    @TableField(value = "bank_user")
	private String bankUser;//开户行账号名称

	@ApiModelProperty(value = "开户行",required = false)
    @TableField(value = "bank_name")
	private String bankName;//开户行



	//get方法
	public String getSellerId() {
		return sellerId;
	}

	//set方法
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
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
	public String getNickName() {
		return nickName;
	}

	//set方法
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	public String getEmail() {
		return email;
	}

	//set方法
	public void setEmail(String email) {
		this.email = email;
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
	public String getTelephone() {
		return telephone;
	}

	//set方法
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	public String getAddressDetail() {
		return addressDetail;
	}

	//set方法
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	//get方法
	public String getLinkmanName() {
		return linkmanName;
	}

	//set方法
	public void setLinkmanName(String linkmanName) {
		this.linkmanName = linkmanName;
	}
	//get方法
	public String getLinkmanQq() {
		return linkmanQq;
	}

	//set方法
	public void setLinkmanQq(String linkmanQq) {
		this.linkmanQq = linkmanQq;
	}
	//get方法
	public String getLinkmanMobile() {
		return linkmanMobile;
	}

	//set方法
	public void setLinkmanMobile(String linkmanMobile) {
		this.linkmanMobile = linkmanMobile;
	}
	//get方法
	public String getLinkmanEmail() {
		return linkmanEmail;
	}

	//set方法
	public void setLinkmanEmail(String linkmanEmail) {
		this.linkmanEmail = linkmanEmail;
	}
	//get方法
	public String getLicenseNumber() {
		return licenseNumber;
	}

	//set方法
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	//get方法
	public String getTaxNumber() {
		return taxNumber;
	}

	//set方法
	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}
	//get方法
	public String getOrgNumber() {
		return orgNumber;
	}

	//set方法
	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}
	//get方法
	public Long getAddress() {
		return address;
	}

	//set方法
	public void setAddress(Long address) {
		this.address = address;
	}
	//get方法
	public String getLogoPic() {
		return logoPic;
	}

	//set方法
	public void setLogoPic(String logoPic) {
		this.logoPic = logoPic;
	}
	//get方法
	public String getBrief() {
		return brief;
	}

	//set方法
	public void setBrief(String brief) {
		this.brief = brief;
	}
	//get方法
	public Date getCreateTime() {
		return createTime;
	}

	//set方法
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	//get方法
	public String getLegalPerson() {
		return legalPerson;
	}

	//set方法
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	//get方法
	public String getLegalPersonCardId() {
		return legalPersonCardId;
	}

	//set方法
	public void setLegalPersonCardId(String legalPersonCardId) {
		this.legalPersonCardId = legalPersonCardId;
	}
	//get方法
	public String getBankUser() {
		return bankUser;
	}

	//set方法
	public void setBankUser(String bankUser) {
		this.bankUser = bankUser;
	}
	//get方法
	public String getBankName() {
		return bankName;
	}

	//set方法
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


}
