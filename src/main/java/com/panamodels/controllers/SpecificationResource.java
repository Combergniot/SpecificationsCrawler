package com.panamodels.controllers;

import java.io.IOException;
import java.util.Optional;

import com.panamodels.crawlers.Scrapper;
import com.panamodels.model.Specification;
import com.panamodels.services.CsvDataImporter;
import com.panamodels.services.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("specs")
public class SpecificationResource {

    private final CsvDataImporter csvDataImporter;
    private final SpecificationService specificationService;
    private final Scrapper scrapper;

    @Autowired
    public SpecificationResource(CsvDataImporter csvDataImporter,
                                 SpecificationService specificationService, Scrapper scrapper) {
        this.csvDataImporter = csvDataImporter;
        this.specificationService = specificationService;
        this.scrapper = scrapper;
    }

    @RequestMapping(
            value = "/importData",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public void importDataFromCSVFile(@RequestParam("file") MultipartFile file) throws IOException {
         csvDataImporter.captureDataFromCSV(file);
    }

    @RequestMapping(
            value = "/updateProperties",
            method = RequestMethod.POST
    )
    public void collectProperties() throws IOException {
        scrapper.collectSpecifications();
    }

    @RequestMapping(
            value = "/showAll",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Iterable<Specification> showAll() {
       return specificationService.list();
    }

    @RequestMapping(
            value = "/show/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Optional<Specification> findOne(@PathVariable String id) {
        return specificationService.findById(Long.valueOf(id));
    }
}


