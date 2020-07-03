package com.panamodels.model;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.Properties;

@Entity
public class Specification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String model;

    private String specUrl;

    private String countryId;

    @Column
    @ElementCollection(targetClass= Properties.class, fetch = FetchType.EAGER)
    private List<Properties> propertiesList;


}
