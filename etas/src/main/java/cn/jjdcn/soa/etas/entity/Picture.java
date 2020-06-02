package cn.jjdcn.soa.etas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (Picture)实体类
 *
 * @author jjdcn
 * @since 2020-03-07 23:29:27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Picture implements Serializable {
    private static final long serialVersionUID = 906937914848564263L;
    /**
     * id
     */
    private Integer id;
    /**
     * 文件fileID
     */
    private String fileId;
    /**
     * 图片地址
     */
    private String url;
    /**
     * 添加人
     */
    private Long userId;
    /**
     * 添加时间
     */
    private Date createTime;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 图片文件原名
     */
    private String originName;
    /**
     * 图片题注
     */
    private String caption;
}