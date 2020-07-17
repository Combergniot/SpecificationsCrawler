package com.panamodels.controllers;

import java.io.IOException;
import java.util.List;

import com.panamodels.crawlers.Scrapper;
import com.panamodels.model.Specification;
import com.panamodels.repositories.SpecificationRepository;
import com.panamodels.services.CsvDataImporter;
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
public class SpecificationResourceController {

    private final CsvDataImporter csvDataImporter;
    private final SpecificationRepository specificationRepository;
    private final Scrapper scrapper;

    @Autowired
    public SpecificationResourceController(CsvDataImporter csvDataImporter,
                                           SpecificationRepository specificationRepository, Scrapper scrapper) {
        this.csvDataImporter = csvDataImporter;
        this.specificationRepository = specificationRepository;
        this.scrapper = scrapper;
    }

//    TODO
    @RequestMapping(
            value = "/importData",
            method = RequestMethod.GET,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public void showSpecifications(@RequestParam("file") MultipartFile file) throws IOException {
        csvDataImporter.captureDataFromCSV(file);
    }

//    TODO
    @RequestMapping(
            value = "/show",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<String> show() throws IOException {
        return scrapper.showOneSpecification("https://www.panasonic.com/pl/consumer/kamery-i-aparaty/kamery/kamery-4k/hc-vx870ep-k.specs.html");
    }

}
