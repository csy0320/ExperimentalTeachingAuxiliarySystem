package cn.jjdcn.soa.etas.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiseaseDto implements Serializable {

    private static final long serialVersionUID = 103050651706157656L;
    /**
     * id
     */
    private Integer id;
    /**
     * 疾病名称
     */
    @NotEmpty(message = "疾病名不能为空")
    private String name;
    /**
     * 病症照片
     */
    private List<Integer> symptomsPics;
    /**
     * 病症特征
     */
    private String symptomsDesc;
    /**
     * 病原图片
     */
    private List<Integer> pathogenPics;
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
     * 病原种类
     */
    private String pathogenType;
}
