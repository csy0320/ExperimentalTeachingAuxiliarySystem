package cn.jjdcn.etas.search.feign;

import cn.jjdcn.etas.manage.api.DiseaseApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("service-manage")
public interface DiseaseClient extends DiseaseApi {
}
