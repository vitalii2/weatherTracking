package org.example;

import java.io.File;

public class seekingFile {
    public static String seek(String s){
        String fileName = "";
        String path = "D:\\java\\IJ SQL\\weatherTrackingSystem";
        File folder = new File(path);
        File [] files = folder.listFiles();
        for (File file:files) {
            if(file.getName().equals(s)){
                fileName += file.getName();
            }
        }
//        System.out.println("Succes");
        return fileName;
    }
}
