package com.panamodels.services;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import com.panamodels.model.Specification;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CsvDataImporter {

    private final SpecificationService specificationService;

    public CsvDataImporter(SpecificationService specificationService) {
        this.specificationService = specificationService;
    }


    public void captureDataFromCSV(final MultipartFile file) throws IOException {
        if (file.getSize() > 0) {
            Reader in = new InputStreamReader(file.getInputStream());

            char delimiter = getDelimiter(file);
            Iterable<CSVRecord> records =
                    CSVFormat
                            .newFormat(delimiter)
                            .withQuote(null)
                            .withFirstRecordAsHeader()
                            .parse(in);

            for (CSVRecord record : records) {
                Specification specification = new Specification();
                specification.setModel(record.get(4));
                specification.setCountryId(record.get(2));
                specification.setSpecUrl(prepareSpecUrl(record.get(5)));
                specification.setProductId(Long.valueOf(record.get(0)));
                specificationService.save(specification);
            }
            in.close();
        }
    }

    private char getDelimiter(final MultipartFile file) throws IOException {
        CsvParserSettings settings = new CsvParserSettings();
        settings.detectFormatAutomatically();

        CsvParser parser = new CsvParser(settings);
        parser.parseAll(file.getInputStream());

        return parser.getDetectedFormat().getDelimiter();
    }

    private String prepareSpecUrl(String link) {
        String text = link.replace(".html", ".specs.html");
        if (text.contains("$")) {
            return text.substring(0, link.lastIndexOf("$")+6);
        }
        return text;
    }
}
