package cn.jjdcn.etas.fdfs.controller;

import cn.jjdcn.etas.fdfs.entity.Picture;
import cn.jjdcn.etas.fdfs.service.PictureService;
import cn.jjdcn.etas.fdfs.utils.MyFastDFSClient;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//import cn.jjdcn.etas.fdfs.utils.FastdfsClient;

@RestController
@RequestMapping("fdfs/image")
@Slf4j
public class ImageManageController {

    @Autowired
    private PictureService pictureService;

    @PostMapping("upload")
    public ResponseEntity<Map> uploadImage(@RequestParam("file") MultipartFile mf) throws IOException {
        Map<String, String> meta = Maps.newHashMap();
        String fileid = MyFastDFSClient.uploadFile(mf);
        String url = MyFastDFSClient.getResAccessUrl(fileid);
        Picture picture = Picture.builder().originName(mf.getOriginalFilename()).caption(mf.getOriginalFilename().substring(0,mf.getOriginalFilename().lastIndexOf(".")))
                .fileId(fileid).url(url).status(0).createTime(new Date()).userId(1L).build();
        pictureService.insert(picture);
        Map<String, Object> map = new HashMap<>();
        map.put("url",url);
        map.put("id",pictureService.queryByFileId(fileid).getId());
        map.put("name",pictureService.queryByFileId(fileid).getCaption());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("delete")
    public ResponseEntity<String> uploadImage(@RequestParam("fileId") String fileId) throws IOException {
        log.info("fileid:{}", fileId);
        boolean deleted = pictureService.deleteImage(fileId);
        String msg;
        if (deleted) {
            msg = "OK";
        } else {
            msg = "ERROR";
            log.warn("FastDFS 删除文件失败 fileID:{}", fileId);
        }
        Picture picture = pictureService.queryByFileId(fileId);
        picture.setStatus(1);
        pictureService.update(picture);

        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
}
