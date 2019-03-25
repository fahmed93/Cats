package com.topbloc.app;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ExcelReader {
    //public static String filePath;

    public static Workbook getWorkbook(String filepath) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(new File(filepath));
        return workbook;
    }

    public static void readStudentInfo(Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        for (Row row: sheet) {
            String studentId = "";
            String major = "";
            String gender = "";
            if (row.getRowNum() == 0) {
            } else {
                for (Cell cell : row) {
                    if (cell.getColumnIndex() == 0) {
                        studentId = dataFormatter.formatCellValue(cell);
                    } else if (cell.getColumnIndex() == 1) {
                        major = dataFormatter.formatCellValue(cell);
                    } else if (cell.getColumnIndex() == 2) {
                        gender = dataFormatter.formatCellValue(cell);
                    }
                }
                Student student = new Student(studentId, major, gender);
                myClass.map.put(studentId, student);
                if (major.equals("computer science") && gender.equals("F")){
                    myClass.csFemales.add(studentId);
                }
                myClass.count++;
                //System.out.println(student.toString());
            }
        }
    }

    public static void readTestScores(Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        for (Row row: sheet) {
            String studentId = "";
            int testScore = 0;
            if (row.getRowNum() == 0) {
            } else {
                for (Cell cell : row) {
                    if (cell.getColumnIndex() == 0) {
                        studentId = dataFormatter.formatCellValue(cell);
                    } else if (cell.getColumnIndex() == 1) {
                        testScore = (int) cell.getNumericCellValue();
                    }
                }
                Student student = myClass.map.get(studentId);
                student.setTestScore(testScore);
                //System.out.println(student.toString());
            }
        }
    }

    public static void readTestRetakeScores(Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        for (Row row: sheet) {
            String studentId = "";
            int testRetakeScore = 0;
            if (row.getRowNum() == 0) {
            } else {
                for (Cell cell : row) {
                    if (cell.getColumnIndex() == 0) {
                        studentId = dataFormatter.formatCellValue(cell);
                    } else if (cell.getColumnIndex() == 1) {
                        testRetakeScore = (int) cell.getNumericCellValue();
                    }
                }
                Student student = myClass.map.get(studentId);
                student.setTestRetakeScore(testRetakeScore);
                //System.out.println(student.toString());
            }
        }
    }

}
