package edu.suai.recommendations.service;

import edu.suai.recommendations.exception.NotFoundException;
import edu.suai.recommendations.model.Criteria;
import edu.suai.recommendations.repository.CriteriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CriteriaService {
    private final CriteriaRepository criteriaRepository;

    @Transactional(readOnly = true)
    public Criteria getCriteriaByTitle (String title) {
        return criteriaRepository.findByTitle(title).orElseThrow(() -> new NotFoundException(Criteria.class));
    }

    @Transactional(readOnly = true)
    public List<Criteria> findAllCriteria() {
        return criteriaRepository.findAll();
    }

    @Transactional
    public boolean createCriteria(Criteria criteria){
        criteriaRepository.save(criteria);
        return true;
    }

//    @Transactional
//    public boolean updateCriteria(Criteria criteria){
//        Criteria object = criteriaRepository.findByTitle(criteria.getTitle()).orElseThrow(() -> new NotFoundException(Criteria.class));
//        object.setDescription(category.getDescription());
//        return true;
//    }

    @Transactional
    public boolean deleteCriteria(String title){
        Criteria object = criteriaRepository.findByTitle(title).orElseThrow(() -> new NotFoundException(Criteria.class));
        criteriaRepository.delete(object);
        return true;
    }
}
