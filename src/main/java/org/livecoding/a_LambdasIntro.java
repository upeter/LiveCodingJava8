package org.livecoding;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class a_LambdasIntro {

    public static void main(String[] args) {
        imperativeWithDuplication();
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


    }


}
