package com.kafein.product.userservice.controller;

import com.kafein.product.userservice.service.UserCreateFileProcessService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class FileController {

    private final UserCreateFileProcessService m_userCreateFileProcessService;

    public FileController(UserCreateFileProcessService userCreateFileProcessService)
    {
        this.m_userCreateFileProcessService = userCreateFileProcessService;
    }

    @PostMapping("/csv")
    public String parseCSV(@RequestBody MultipartFile file) throws IOException
    {

        m_userCreateFileProcessService.processCsvFile(file);
        return "File Processed";
    }

    @PostMapping("/excel")
    public String parseExcel(@RequestBody MultipartFile file) throws IOException {

       m_userCreateFileProcessService.processExcelFile(file);

        // Loop through sheets


       return "Processed " + file.getOriginalFilename();
    }
}
