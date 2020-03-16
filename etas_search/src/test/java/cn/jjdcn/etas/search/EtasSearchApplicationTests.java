package cn.jjdcn.etas.search;

import cn.jjdcn.etas.search.repository.DiseaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

@SpringBootTest
@Slf4j
class EtasSearchApplicationTests {

    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Test
    void contextLoads() {
//        this.restTemplate.createIndex(Disease.class);
//        this.restTemplate.putMapping(Disease.class);
//        Disease disease = Disease.builder().id(1).build();
//        diseaseRepository.save(disease);


    }

}
