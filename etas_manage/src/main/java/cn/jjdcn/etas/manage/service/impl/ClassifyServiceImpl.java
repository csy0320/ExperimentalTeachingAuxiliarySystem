package cn.jjdcn.etas.manage.service.impl;

import cn.jjdcn.etas.manage.entity.Classify;
import cn.jjdcn.etas.manage.dao.ClassifyDao;
import cn.jjdcn.etas.manage.service.ClassifyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类表(Classify)表服务实现类
 *
 * @author jjdcn
 * @since 2020-03-09 14:14:29
 */
@Service("classifyService")
public class ClassifyServiceImpl implements ClassifyService {
    @Resource
    private ClassifyDao classifyDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Classify queryById(Integer id) {
        return this.classifyDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Classify> queryAllByLimit(int offset, int limit) {
        return this.classifyDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param classify 实例对象
     * @return 实例对象
     */
    @Override
    public Classify insert(Classify classify) {
        this.classifyDao.insert(classify);
        return classify;
    }

    /**
     * 修改数据
     *
     * @param classify 实例对象
     * @return 实例对象
     */
    @Override
    public Classify update(Classify classify) {
        this.classifyDao.update(classify);
        return this.queryById(classify.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.classifyDao.deleteById(id) > 0;
    }
}