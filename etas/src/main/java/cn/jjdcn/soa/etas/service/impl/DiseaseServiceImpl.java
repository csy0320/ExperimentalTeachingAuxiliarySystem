package cn.jjdcn.soa.etas.service.impl;

import cn.jjdcn.soa.etas.entity.Disease;
import cn.jjdcn.soa.etas.entity.Picture;
import cn.jjdcn.soa.etas.mapper.manage.DiseaseMapper;
import cn.jjdcn.soa.etas.pojo.DiseaseIndex;
import cn.jjdcn.soa.etas.pojo.dto.DiseaseDto;
import cn.jjdcn.soa.etas.pojo.vo.DiseaseVO;
import cn.jjdcn.soa.etas.service.DiseaseIndexService;
import cn.jjdcn.soa.etas.service.DiseaseService;
import cn.jjdcn.soa.etas.service.PictureService;
import cn.jjdcn.soa.etas.utils.StringToListUtils;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 植病表(Disease)表服务实现类
 *
 * @author jjdcn
 * @since 2020-03-09 14:14:29
 */
@Slf4j
@Service("diseaseService")
public class DiseaseServiceImpl implements DiseaseService {
    @Resource
    private DiseaseMapper diseaseMapper;

    @Autowired
    private DiseaseIndexService diseaseIndexService;

    @Autowired
    private PictureService pictureService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    @Cacheable(value = "disease", key = "#id")
    public DiseaseVO queryById(Integer id) {
        log.info("查询了ID: {}", id);
        Disease disease = this.diseaseMapper.queryById(id);
        List<Picture> pictures1 = pictureService.queryByIds(StringToListUtils.splitIdsString(disease.getPathogenPic() == null ? "" : disease.getPathogenPic()));
        List<Picture> pictures2 = pictureService.queryByIds(StringToListUtils.splitIdsString(disease.getSymptomsPic() == null ? "" : disease.getSymptomsPic()));
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
        return this.diseaseMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    @Override
    public List<Disease> queryAll() {
        return this.diseaseMapper.queryAll(new Disease());
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
        this.diseaseMapper.insert(disease);

        diseaseIndexService.update(
                DiseaseIndex.builder()
                        .id(disease.getId())
                        .name(disease.getName())
                        .classClass(disease.getClassClass())
                        .classDomain(disease.getClassDomain())
                        .classFamily(disease.getClassFamily())
                        .classGenus(disease.getClassGenus())
                        .classOrder(disease.getClassOrder())
                        .classPhylum(disease.getClassPhylum())
                        .classSpecies(disease.getClassSpecies())
                        .pathogenDesc(disease.getPathogenDesc())
                        .symptomsDesc(disease.getSymptomsDesc())
                        .prevention(disease.getPrevention())
                        .virusType(disease.getVirusType())
                        .pathogenType(disease.getPathogenType())
                        .build());
        return disease;
    }

    /**
     * 修改数据
     *
     * @param diseaseDto 实例对象
     * @return int
     */
    @Override
    @CacheEvict(value = "disease", key = "#diseaseDto.id")
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

        diseaseIndexService.update(
                DiseaseIndex.builder()
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
                        .symptomsDesc(diseaseDto.getSymptomsDesc())
                        .prevention(diseaseDto.getPrevention())
                        .virusType(diseaseDto.getVirusType())
                        .pathogenType(diseaseDto.getPathogenType())
                        .build());

        return this.diseaseMapper.update(disease);

    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        diseaseIndexService.delete(id);
        return this.diseaseMapper.deleteById(id) > 0;
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
        return diseaseMapper.queryAll(disease);
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
        return diseaseMapper.countAllByFilter(disease);
    }

    @Override
    public Map queryBaseInfo() {
        HashMap<Object, Object> baseInfo = Maps.newHashMap();
        baseInfo.put("diseaseNames", this.diseaseMapper.queryBaseInfo("name").stream().filter(Objects::nonNull).collect(Collectors.toList()));
        baseInfo.put("classGenuses", this.diseaseMapper.queryBaseInfo("class_genus").stream().filter(Objects::nonNull).collect(Collectors.toList()));
        baseInfo.put("classClasses", this.diseaseMapper.queryBaseInfo("class_class").stream().filter(Objects::nonNull).collect(Collectors.toList()));
        baseInfo.put("classOrders", this.diseaseMapper.queryBaseInfo("class_order").stream().filter(Objects::nonNull).collect(Collectors.toList()));
        baseInfo.put("classFamilies", this.diseaseMapper.queryBaseInfo("class_family").stream().filter(Objects::nonNull).collect(Collectors.toList()));
        baseInfo.put("classPhylums", this.diseaseMapper.queryBaseInfo("class_phylum").stream().filter(Objects::nonNull).collect(Collectors.toList()));
        baseInfo.put("virusTypes", this.diseaseMapper.queryBaseInfo("virus_type").stream().filter(Objects::nonNull).collect(Collectors.toList()));
        baseInfo.put("classSpecies", this.diseaseMapper.queryBaseInfo("class_species").stream().filter(Objects::nonNull).collect(Collectors.toList()));
        return baseInfo;
    }
}