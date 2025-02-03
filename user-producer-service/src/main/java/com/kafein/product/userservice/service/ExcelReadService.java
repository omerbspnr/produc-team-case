package com.kafein.product.userservice.service;

import com.kafein.product.userservice.dto.UserCreatePayloadDto;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelReadService {


    private static final Logger logger = LoggerFactory.getLogger(ExcelReadService.class);




    public List<UserCreatePayloadDto> getUsers(MultipartFile file)
    {
        List<UserCreatePayloadDto> users = new ArrayList<>();

        try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {

            XSSFSheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();
            for (int rowNum = 1; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {
                int columnIdx = 0;
                XSSFRow row = sheet.getRow(rowNum);
                String name = formatter.formatCellValue(row.getCell(columnIdx++));
                String surname = formatter.formatCellValue(row.getCell(columnIdx++));
                String password = formatter.formatCellValue(row.getCell(columnIdx++));
                String email = formatter.formatCellValue(row.getCell(columnIdx++));

                users.add(new UserCreatePayloadDto(name, surname, email, password));
            }

        } catch (IOException e) {
            logger.info("File not found");
            throw new RuntimeException(e);
        }

        return users;

    }
}
