package cn.jjdcn.etas.manage.feign;

import cn.jjdcn.etas.search.api.DiseaseIndexApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("service-search")
public interface DiseaseIndexClient extends DiseaseIndexApi {
}
