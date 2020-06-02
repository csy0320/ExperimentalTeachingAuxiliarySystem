package cn.jjdcn.soa.etas.service.impl;

import cn.jjdcn.soa.etas.pojo.DiseaseIndex;
import cn.jjdcn.soa.etas.repository.DiseaseRepository;
import cn.jjdcn.soa.etas.service.DiseaseIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("diseaseIndexService")
public class DiseaseIndexServiceImpl implements DiseaseIndexService {

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Override
    public DiseaseIndex update(DiseaseIndex diseaseIndex) {
        return diseaseRepository.save(diseaseIndex);
    }

    @Override
    public void delete(Integer id) {
        diseaseRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        diseaseRepository.deleteAll();
    }

    @Override
    public void updateAll(List<DiseaseIndex> diseaseIndexList) {
         diseaseRepository.saveAll(diseaseIndexList);
    }
}
