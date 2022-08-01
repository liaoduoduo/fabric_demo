package com.ldy.utils;

import com.ldy.entity.DecideInfo;
import com.ldy.entity.DecideInfoCategory;
import com.ldy.entity.TaskCategory;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {

    public static XSSFSheet readExcel(File file) {
        InputStream in;
        // 读取整个Excel
        XSSFWorkbook sheets;
        // 获取第一个表单Sheet
        XSSFSheet sheetAt = null;
        try {
            in = Files.newInputStream(file.toPath());
            sheets = new XSSFWorkbook(in);
            sheetAt = sheets.getSheetAt(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //默认第一行为标题行，i = 0
        // XSSFRow titleRow = sheetAt.getRow(0);
        return sheetAt;
    }

    public static List<TaskCategory> readTaskCategoryFromExcel(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("文件不存在!");
        }
        XSSFSheet sheetAt = readExcel(file);
        List<TaskCategory> list = new ArrayList<>();
        // 循环获取每一行数据
        for (int i = 1; i < sheetAt.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheetAt.getRow(i);
            TaskCategory taskCategory = new TaskCategory();
            for (int index = 0; index < row.getPhysicalNumberOfCells(); index++) {
                XSSFCell cell = row.getCell(index);
                cell.setCellType(CellType.STRING);
                if (cell.getStringCellValue().equals("")) {
                    continue;
                }
                if (index == 0) {
                    taskCategory.setId(Long.parseLong(String.valueOf(cell)));
                }
                if (index == 1) {
                    taskCategory.setName(String.valueOf(cell));
                }
                taskCategory.setDeleted(0);
            }
            list.add(taskCategory);
        }
        list.forEach(System.out::println);
        return list;
    }

    public static List<DecideInfo> readDecideInfoFromExcel(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("文件不存在!");
        }
        XSSFSheet sheetAt = readExcel(file);
        List<DecideInfo> list = new ArrayList<>();
        // 循环获取每一行数据
        for (int i = 1; i < sheetAt.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheetAt.getRow(i);
            DecideInfo decideInfo = new DecideInfo();
            for (int index = 0; index < row.getPhysicalNumberOfCells(); index++) {
                XSSFCell cell = row.getCell(index);
                cell.setCellType(CellType.STRING);
                if (cell.getStringCellValue().equals("")) {
                    continue;
                }
                if (index == 0) {
                    decideInfo.setId(Long.parseLong(String.valueOf(cell)));
                }
                if (index == 1) {
                    decideInfo.setName(String.valueOf(cell));
                }
                if (index == 2) {
                    decideInfo.setNameZh(String.valueOf(cell));
                }
                if (index == 3) {
                    decideInfo.setDecideInfoCategoryId(Long.parseLong(String.valueOf(cell)));
                }
                if (index == 4) {
                    decideInfo.setWeight(Double.parseDouble(String.valueOf(cell)));
                }
                decideInfo.setDeleted(0);
            }
            list.add(decideInfo);
        }
        list.forEach(System.out::println);
        return list;
    }

    public static List<DecideInfoCategory> readDecideInfoCategoryFromExcel(String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("文件不存在!");
        }
        XSSFSheet sheetAt = readExcel(file);
        List<DecideInfoCategory> list = new ArrayList<>();
        // 循环获取每一行数据
        for (int i = 1; i < sheetAt.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheetAt.getRow(i);
            DecideInfoCategory decideInfoCategory = new DecideInfoCategory();
            for (int index = 0; index < row.getPhysicalNumberOfCells(); index++) {
                XSSFCell cell = row.getCell(index);
                cell.setCellType(CellType.STRING);
                if (cell.getStringCellValue().equals("")) {
                    continue;
                }
                if (index == 0) {
                    decideInfoCategory.setId(Long.parseLong(String.valueOf(cell)));
                    // taskCategory.setId(Long.parseLong(String.valueOf(cell)));
                }
                if (index == 1) {
                    decideInfoCategory.setName(String.valueOf(cell));
                    // taskCategory.setName(String.valueOf(cell));
                }
                if (index == 2) {
                    decideInfoCategory.setTaskCategoryId(Long.parseLong(String.valueOf(cell)));
                }
                decideInfoCategory.setDeleted(0);
            }
            list.add(decideInfoCategory);
        }
        list.forEach(System.out::println);
        return list;
    }
}
