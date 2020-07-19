package com.panamodels.services;

import com.panamodels.model.Properties;
import com.panamodels.model.Specification;
import com.panamodels.repositories.PropertiesRepository;
import org.springframework.stereotype.Service;

@Service
public class PropertiesService {

    private final PropertiesRepository propertiesRepository;

    public PropertiesService(PropertiesRepository propertiesRepository) {
        this.propertiesRepository = propertiesRepository;
    }

    public Properties save(Properties properties) {
        return propertiesRepository.save(properties);
    }



}
