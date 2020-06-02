package cn.jjdcn.soa.etas.service;


import cn.jjdcn.soa.etas.pojo.DiseaseIndex;

import java.util.List;

public interface EtasSearchService {
    List<DiseaseIndex> searchBykeyword(String keyword);
}
