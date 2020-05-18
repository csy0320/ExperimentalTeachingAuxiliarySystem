package cn.jjdcn.etas.search.service.impl;

import cn.jjdcn.etas.search.pojo.Disease;
import cn.jjdcn.etas.search.repository.DiseaseRepository;
import cn.jjdcn.etas.search.service.DiseaseIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiseaseIndexServiceImpl implements DiseaseIndexService {

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Override
    public Disease update(Disease disease) {
        return diseaseRepository.save(disease);
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
    public void updateAll(List<Disease> diseases) {
         diseaseRepository.saveAll(diseases);
    }
}
