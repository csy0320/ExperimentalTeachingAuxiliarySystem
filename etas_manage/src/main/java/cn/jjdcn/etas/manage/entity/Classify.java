package cn.jjdcn.etas.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分类表(Classify)实体类
 *
 * @author jjdcn
 * @since 2020-03-09 14:14:25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Classify implements Serializable {
    private static final long serialVersionUID = 702496867288281642L;
    /**
    * id
    */
    private Integer id;
    /**
    * 类别名称
    */
    private String name;
    /**
    * 上级门类id
    */
    private Integer parentId;
    /**
    * 描述
    */
    private String description;
}