package cn.jjdcn.etas.manage.feign;

import cn.jjdcn.etas.fdfs.api.PictureApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("service-fdfs")
public interface PictureClient extends PictureApi {
}
