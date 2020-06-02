package cn.jjdcn.soa.etas.repository;

import cn.jjdcn.soa.etas.pojo.DiseaseIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends ElasticsearchRepository<DiseaseIndex,Integer> {
}
