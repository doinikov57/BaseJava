package ru.javawebinar.basejava;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;


public class MainFile {

    private static void deepDirPrint(Path path) throws IOException {
        if (!Files.exists(path)) {
            System.err.println("dir of file not exists: " + path.toString());
            throw new RuntimeException("dir of file not exists:" + path.toString());
        }
        StringBuilder offset = tab(path.getNameCount());
        System.out.println(offset + "\\" + path.getFileName().toString());
        Files.list(path).filter(p -> !Files.isDirectory(p)).forEach(p ->
                System.out.println(offset + p.getFileName().toString())
        );
        for (Path p : Files.list(path).filter(p -> Files.isDirectory(p)).collect(Collectors.toList())
        ) {
            deepDirPrint(p);
        }
    }

    static StringBuilder tab(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("  ");
        }
        return sb;
    }

    private static void deepDirPrint2(File dir, String tab) throws IOException {

        if (!dir.exists()) {
            System.err.println("dir of file not exists: " + dir.getCanonicalPath());
            throw new RuntimeException("dir of file not exists:" + dir.getCanonicalPath());
        }

        if (dir.isDirectory()) {
            System.out.println(tab + "\\" + dir.getName());
            tab = tab + "  ";
            for (String fileName : dir.list()) {
                File dr = new File(dir + "/" + fileName);
                deepDirPrint2(dr, tab);
            }
        } else {
            System.out.println(tab + " " + dir.getName());
        }
    }

    public static void main(String[] args) {
        String dir = "./src";
        System.out.println("Fine Deep Pint \n" + Paths.get(dir).toAbsolutePath().normalize());
        try {
            Path path = Paths.get(dir);
            deepDirPrint(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Deep Pint 2 \n" + dir);
        try {
            deepDirPrint2(new File(dir), "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}