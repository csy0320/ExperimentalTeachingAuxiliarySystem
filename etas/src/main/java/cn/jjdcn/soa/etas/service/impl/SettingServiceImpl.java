package cn.jjdcn.soa.etas.service.impl;

import cn.jjdcn.soa.etas.entity.Setting;
import cn.jjdcn.soa.etas.mapper.manage.SettingMapper;
import cn.jjdcn.soa.etas.service.SettingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Setting)表服务实现类
 *
 * @author jjdcn
 * @since 2020-03-13 21:52:45
 */
@Service("settingService")
public class SettingServiceImpl implements SettingService {
    @Resource
    private SettingMapper settingMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Setting queryById(Integer id) {
        return this.settingMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Setting> queryAllByLimit(int offset, int limit) {
        return this.settingMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param setting 实例对象
     * @return 实例对象
     */
    @Override
    public Setting insert(Setting setting) {
        this.settingMapper.insert(setting);
        return setting;
    }

    /**
     * 修改数据
     *
     * @param setting 实例对象
     * @return 实例对象
     */
    @Override
    public Setting update(Setting setting) {
        this.settingMapper.update(setting);
        return this.queryById(setting.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.settingMapper.deleteById(id) > 0;
    }

    @Override
    public Setting queryByUserId(int userId) {
        return settingMapper.queryByUserId(userId);
    }

    @Override
    public Setting updateByUserId(Setting setting) {
        settingMapper.updateByUserId(setting);
        return settingMapper.queryByUserId(setting.getUserId());
    }
}