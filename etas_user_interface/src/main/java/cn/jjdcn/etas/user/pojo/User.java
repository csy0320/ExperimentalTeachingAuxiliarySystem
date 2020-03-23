package cn.jjdcn.etas.user.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author jjdcn
 * @email shzu_csy@163.com
 * @date 2020-02-14 15:12:01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	private Long id;
	/**
	 * 
	 */

	private Date createTime;
	/**
	 * 
	 */

	private Date lastLoginTime;
	/**
	 * 
	 */

	private Long loginTimes;

	/**
	 *
	 */

	private String salt;

	/**
	 * 
	 */

	private String password;
	/**
	 * 
	 */

	private String username;

}
