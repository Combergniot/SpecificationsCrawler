package com.panamodels.services;

import java.util.Optional;

import com.panamodels.model.Specification;
import com.panamodels.repositories.SpecificationRepository;
import org.springframework.stereotype.Service;

@Service
public class SpecificationService {

    private final SpecificationRepository specificationRepository;

    public SpecificationService(SpecificationRepository specificationRepository) {
        this.specificationRepository = specificationRepository;
    }

    public Iterable<Specification> list() {
        return specificationRepository.findAll();
    }

    public Specification save(Specification specification) {
        return specificationRepository.save(specification);
    }

    public Optional<Specification> findById(Long id) {
        return specificationRepository.findById(id);
    }



}
