package cn.jjdcn.etas.common.response;

/**
 * 版权：（版权〉
 * 描述：〈描述〉
 * 修改人：姓名(工号)
 * 修改时间：YYYY-MM-DD
 * 需求单号：〈需求单号〉
 * 修改内容：〈修改内容〉
 */
public enum ResponseMessage {

	OK("请求成功"),
	ERROR("请求失败"),
	PERMISSION_DENY("请求失败，权限不足"),
	NOT_FOUND("请求失败，资源不存在");

	private String message;

	ResponseMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
