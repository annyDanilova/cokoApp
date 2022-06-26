package com.company.service;

import com.company.domain.Request;
import com.company.repos.RequestRepos;
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

@Service
public class UploadRequestsService {

    @Autowired
    private RequestRepos requestRepos;

    @Autowired
    private UserService userService;

    public void uploadRequests(HttpServletResponse response) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("отчет");
        Cell cell;
        Row row;
        row = sheet.createRow(0);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("ФИО");
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Категория пользователя");
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Название теста");
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Количество вопросов");
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Количество правильных ответов");
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Процент");

        List<Request> requests = requestRepos.findAll();

        for (int i = 0; i < requests.size(); i++) {
            row = sheet.createRow(i + 1);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(userService.getFullName(requests.get(i).getUser()));
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(requests.get(i).getUser().getUserRole().getRole());
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(requests.get(i).getTestForUser().getTitle());
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(requests.get(i).getCountOfAllQuestions());
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(requests.get(i).getCountOfTrueAnswers());
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(requests.get(i).getPercent());
        }

        try {
            File file = new File("src/main/resources/reportExcel/Отчет тестирований.xlsx");
            workbook.write(file);
            Path path = file.toPath();
            response.setHeader("Content-disposition", "attachment;filename=" + "Отчет тестирований.xlsx");
            response.setContentType("application/vnd.ms-excel");
            Files.copy(path, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
