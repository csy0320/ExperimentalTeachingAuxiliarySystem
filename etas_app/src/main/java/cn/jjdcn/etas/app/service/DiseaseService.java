package cn.jjdcn.etas.app.service;

import cn.jjdcn.etas.app.mapper.fdfs.PictureMapper;
import cn.jjdcn.etas.app.mapper.manage.DiseaseMapper;
import cn.jjdcn.etas.app.pojo.Disease;
import cn.jjdcn.etas.app.pojo.DiseaseVO;
import cn.jjdcn.etas.app.pojo.Picture;
import cn.jjdcn.etas.common.utils.StringToListUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 植病表(Disease)表服务实现类
 *
 * @author jjdcn
 * @since 2020-04-14
 */
@Service("diseaseService")
@Slf4j
public class DiseaseService{
    @Autowired
    private DiseaseMapper diseaseMapper;

    @Autowired
    private PictureMapper pictureMapper;


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public DiseaseVO queryById(Integer id) {
        log.info("queryBydiseaseId:{}",id);
        Disease disease = this.diseaseMapper.queryById(id);
        if (disease == null) return null;
        log.info("disease:{}",disease);

        List ids1 =  StringToListUtils.splitIdsString(disease.getPathogenPic() == null ? "" : disease.getPathogenPic());
        List ids2 =  StringToListUtils.splitIdsString(disease.getSymptomsPic() == null ? "" : disease.getSymptomsPic());
        List<Picture> pictures1 = new ArrayList<>();
        List<Picture> pictures2 = new ArrayList<>();
        if (ids1.size()>0){
            pictures1 = pictureMapper.queryByIds(ids1);
        }
        if (ids2.size()>0){
            pictures2 = pictureMapper.queryByIds(ids2);
        }
        return DiseaseVO.builder()
                .id(disease.getId())
                .name(disease.getName())
                .classClass(disease.getClassClass() == null ? "" : disease.getClassClass())
                .classDomain(disease.getClassDomain() == null ? "" : disease.getClassDomain())
                .classFamily(disease.getClassFamily() == null ? "" : disease.getClassFamily())
                .classGenus(disease.getClassGenus() == null ? "" : disease.getClassGenus())
                .classOrder(disease.getClassOrder() == null ? "" : disease.getClassOrder())
                .classPhylum(disease.getClassPhylum() == null ? "" : disease.getClassPhylum())
                .classSpecies(disease.getClassSpecies() == null ? "" : disease.getClassSpecies())
                .pathogenDesc(disease.getPathogenDesc() == null ? "" : disease.getPathogenDesc())
                .pathogenPics(pictures1 == null ? new ArrayList<>() : pictures1)
                .symptomsPics(pictures2 == null ? new ArrayList<>() : pictures2)
                .symptomsDesc(disease.getSymptomsDesc() == null ? "" : disease.getSymptomsDesc())
                .prevention(disease.getPrevention() == null ? "" : disease.getPrevention())
                .virusType(disease.getVirusType() == null ? "" : disease.getVirusType())
                .pathogenType(disease.getPathogenType() == null ? "" : disease.getPathogenType()).build();
    }
}