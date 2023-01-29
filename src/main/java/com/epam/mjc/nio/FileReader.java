package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class FileReader {
    private String name;
    private Integer age;
    private String email;
    private Long phone;

    public Profile getDataFromFile(File file) {

        Path path;
        try {
            path = Paths.get(file.getAbsolutePath());

            Stream<String> lines = Files.lines(path);
    
            String data = lines.collect(Collectors.joining("\n"));

            lines.close();

            String[] parts = data.split("\n");

            for(String s : parts){
				if(s.contains("Name:")) name = s.split(": ")[1];
				if(s.contains("Age:")) age = Integer.parseInt(s.split(": ")[1]);
				if(s.contains("Email:")) email = s.split(": ")[1];
				if(s.contains("Phone:")) phone = Long.parseLong(s.split(": ")[1]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    
        return new Profile(name, age, email, phone);
    }
}
