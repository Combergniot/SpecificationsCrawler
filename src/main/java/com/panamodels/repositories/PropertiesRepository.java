package com.panamodels.repositories;

import com.panamodels.model.Properties;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertiesRepository  extends CrudRepository<Properties, Long> {
}
