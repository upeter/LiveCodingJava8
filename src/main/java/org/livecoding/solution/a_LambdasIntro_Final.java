package org.livecoding.solution;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;


public class a_LambdasIntro_Final {

    public static void main(String[] args) {
        imperativeWithDuplication();
        functionalWithoutDuplication();
        functionalWithLambdas();
    }


    private static void functionalWithLambdas() {
        //Anonymous inner functions
        File f = new File("/tmp");
        File[] res = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        //print(res);
        //without inference
        f.listFiles((File fi) -> fi.isDirectory());
        //with interference
        f.listFiles(fi -> fi.isDirectory());
        //multiline function
        f.listFiles(fi -> {
            System.out.println(fi.getName());
            return fi.isDirectory();
        });

        //Method reference show ! not working
        f.listFiles(File::isDirectory);

        //assign to interface
        FileFilter ff = (File fi) -> fi.isDirectory();
        ff = File::isDirectory;
        Predicate<File> pff = File::isDirectory;
        //show that interface must match
        f.listFiles(ff);
    }


    private static void functionalWithoutDuplication() {
        File file = new File("/tmp");
        //There is an more elegant solution:
        List<File> filtered3 = Arrays.asList(file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        }));


        List<File> filtered4 = Arrays.asList(file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return !pathname.isDirectory();
            }
        }));
    }

    private static void imperativeWithDuplication() {
        File file = new File("/tmp");
        List<File> filtered = new ArrayList<>();
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                filtered.add(f);
            }
        }
        System.out.println(filtered);

        //almost the same...
        List<File> filtered2 = new ArrayList<>();
        for (File f : file.listFiles()) {
            if (!f.isDirectory()) {
                filtered2.add(f);
            }
        }
    }

}
