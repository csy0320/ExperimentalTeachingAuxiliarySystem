package cn.jjdcn.etas.manage.dao;

import cn.jjdcn.etas.manage.entity.Classify;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 分类表(Classify)表数据库访问层
 *
 * @author jjdcn
 * @since 2020-03-09 14:14:25
 */
public interface ClassifyDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Classify queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Classify> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param classify 实例对象
     * @return 对象列表
     */
    List<Classify> queryAll(Classify classify);

    /**
     * 新增数据
     *
     * @param classify 实例对象
     * @return 影响行数
     */
    int insert(Classify classify);

    /**
     * 修改数据
     *
     * @param classify 实例对象
     * @return 影响行数
     */
    int update(Classify classify);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}