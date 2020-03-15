package cn.jjdcn.etas.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 
 * @author jjdcn
 * @email shzu_csy@163.com
 * @date 2020-02-14 15:12:01
 */
@ApiModel
@Data
@TableName("user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty(name = "id",value = "")
	private Long id;
	/**
	 * 
	 */
	@ApiModelProperty(name = "createTime",value = "")
	private Date createTime;
	/**
	 * 
	 */
	@ApiModelProperty(name = "lastLoginTime",value = "")
	private Date lastLoginTime;
	/**
	 * 
	 */
	@ApiModelProperty(name = "loginTimes",value = "")
	private Long loginTimes;

	/**
	 *
	 */
	@ApiModelProperty(name = "salt",value = "")
	private String salt;

	/**
	 * 
	 */
	@ApiModelProperty(name = "password",value = "")
	private String password;
	/**
	 * 
	 */
	@ApiModelProperty(name = "username",value = "")
	private String username;

}
