package com.issue.iaserver.format.service.impl;

import org.mozilla.universalchardet.UniversalDetector;

public class TestDetector {
    public static void main(String[] args) throws java.io.IOException {
        byte[] buf = new byte[4096];
        String fileName = "src/main/resources/datamini.csv";
        java.io.FileInputStream fis = new java.io.FileInputStream(fileName);


        // (1)
        UniversalDetector detector = new UniversalDetector(null);

// (2)
        int nread;
        while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
            detector.handleData(buf, 0, nread);
        }
// (3)
        detector.dataEnd();

// (4)
        String encoding = detector.getDetectedCharset();
        if (encoding != null) {
            System.out.println("Detected encoding = " + encoding);
        } else {
            System.out.println("No encoding detected.");
        }

// (5)
        detector.reset();


    }
}