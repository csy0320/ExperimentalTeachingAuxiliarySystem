package cn.jjdcn.etas.search;

import cn.jjdcn.etas.search.feign.DiseaseClient;
import cn.jjdcn.etas.search.pojo.Disease;
import cn.jjdcn.etas.search.pojo.SearchParam;
import cn.jjdcn.etas.search.repository.DiseaseRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
@Slf4j
class EtasSearchApplicationTests {


    @Autowired
    private ElasticsearchRestTemplate restTemplate;

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Autowired
    private DiseaseClient diseaseClient;
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    private static final Gson gson = new Gson();
    @Test
    void contextLoads() {
        this.restTemplate.createIndex(Disease.class);
        this.restTemplate.putMapping(Disease.class);
//        Disease disease = Disease.builder().id(1).build();
//        diseaseRepository.save(disease);
//        diseaseRepository.deleteById(1);
    }


    //    将数据导入数据集
    @Test
    void map() {
        List<Disease> collect = diseaseClient.getAllDisease().stream().map(disease -> Disease.builder()
                .id(disease.getId())
                .classClass(disease.getClassClass()).classDomain(disease.getClassDomain())
                .classFamily(disease.getClassFamily())
                .classGenus(disease.getClassGenus())
                .classOrder(disease.getClassOrder())
                .classPhylum(disease.getClassPhylum())
                .classSpecies(disease.getClassSpecies())
                .clickCount(disease.getClickCount())
                .name(disease.getName())
                .pathogenDesc(disease.getPathogenDesc())
                .pathogenType(disease.getPathogenType())
                .prevention(disease.getPrevention())
                .symptomsDesc(disease.getSymptomsDesc())
                .virusType(disease.getVirusType()).build()
        ).collect(Collectors.toList());

//        collect.forEach(System.out::println);
        diseaseRepository.saveAll(collect);
    }

    //    @Test
//    void testSearch(){
//        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.termQuery("name", "测试"))
//                .withHighlightFields(new HighlightBuilder.Field("name")).build();
//
//        AggregatedPage<Disease> results = restTemplate.queryForPage(searchQuery, Disease.class, new SearchResultMapper() {
//            @Override
//            public <T> AggregatedPage<T> mapResults(SearchResponse searchResponse, Class<T> aClass, Pageable pageable) {
//                SearchHits hits = searchResponse.getHits();
//
//
//                return hits;
//            }
//
//            @Override
//            public <T> T mapSearchHit(SearchHit searchHit, Class<T> aClass) {
//                return null;
//            }
//        });
//
////        results.forEach(System.out::println);
//
//    }
    @Test
    void testSearch() throws IOException {

        SearchParam searchParam = SearchParam.builder().keyword("").build();
        SearchRequest searchRequest = buildQueryDsl(searchParam);
        assert searchRequest != null;
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);


        SearchHits searchHits = search.getHits();
        SearchHit[] hits = searchHits.getHits();

        List<Disease> diseases = new ArrayList<>();
        for (SearchHit hit : hits){
            Disease disease = gson.fromJson(hit.getSourceAsString(), Disease.class);
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            highlightFields.forEach((s, highlightField) -> {
                System.out.println("s = " + s);
                System.out.println("highlightFields.get(s) = " + highlightFields.get(s).getFragments()[0]);
                disease.setName(highlightFields.get(s).getFragments()[0].toString());
            });
            diseases.add(disease);
        }

        diseases.forEach(System.out::println);

    }

    private SearchRequest buildQueryDsl(SearchParam searchParam) {
        String keyword = searchParam.getKeyword();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        boolQueryBuilder.must(QueryBuilders.matchQuery("name", keyword));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.highlighter(new HighlightBuilder().field("name").preTags("<em>").postTags("</em>"));
        searchSourceBuilder.query(boolQueryBuilder);

        SearchRequest searchRequest = new SearchRequest("manage");
        searchRequest.types("disease");
        searchRequest.source(searchSourceBuilder);
        return searchRequest;
    }

    @Test
    void test(){
        System.out.println("Disease.builder().build() = " + Disease.builder().build().toString());
}
}
