package com.bhusk.utils;


/**
 * 执行结果bean
 * @author  
 */
public class StatusBean<T> {
	/** 状态码 */
	private boolean status;
	/** 异常码 */
	private String code;
	/** 消息 */
	private String msg;
	/** 结果 */
	private T data;
	/**
	 * 构造状态BEAN
	 * @param s
	 */
	public StatusBean(String s) {
	}
	/**
	 * 构造状态BEAN
	 * @param status 是否成功
	 * @param msg 消息
	 */
	public StatusBean(boolean status, String msg){
		this.status = status;
		this.msg = msg;
	}
	/**
	 * 构造状态BEAN
	 * @param status 是否成功
	 * @param msg 消息
	 */
	public StatusBean(boolean status, String msg, T data){
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	/**
	 * 构造状态BEAN
	 * @param status
	 * @param code
	 * @param msg
	 * @param data
	 */
	public StatusBean(boolean status, String code, String msg, T data){
		this.status = status;
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	/**
	 * 构造状态BEAN
	 * @param status 是否成功
	 * @param data 消息
	 */
	public StatusBean(boolean status, T data){
		this.status = status;
		this.msg = "";
		this.data = data;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
