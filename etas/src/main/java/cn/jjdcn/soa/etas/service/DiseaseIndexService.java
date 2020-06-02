package cn.jjdcn.soa.etas.service;


import cn.jjdcn.soa.etas.pojo.DiseaseIndex;

import java.util.List;

public interface DiseaseIndexService {

    DiseaseIndex update(DiseaseIndex disease);

    void updateAll(List<DiseaseIndex> diseases);

    void delete(Integer id);

    void deleteAll();
}
