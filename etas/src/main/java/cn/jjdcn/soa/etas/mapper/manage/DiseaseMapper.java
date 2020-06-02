package cn.jjdcn.soa.etas.mapper.manage;

import cn.jjdcn.soa.etas.entity.Disease;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 植病表(Disease)表数据库访问层
 *
 * @author jjdcn
 * @since 2020-03-09 14:14:29
 */
public interface DiseaseMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Disease queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Disease> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param disease 实例对象
     * @return 对象列表
     */
    List<Disease> queryAll(Disease disease);

    /**
     * 新增数据
     *
     * @param disease 实例对象
     * @return 影响行数
     */
    int insert(Disease disease);

    /**
     * 修改数据
     *
     * @param disease 实例对象
     * @return 影响行数
     */
    int update(Disease disease);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    int countAllByFilter(Disease disease);

    List<String> queryBaseInfo(String conlumName);
}