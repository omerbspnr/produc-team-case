package com.kafein.product.userservice.service;

import com.kafein.product.userservice.dto.UserCreatePayloadDto;
import com.kafein.product.userservice.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserCreateFileProcessService {
    private final CsvReadService csvReadService;
    private final ExcelReadService excelReadService;
    private final UserCreatedKafkaProduceService userCreatedKafkaProduceService;


    public UserCreateFileProcessService(CsvReadService csvReadService, ExcelReadService excelReadService, UserCreatedKafkaProduceService userCreatedKafkaProduceService)
    {
        this.csvReadService = csvReadService;
        this.excelReadService = excelReadService;
        this.userCreatedKafkaProduceService = userCreatedKafkaProduceService;
    }

    public void processExcelFile(MultipartFile file) {

        if (!FileUtil.isValidFileExtension(file)) {
            throw new RuntimeException("Invalid file format");
        }
        List<UserCreatePayloadDto> userCreatePayloadDtoList = excelReadService.getUsers(file);

        userCreatePayloadDtoList.forEach(userCreatedKafkaProduceService::sendMessage);


    }

    public void processCsvFile(MultipartFile file)
    {
        if (!FileUtil.isValidFileExtension(file)) {
            throw new RuntimeException("Invalid file format");
        }
        List<UserCreatePayloadDto> userCreatePayloadDtoList = csvReadService.getUsers(file);

        userCreatePayloadDtoList.forEach(userCreatedKafkaProduceService::sendMessage);

    }

}
