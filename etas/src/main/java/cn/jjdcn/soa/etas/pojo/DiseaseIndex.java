package cn.jjdcn.soa.etas.pojo;


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
@Document(indexName = "manage", type = "disease", shards = 3, replicas = 2)
public class DiseaseIndex {

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
    @Field(type = FieldType.Keyword, index = false)
    private String symptomsPic;
    /**
     * 病症特征
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String symptomsDesc;
    /**
     * 病原图片(显微镜)存储picture-id,';'间隔
     */
    @Field(type = FieldType.Keyword, index = false)
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
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
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
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String classSpecies;
    /**
     * 病毒种类
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String virusType;
    /**
     * 状态:0正常,1:已删除
     */
    @Field(type = FieldType.Integer, index = false)
    private Integer status;
    /**
     * 添加者id
     */
    @Field(type = FieldType.Integer, index = false)
    private Integer userId;
    /**
     * 创建时间
     */
    @Field(type = FieldType.Date, index = false)
    private Date createTime;
    /**
     * 点击量
     */
    @Field(type = FieldType.Integer, index = false)
    private Integer clickCount;
    /**
     * 病原种类
     */
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String pathogenType;

    public void set(String key, String value) {
        switch (key) {
            case "name":
                this.setName(value);
                break;
            case "symptomsDesc":
                this.setSymptomsDesc(value);
                break;
            case "pathogenDesc":
                this.setPathogenDesc(value);
                break;
            case "prevention":
                this.setPrevention(value);
                break;
            case "classDomain":
                this.setClassDomain(value);
                break;
            case "classPhylum":
                this.setClassPhylum(value);
                break;
            case "classFamily":
                this.setClassFamily(value);
                break;
            case "classOrder":
                this.setClassOrder(value);
                break;
            case "classClass":
                this.setClassClass(value);
                break;
            case "classGenus":
                this.setClassGenus(value);
                break;
            case "classSpecies":
                this.setClassSpecies(value);
                break;
            case "virusType":
                this.setVirusType(value);
                break;
            case "pathogenType":
                this.setPathogenType(value);
                break;
            default:
        }
    }
}

