package cn.jjdcn.etas.manage.controller;

import cn.jjdcn.etas.common.bean.Result;
import cn.jjdcn.etas.manage.auth.annotation.CheckAuth;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("manage")
public class ImportController {

    @PostMapping("upload")
    @CheckAuth
    public Result doUploadForImport(@RequestBody MultipartFile multipartFile){
        return Result.ok();
    }
}
