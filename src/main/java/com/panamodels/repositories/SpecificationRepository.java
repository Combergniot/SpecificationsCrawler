package com.panamodels.repositories;

import java.util.Optional;

import com.panamodels.model.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationRepository extends CrudRepository<Specification, Long> {

    Optional<Specification> findById(Long id);
}
