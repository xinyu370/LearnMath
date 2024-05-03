package org.example.netty.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class TestFilesWalkFileTree {
    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Paths.get("/home/xhl/桌面/learn_math/src/main/java/org/example"),
                new SimpleFileVisitor<Path>(){
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        System.out.println("===>"+dir.getFileName());
                        return super.preVisitDirectory(dir, attrs);
                    }
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        System.out.println("--visit file---"+file.getFileName());
                        return super.visitFile(file, attrs);
                    }
                });
    }
}
