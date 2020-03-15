package cn.jjdcn.etas.fdfs.controller;

import cn.jjdcn.etas.fdfs.entity.Picture;
import cn.jjdcn.etas.fdfs.service.PictureService;
import cn.jjdcn.etas.fdfs.utils.FastdfsClient;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.StorageClient1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("image")
@Slf4j
public class ImageManageController {

    @Value("${fdfsHost}")
    private String fdfsHost;

    @Autowired
    private PictureService pictureService;

    @PostMapping("upload")
    public ResponseEntity<Map> uploadImage(@RequestParam("file") MultipartFile mf) throws IOException {
        Map<String, String> meta = Maps.newHashMap();
        String fileid = FastdfsClient.uploadFile(mf.getBytes(), Files.getFileExtension(mf.getOriginalFilename()), meta);
        String url = fdfsHost + "/" + fileid;
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
        int i = pictureService.deleteImage(fileId);
        String msg;
        if (i == 0) {
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
