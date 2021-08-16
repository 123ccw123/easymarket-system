package com.ccw.seckill.pojo;

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
 * @Description:SeckillOrder构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "SeckillOrder",value = "SeckillOrder")
@TableName(value="tb_seckill_order")
public class SeckillOrder implements Serializable{

	@ApiModelProperty(value = "主键",required = false)
    @TableId(type = IdType.INPUT)
    @TableField(value = "id")
	private Long id;//主键

	@ApiModelProperty(value = "秒杀商品ID",required = false)
    @TableField(value = "seckill_id")
	private Long seckillId;//秒杀商品ID

	@ApiModelProperty(value = "支付金额",required = false)
    @TableField(value = "money")
	private BigDecimal money;//支付金额

	@ApiModelProperty(value = "用户",required = false)
    @TableField(value = "user_id")
	private String userId;//用户

	@ApiModelProperty(value = "商家",required = false)
    @TableField(value = "seller_id")
	private String sellerId;//商家

	@ApiModelProperty(value = "创建时间",required = false)
    @TableField(value = "create_time")
	private Date createTime;//创建时间

	@ApiModelProperty(value = "支付时间",required = false)
    @TableField(value = "pay_time")
	private Date payTime;//支付时间

	@ApiModelProperty(value = "状态",required = false)
    @TableField(value = "status")
	private String status;//状态

	@ApiModelProperty(value = "收货人地址",required = false)
    @TableField(value = "receiver_address")
	private String receiverAddress;//收货人地址

	@ApiModelProperty(value = "收货人电话",required = false)
    @TableField(value = "receiver_mobile")
	private String receiverMobile;//收货人电话

	@ApiModelProperty(value = "收货人",required = false)
    @TableField(value = "receiver")
	private String receiver;//收货人

	@ApiModelProperty(value = "交易流水",required = false)
    @TableField(value = "transaction_id")
	private String transactionId;//交易流水



	//get方法
	public Long getId() {
		return id;
	}

	//set方法
	public void setId(Long id) {
		this.id = id;
	}
	//get方法
	public Long getSeckillId() {
		return seckillId;
	}

	//set方法
	public void setSeckillId(Long seckillId) {
		this.seckillId = seckillId;
	}
	//get方法
	public BigDecimal getMoney() {
		return money;
	}

	//set方法
	public void setMoney(BigDecimal money) {
		this.money = money;
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
	public String getSellerId() {
		return sellerId;
	}

	//set方法
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
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
	public Date getPayTime() {
		return payTime;
	}

	//set方法
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
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
	public String getReceiverAddress() {
		return receiverAddress;
	}

	//set方法
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	//get方法
	public String getReceiverMobile() {
		return receiverMobile;
	}

	//set方法
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	//get方法
	public String getReceiver() {
		return receiver;
	}

	//set方法
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	//get方法
	public String getTransactionId() {
		return transactionId;
	}

	//set方法
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


}
