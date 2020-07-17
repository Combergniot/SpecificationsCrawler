package com.panamodels.services;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.panamodels.model.SpecificationDTO;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CsvDataImporter {

    public List<SpecificationDTO> captureDataFromCSV(final MultipartFile file) throws IOException{
        List<SpecificationDTO> specifications = new ArrayList<>();

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
                SpecificationDTO specificationDTO = new SpecificationDTO();
                specificationDTO.setModel(record.get(4));
                specificationDTO.setCountryId(record.get(2));
                specificationDTO.setSpecUrl(record.get(5));
                specificationDTO.setProductId(Long.valueOf(record.get(0)));
            }
            in.close();
        }
    return specifications;
    }

    private char getDelimiter(final MultipartFile file) throws IOException {
        CsvParserSettings settings = new CsvParserSettings();
        settings.detectFormatAutomatically();

        CsvParser parser = new CsvParser(settings);
        parser.parseAll(file.getInputStream());

        return parser.getDetectedFormat().getDelimiter();
    }
}
