package cn.jjdcn.soa.etas.service.impl;

import cn.jjdcn.soa.etas.entity.Picture;
import cn.jjdcn.soa.etas.mapper.fdfs.PictureMapper;
import cn.jjdcn.soa.etas.service.PictureService;
import cn.jjdcn.soa.etas.utils.MyFastDFSClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Picture)表服务实现类
 *
 * @author jjdcn
 * @since 2020-03-07 23:17:07
 */
@Service("pictureService")
public class PictureServiceImpl implements PictureService {
    @Resource
    private PictureMapper pictureMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Picture queryById(Integer id) {
        return this.pictureMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Picture> queryAllByLimit(int offset, int limit) {
        return this.pictureMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param picture 实例对象
     * @return 实例对象
     */
    @Override
    public Picture insert(Picture picture) {
        this.pictureMapper.insert(picture);
        return picture;
    }

    /**
     * 修改数据
     *
     * @param picture 实例对象
     * @return 实例对象
     */
    @Override
    public Picture update(Picture picture) {
        this.pictureMapper.update(picture);
        return this.queryById(picture.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.pictureMapper.deleteById(id) > 0;
    }

    @Override
    public boolean deleteImage(String fileId) {
        return MyFastDFSClient.deleteFile(fileId);
    }

    @Override
    public Picture queryByFileId(String fileId) {
        return pictureMapper.queryByFileId(fileId);
    }

    @Override
    public List<Picture> queryByIds(List ids) {
        if (ids.size() == 0) {
            return new ArrayList<>();
        } else {
            return pictureMapper.queryByIds(ids);
        }
    }
}