package cn.jjdcn.etas.manage.service.impl;

import cn.jjdcn.etas.common.bean.Result;
import cn.jjdcn.etas.fdfs.entity.Picture;
import cn.jjdcn.etas.manage.entity.Disease;
import cn.jjdcn.etas.manage.dao.DiseaseDao;
import cn.jjdcn.etas.manage.feign.PictureClient;
import cn.jjdcn.etas.manage.pojo.vo.DiseaseVO;
import cn.jjdcn.etas.manage.service.DiseaseService;
import cn.jjdcn.etas.manage.pojo.dto.DiseaseDto;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 植病表(Disease)表服务实现类
 *
 * @author jjdcn
 * @since 2020-03-09 14:14:29
 */
@Service("diseaseService")
public class DiseaseServiceImpl implements DiseaseService {
    @Resource
    private DiseaseDao diseaseDao;


    @Autowired
    private PictureClient pictureClient;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DiseaseVO queryById(Integer id) {
        Disease disease = this.diseaseDao.queryById(id);
        List<Picture> pictures1 = pictureClient.doQueryPictureByIds(disease.getPathogenPic() == null ? "" : disease.getPathogenPic());
        List<Picture> pictures2 = pictureClient.doQueryPictureByIds(disease.getSymptomsPic() == null ? "" : disease.getSymptomsPic());
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
                .pathogenPics(pictures1)
                .symptomsPics(pictures2)
                .symptomsDesc(disease.getSymptomsDesc() == null ? "" : disease.getSymptomsDesc())
                .prevention(disease.getPrevention() == null ? "" : disease.getPrevention())
                .virusType(disease.getVirusType() == null ? "" : disease.getVirusType())
                .pathogenType(disease.getPathogenType() == null ? "" : disease.getPathogenType()).build();
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Disease> queryAllByLimit(int offset, int limit) {
        return this.diseaseDao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<Disease> queryAll() {
        return this.diseaseDao.queryAll(new Disease());
    }

    /**
     * 新增数据
     *
     * @param diseaseDto 实例对象
     * @return 实例对象
     */
    @Override
    public Disease insert(DiseaseDto diseaseDto) {
        Disease disease = Disease.builder()
                .name(diseaseDto.getName())
                .classClass(diseaseDto.getClassClass())
                .classDomain(diseaseDto.getClassDomain())
                .classFamily(diseaseDto.getClassFamily())
                .classGenus(diseaseDto.getClassGenus())
                .classOrder(diseaseDto.getClassOrder())
                .classPhylum(diseaseDto.getClassPhylum())
                .classSpecies(diseaseDto.getClassSpecies())
                .createTime(new Date())
                .pathogenDesc(diseaseDto.getPathogenDesc())
                .pathogenPic(Joiner.on("-").join(diseaseDto.getPathogenPics()))
                .symptomsPic(Joiner.on("-").join(diseaseDto.getSymptomsPics()))
                .symptomsDesc(diseaseDto.getSymptomsDesc())
                .prevention(diseaseDto.getPrevention())
                .virusType(diseaseDto.getVirusType())
                .pathogenType(diseaseDto.getPathogenType())
                .status(0).userId(1).clickCount(0).build();
        this.diseaseDao.insert(disease);
        return disease;
    }

    /**
     * 修改数据
     *
     * @param diseaseDto 实例对象
     * @return int
     */
    @Override
    public int update(DiseaseDto diseaseDto) {
        Disease disease = Disease.builder()
                .id(diseaseDto.getId())
                .name(diseaseDto.getName())
                .classClass(diseaseDto.getClassClass())
                .classDomain(diseaseDto.getClassDomain())
                .classFamily(diseaseDto.getClassFamily())
                .classGenus(diseaseDto.getClassGenus())
                .classOrder(diseaseDto.getClassOrder())
                .classPhylum(diseaseDto.getClassPhylum())
                .classSpecies(diseaseDto.getClassSpecies())
                .pathogenDesc(diseaseDto.getPathogenDesc())
                .pathogenPic(Joiner.on("-").join(diseaseDto.getPathogenPics()))
                .symptomsPic(Joiner.on("-").join(diseaseDto.getSymptomsPics()))
                .symptomsDesc(diseaseDto.getSymptomsDesc())
                .prevention(diseaseDto.getPrevention())
                .virusType(diseaseDto.getVirusType())
                .pathogenType(diseaseDto.getPathogenType())
                .build();
        return this.diseaseDao.update(disease);

    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.diseaseDao.deleteById(id) > 0;
    }

    @Override
    public List<Disease> queryAllByFilter(DiseaseDto dto) {

        Disease disease = Disease.builder().name(dto.getName())
                .classClass(dto.getClassClass())
                .classDomain(dto.getClassDomain())
                .classFamily(dto.getClassFamily())
                .classGenus(dto.getClassGenus())
                .classOrder(dto.getClassOrder())
                .classPhylum(dto.getClassPhylum())
                .classSpecies(dto.getClassSpecies())
                .pathogenDesc(dto.getPathogenDesc())
                .symptomsDesc(dto.getSymptomsDesc())
                .prevention(dto.getPrevention())
                .virusType(dto.getVirusType())
                .pathogenType(dto.getPathogenType())
                .build();
        return diseaseDao.queryAll(disease);
    }

    @Override
    public int countAllByFilter(DiseaseDto dto) {
        Disease disease = Disease.builder().name(dto.getName())
                .classClass(dto.getClassClass())
                .classDomain(dto.getClassDomain())
                .classFamily(dto.getClassFamily())
                .classGenus(dto.getClassGenus())
                .classOrder(dto.getClassOrder())
                .classPhylum(dto.getClassPhylum())
                .classSpecies(dto.getClassSpecies())
                .pathogenDesc(dto.getPathogenDesc())
                .symptomsDesc(dto.getSymptomsDesc())
                .prevention(dto.getPrevention())
                .virusType(dto.getVirusType())
                .pathogenType(dto.getPathogenType())
                .build();
        return diseaseDao.countAllByFilter(disease);
    }

    @Override
    public Map queryBaseInfo() {
        HashMap<Object, Object> baseInfo = Maps.newHashMap();
        baseInfo.put("diseaseNames", this.diseaseDao.queryBaseInfo("name"));
        baseInfo.put("classGenuses", this.diseaseDao.queryBaseInfo("class_genus"));
        baseInfo.put("classClasses", this.diseaseDao.queryBaseInfo("class_class"));
        baseInfo.put("classOrders", this.diseaseDao.queryBaseInfo("class_order"));
        baseInfo.put("classFamilies", this.diseaseDao.queryBaseInfo("class_family"));
        baseInfo.put("classPhylums", this.diseaseDao.queryBaseInfo("class_phylum"));
        baseInfo.put("virusTypes", this.diseaseDao.queryBaseInfo("virus_type"));
        baseInfo.put("classSpecies", this.diseaseDao.queryBaseInfo("class_species"));
        return baseInfo;
    }
}