package com.company.service;

import com.company.domain.user.User;
import com.company.domain.user.UserRole;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Сервис отвечающий за выгрузку отчетов
 */
@Service
public class UploadPasswordsService {

    @Autowired
    private UserService userService;

    public void uploadPasswords(HttpServletResponse response, String userRole){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("пароли");
        Cell cell;
        Row row;
        row = sheet.createRow(0);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("ФИО");
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Пароль");

        List<User> users = userService.findUserOnRole(UserRole.valueOf(userRole));

        for (int i = 0; i < users.size(); i++) {
            row = sheet.createRow(i+1);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(userService.getFullName(users.get(i)));
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(users.get(i).getPassword());
        }

        try {
            File file = new File("src/main/resources/reportExcel/Пароли тестирования.xlsx");
            workbook.write(file);
            Path path = file.toPath();
            response.setHeader("Content-disposition", "attachment;filename=" + "Пароли тестирования.xlsx");
            response.setContentType("application/vnd.ms-excel");
            Files.copy(path, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
