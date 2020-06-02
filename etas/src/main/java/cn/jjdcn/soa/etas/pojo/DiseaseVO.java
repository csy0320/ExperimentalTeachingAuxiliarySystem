package cn.jjdcn.soa.etas.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 疾病的值对象类,通过id查询返回给前端的数据
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiseaseVO {
    private Integer id;
    private String name;
    private String symptomsDesc;
    private String pathogenDesc;
    private String prevention;
    private String pathogenType;
    private String classClass;
    private String classDomain;
    private String classFamily;
    private String classGenus;
    private String classOrder;
    private String classPhylum;
    private String classSpecies;
    private String virusType;
    private List<Picture> symptomsPics;
    private List<Picture> pathogenPics;

}
