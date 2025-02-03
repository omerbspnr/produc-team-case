package com.kafein.product.userservice.util;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public final class FileUtil {


    private FileUtil()
    {}

    public static boolean isValidFileExtension(MultipartFile multipartFile)
    {
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());

        if (StringUtils.isEmpty(extension)) {
            return false;
        }

        return extension.equals("csv") || extension.equals("xls") || extension.equals("xlsx");
    }
}
