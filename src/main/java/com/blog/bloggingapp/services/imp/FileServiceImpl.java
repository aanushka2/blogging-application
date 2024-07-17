package com.blog.bloggingapp.services.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.bloggingapp.services.FileSevice;


@Service
public class FileServiceImpl implements FileSevice {
	@Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //path=path till folder images
        //file=data
        //extract data from file and store it at given path



        //extract filename
        String name=file.getOriginalFilename();

        //if diff file with same name-use randomID for each file
        String randomID= UUID.randomUUID().toString();
        String fileName1=randomID.concat(name.substring(name.lastIndexOf(".")));



        //fullpath--path till file
        String filePath=path+ File.separator+fileName1;

        //create folder if not created
        File f=new File (path);
        if(!f.exists())
        {
            f.mkdir();
        }

        //copy file
        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileName1;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {

        String fillPath=path+File.separator+fileName;
        InputStream is=new FileInputStream(fillPath);

        return is;
    }

}
