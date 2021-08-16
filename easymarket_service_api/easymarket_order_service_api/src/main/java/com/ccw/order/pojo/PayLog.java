package com.ccw.order.pojo;

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
 * @Description:PayLog构建
 * @Date 2021/2/1 14:19
 *****/
@ApiModel(description = "PayLog",value = "PayLog")
@TableName(value="tb_pay_log")
public class PayLog implements Serializable{

	@ApiModelProperty(value = "支付订单号",required = false)
    @TableId(type = IdType.INPUT)
    @TableField(value = "out_trade_no")
	private String outTradeNo;//支付订单号

	@ApiModelProperty(value = "创建日期",required = false)
    @TableField(value = "create_time")
	private Date createTime;//创建日期

	@ApiModelProperty(value = "支付完成时间",required = false)
    @TableField(value = "pay_time")
	private Date payTime;//支付完成时间

	@ApiModelProperty(value = "支付金额（分）",required = false)
    @TableField(value = "total_fee")
	private Long totalFee;//支付金额（分）

	@ApiModelProperty(value = "用户ID",required = false)
    @TableField(value = "user_id")
	private String userId;//用户ID

	@ApiModelProperty(value = "交易号码",required = false)
    @TableField(value = "transaction_id")
	private String transactionId;//交易号码

	@ApiModelProperty(value = "交易状态",required = false)
    @TableField(value = "trade_state")
	private String tradeState;//交易状态

	@ApiModelProperty(value = "订单编号列表",required = false)
    @TableField(value = "order_list")
	private String orderList;//订单编号列表

	@ApiModelProperty(value = "支付类型",required = false)
    @TableField(value = "pay_type")
	private String payType;//支付类型



	//get方法
	public String getOutTradeNo() {
		return outTradeNo;
	}

	//set方法
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
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
	public Long getTotalFee() {
		return totalFee;
	}

	//set方法
	public void setTotalFee(Long totalFee) {
		this.totalFee = totalFee;
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
	public String getTransactionId() {
		return transactionId;
	}

	//set方法
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	//get方法
	public String getTradeState() {
		return tradeState;
	}

	//set方法
	public void setTradeState(String tradeState) {
		this.tradeState = tradeState;
	}
	//get方法
	public String getOrderList() {
		return orderList;
	}

	//set方法
	public void setOrderList(String orderList) {
		this.orderList = orderList;
	}
	//get方法
	public String getPayType() {
		return payType;
	}

	//set方法
	public void setPayType(String payType) {
		this.payType = payType;
	}


}
