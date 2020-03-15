package cn.jjdcn.etas.fdfs.dao;

import cn.jjdcn.etas.fdfs.entity.Picture;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Picture)表数据库访问层
 *
 * @author jjdcn
 * @since 2020-03-07 23:17:06
 */
public interface PictureDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Picture queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Picture> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param picture 实例对象
     * @return 对象列表
     */
    List<Picture> queryAll(Picture picture);

    /**
     * 新增数据
     *
     * @param picture 实例对象
     * @return 影响行数
     */
    int insert(Picture picture);

    /**
     * 修改数据
     *
     * @param picture 实例对象
     * @return 影响行数
     */
    int update(Picture picture);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过fileId删除数据
     *
     * @param fileId 主键
     * @return 影响行数
     */
    Picture queryByFileId(String fileId);

    List<Picture> queryByIds(List ids);
}