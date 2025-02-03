package com.kafein.product.userservice.service;

import com.kafein.product.userservice.dto.UserCreatePayloadDto;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvReadService  {

    public List<UserCreatePayloadDto> getUsers(MultipartFile file)
    {
        List<UserCreatePayloadDto> users = new ArrayList<>();

        try(Reader reader = new InputStreamReader(file.getInputStream());
            CSVReader csvReader = new CSVReaderBuilder(reader).build();) {

        // Parse CSV data

            csvReader.skip(1);
        List<String[]> rows = csvReader.readAll();
            for (String[] row : rows) {
            int columnIdx = 0;
            String name = row[columnIdx++];
            String surname = row[columnIdx++];
            String password = row[columnIdx++];
            String email = row[columnIdx++];
            users.add(new UserCreatePayloadDto(name, surname, email, password));

            }

        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }


        return users;

    }
}
