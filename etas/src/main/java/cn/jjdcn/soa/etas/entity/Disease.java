package cn.jjdcn.soa.etas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 植病表(Disease)实体类
 *
 * @author jjdcn
 * @since 2020-03-09 14:14:29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Disease implements Serializable {
    private static final long serialVersionUID = 103050651706157656L;
    /**
    * ID
    */
    private Integer id;
    /**
    * 疾病名称
    */
    private String name;
    /**
    * 病症照片 存储picture-id,';'间隔
    */
    private String symptomsPic;
    /**
    * 病症特征
    */
    private String symptomsDesc;
    /**
    * 病原图片(显微镜)存储picture-id,';'间隔
    */
    private String pathogenPic;
    /**
    * 病原描述
    */
    private String pathogenDesc;
    /**
    * 防治
    */
    private String prevention;
    /**
    * 界
    */
    private String classDomain;
    /**
    * 门
    */
    private String classPhylum;
    /**
    * 科
    */
    private String classFamily;
    /**
    * 目
    */
    private String classOrder;
    /**
    * 纲
    */
    private String classClass;
    /**
    * 属
    */
    private String classGenus;
    /**
    * 种(病原)
    */
    private String classSpecies;
    /**
    * 病毒种类
    */
    private String virusType;
    /**
    * 状态:0正常,1:已删除
    */
    private Integer status;
    /**
    * 添加者id
    */
    private Integer userId;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 点击量
    */
    private Integer clickCount;
    /**
    * 病原种类
    */
    private String pathogenType;
}