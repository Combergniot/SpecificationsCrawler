package com.panamodels.crawlers;


import java.io.IOException;

import com.panamodels.model.Properties;
import com.panamodels.model.Specification;
import com.panamodels.repositories.PropertiesRepository;
import com.panamodels.repositories.SpecificationRepository;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Scrapper extends ScrapperConfig {

    private final SpecificationRepository specificationRepository;
    private final PropertiesRepository propertiesRepository;

    @Autowired
    public Scrapper(SpecificationRepository specificationRepository, PropertiesRepository propertiesRepository) {
        this.specificationRepository = specificationRepository;
        this.propertiesRepository = propertiesRepository;
    }

    public void collectSpecifications() throws IOException, IllegalArgumentException {
        Iterable<Specification> specifications = specificationRepository.findAll();

        for (Specification specs : specifications) {
            if (specs.getSpecUrl() != null && specs.getSpecUrl().length() !=0) {
                Document document = connectWith(specs.getSpecUrl());
                Elements elements = document.select("tbody.body-child>tr");
                for (Element element : elements) {
                    Properties properties = new Properties();
                    properties.setSpecification(specs);
                    properties.setName(element.select("th[scope = row]").text());
                    properties.setValue(element.select("td.tb-blk").text());
                    propertiesRepository.save(properties);
                }
            }
        }
    }
}
