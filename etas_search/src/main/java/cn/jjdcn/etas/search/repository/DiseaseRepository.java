package cn.jjdcn.etas.search.repository;

import cn.jjdcn.etas.search.pojo.Disease;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends ElasticsearchRepository<Disease,Integer> {
}
