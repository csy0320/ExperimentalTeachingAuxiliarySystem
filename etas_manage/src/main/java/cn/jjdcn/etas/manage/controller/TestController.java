package cn.jjdcn.etas.manage.controller;

import cn.jjdcn.etas.fdfs.entity.Picture;
import cn.jjdcn.etas.manage.auth.annotation.CheckAuth;
import cn.jjdcn.etas.manage.feign.PictureClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("manage")
public class TestController {

    @Autowired
    private PictureClient pictureClient;

    @GetMapping("disease")
    @CheckAuth()
    public ResponseEntity<String> getAllDisease() {
        return new ResponseEntity<>("访问成功", HttpStatus.OK);
    }

    @GetMapping("test")
    public ResponseEntity<List<Picture>> test() {
        List<Picture> pictures = pictureClient.doQueryPictureByIds("1-2");
        return new ResponseEntity<>(pictures, HttpStatus.OK);
    }

}
