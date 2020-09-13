package edu.suai.recommendations.repository;

import edu.suai.recommendations.model.Criteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CriteriaRepository extends JpaRepository<Criteria, Long> {
    Optional<Criteria> findByTitle(String title);
}
