package cn.jjdcn.etas.search.service.impl;

import cn.jjdcn.etas.search.pojo.Disease;
import cn.jjdcn.etas.search.pojo.SearchParam;
import cn.jjdcn.etas.search.service.EtasSearchService;
import com.google.gson.Gson;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EtasSearchServiceImpl implements EtasSearchService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    private static final Gson gson = new Gson();

    @Override
    public List<Disease> searchBykeyword(String keyword) {
        List<Disease> diseases = new ArrayList<>();

        SearchParam searchParam = SearchParam.builder().keyword(keyword).build();
        try {
            SearchRequest searchRequest = buildQueryDsl(searchParam);
            SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

            SearchHits searchHits = search.getHits();
            SearchHit[] hits = searchHits.getHits();

            for (SearchHit hit : hits) {
                Disease disease = gson.fromJson(hit.getSourceAsString(), Disease.class);
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                highlightFields.forEach((s, highlightField) -> {
                    System.out.println("s = " + s);
                    System.out.println("highlightFields.get(s) = " + highlightFields.get(s).getFragments()[0]);
                    disease.set(s, highlightFields.get(s).getFragments()[0].toString());
                });
                diseases.add(disease);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diseases;
    }


    private SearchRequest buildQueryDsl(SearchParam searchParam) {
        String keyword = searchParam.getKeyword();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder
                .should(QueryBuilders.matchQuery("name", keyword))
                .should(QueryBuilders.matchQuery("symptomsDesc", keyword))
                .should(QueryBuilders.matchQuery("pathogenDesc", keyword))
                .should(QueryBuilders.matchQuery("prevention", keyword))
                .should(QueryBuilders.matchQuery("classFamily", keyword))
                .should(QueryBuilders.matchQuery("classOrder", keyword))
                .should(QueryBuilders.matchQuery("classClass", keyword))
                .should(QueryBuilders.matchQuery("classGenus", keyword))
                .should(QueryBuilders.matchQuery("classSpecies", keyword))
                .should(QueryBuilders.matchQuery("virusType", keyword))
                .should(QueryBuilders.matchQuery("pathogenType", keyword));

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        searchSourceBuilder.highlighter(new HighlightBuilder()
                .field("name").preTags("<em>").postTags("</em>")
                .field("symptomsDesc").preTags("<em>").postTags("</em>")
                .field("pathogenDesc").preTags("<em>").postTags("</em>")
                .field("prevention").preTags("<em>").postTags("</em>")
                .field("classFamily").preTags("<em>").postTags("</em>")
                .field("classOrder").preTags("<em>").postTags("</em>")
                .field("classClass").preTags("<em>").postTags("</em>")
                .field("classGenus").preTags("<em>").postTags("</em>")
                .field("classSpecies").preTags("<em>").postTags("</em>")
                .field("virusType").preTags("<em>").postTags("</em>")
                .field("pathogenType").preTags("<em>").postTags("</em>")
                .field("").preTags("<em>").postTags("</em>"));
        searchSourceBuilder.query(boolQueryBuilder);

        SearchRequest searchRequest = new SearchRequest("manage");
        searchRequest.types("disease");
        searchRequest.source(searchSourceBuilder);
        return searchRequest;
    }
}
