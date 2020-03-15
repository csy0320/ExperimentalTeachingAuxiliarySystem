package cn.jjdcn.etas.fdfs.api;

import cn.jjdcn.etas.common.bean.Result;
import cn.jjdcn.etas.fdfs.entity.Picture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


public interface PictureApi {

    @GetMapping("/picture/get/ids")
    List<Picture> doQueryPictureByIds(@RequestParam("ids") String ids);
}
