package cn.jjdcn.soa.etas.service;

import cn.jjdcn.soa.etas.entity.Disease;
import cn.jjdcn.soa.etas.pojo.dto.DiseaseDto;
import cn.jjdcn.soa.etas.pojo.vo.DiseaseVO;

import java.util.List;
import java.util.Map;

/**
 * 植病表(Disease)表服务接口
 *
 * @author jjdcn
 * @since 2020-03-09 14:14:29
 */
public interface DiseaseService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DiseaseVO queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Disease> queryAllByLimit(int offset, int limit);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<Disease> queryAll();

    /**
     * 新增数据
     *
     * @param diseaseDto 实例对象
     * @return 实例对象
     */
    Disease insert(DiseaseDto diseaseDto);

    /**
     * 修改数据
     *
     * @param diseaseDto 实例对象
     * @return int
     */
    int update(DiseaseDto diseaseDto);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 通过条件查询Disease
     * @return
     */
    List<Disease> queryAllByFilter(DiseaseDto dto);

    int countAllByFilter(DiseaseDto dto);

    Map queryBaseInfo();
}