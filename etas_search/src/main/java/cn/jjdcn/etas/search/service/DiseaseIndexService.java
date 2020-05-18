package cn.jjdcn.etas.search.service;

import cn.jjdcn.etas.search.pojo.Disease;

import java.util.List;

public interface DiseaseIndexService {

    Disease update(Disease disease);

    void updateAll(List<Disease> diseases);

    void delete(Integer id);

    void deleteAll();
}
