package cn.jjdcn.etas.auth.feign;

import cn.jjdcn.etas.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("service-user")
public interface UserClient extends UserApi {
}
