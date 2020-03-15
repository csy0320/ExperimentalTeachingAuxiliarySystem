package cn.jjdcn.etas.common.response;


public enum ResponseCode {

	OK(20000),
	ERROR(20001),// 请求失败
	PERMISSION_DENY(20002),// 权限不足
	NOT_FOUND(20003);// 资源不存在

	private Integer code;

	ResponseCode(Integer code){
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}
}
