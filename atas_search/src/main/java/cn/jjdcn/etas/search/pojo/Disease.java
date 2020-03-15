package cn.jjdcn.etas.search.pojo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "disease",shards = 3,replicas = 2)
public class Disease {

    @Id
    private Integer id;
    /**
     * 疾病名称
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;
    /**
     * 病症照片 存储picture-id,';'间隔
     */
    private String symptomsPic;
    /**
     * 病症特征
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String symptomsDesc;
    /**
     * 病原图片(显微镜)存储picture-id,';'间隔
     */
    private String pathogenPic;
    /**
     * 病原描述
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String pathogenDesc;
    /**
     * 防治
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String prevention;
    /**
     * 界
     */
    private String classDomain;
    /**
     * 门
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String classPhylum;
    /**
     * 科
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String classFamily;
    /**
     * 目
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String classOrder;
    /**
     * 纲
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String classClass;
    /**
     * 属
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String classGenus;
    /**
     * 种(病原)
     */
    @Field(type = FieldType.Text, index = false)
    private String classSpecies;
    /**
     * 病毒种类
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
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
    @Field(type = FieldType.Keyword, index = false)
    private String pathogenType;
}
