package cn.jjdcn.etas.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Setting)实体类
 *
 * @author jjdcn
 * @since 2020-03-13 21:52:45
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Setting implements Serializable {
    private static final long serialVersionUID = 362393495243280519L;
    /**
    * 主键
    */
    private Integer id;
    /**
    * 用户id
    */
    private Integer userId;
    /**
    * 表格size
    */
    private Integer pageSize;
}