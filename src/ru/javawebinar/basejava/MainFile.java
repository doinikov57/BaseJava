package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;

public class MainFile {

    private static void dirPrint(File dir) throws IOException {
        if (!dir.exists()) {
            System.err.println("dir of file not exists: " + dir.getCanonicalPath());
            throw new RuntimeException("dir of file not exists:" + dir.getCanonicalPath());
        }

        if (dir.isDirectory()) {
            System.out.println("Directory " + dir);
            for (String fileName : dir.list()) {
                File dr = new File(dir + "/" + fileName);
                dirPrint(dr);
            }
        } else
            System.out.println("\t" + dir.getName());
    }

    public static void main(String[] args) {

        try {
            dirPrint(new File("./src"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}