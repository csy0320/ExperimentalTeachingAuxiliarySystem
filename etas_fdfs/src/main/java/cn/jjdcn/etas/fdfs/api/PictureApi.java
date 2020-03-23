package cn.jjdcn.etas.fdfs.api;

import cn.jjdcn.etas.fdfs.entity.Picture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface PictureApi {

    @GetMapping("/fdfs/picture/get/ids")
    List<Picture> doQueryPictureByIds(@RequestParam("ids") String ids);
}
