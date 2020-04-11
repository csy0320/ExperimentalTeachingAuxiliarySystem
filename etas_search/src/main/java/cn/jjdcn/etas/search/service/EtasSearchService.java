package cn.jjdcn.etas.search.service;

import cn.jjdcn.etas.search.pojo.Disease;

import java.util.List;

public interface EtasSearchService {
    List<Disease> searchBykeyword(String keyword);
}
