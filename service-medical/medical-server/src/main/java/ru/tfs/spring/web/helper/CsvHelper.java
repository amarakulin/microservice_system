package ru.tfs.spring.web.helper;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class CsvHelper {

    public static <T> List<T> getValuesFromCsv(InputStream inputStream, Class<T> clazz) throws IOException {
        HeaderColumnNameMappingStrategy<T> mappingStrategy = new HeaderColumnNameMappingStrategy<>();
        mappingStrategy.setType(clazz);
        try (Reader reader = new InputStreamReader(inputStream)) {
            CsvToBean<T> csvToBean = new CsvToBeanBuilder(reader)
                    .withMappingStrategy(mappingStrategy)
                    .build();
            return csvToBean.parse();
        }
    }
}
