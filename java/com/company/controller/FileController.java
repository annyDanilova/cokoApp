package com.company.controller;

import com.company.domain.test.TestForUser;
import com.company.domain.user.User;
import com.company.domain.user.UserRole;
import com.company.service.DeserializeService;
import com.company.service.UploadPasswordsService;
import com.company.service.UploadRequestsService;
import com.company.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Контроллер отвечающий за файлы
 */
@Controller
public class FileController {

    @Autowired
    private DeserializeService deserializeService;

    @Autowired
    private UploadPasswordsService uploadPasswordsService;

    @Autowired
    private UploadRequestsService uploadRequestsService;

    @Autowired
    private UserService userService;

    @PostMapping("/loadFile")
    public String loadFile(@RequestParam("file") MultipartFile file) {
        XSSFWorkbook workbook = null;
        try {
            InputStream inputStream = new BufferedInputStream(file.getInputStream());
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // Get iterator to all cells of current row
            Iterator<Cell> cellIterator = row.cellIterator();

            User user = new User();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                user.setSurname(cell.getStringCellValue());
                cell = cellIterator.next();
                user.setFirstname(cell.getStringCellValue());
                cell = cellIterator.next();
                user.setSecondName(cell.getStringCellValue());
                cell = cellIterator.next();
                user.setUserRole(UserRole.valueOf(cell.getStringCellValue()));
                cell = cellIterator.next();
                user.setSchoolCode((int) cell.getNumericCellValue());
            }
            userService.create(user);
        }
            return "redirect:/admin";
    }

    @GetMapping("/uploadPasswords")
    public void uploadPasswords(@RequestParam String userRole,
                                HttpServletResponse response){
        uploadPasswordsService.uploadPasswords(response, userRole);
    }


    @GetMapping("/uploadRequests")
    public void uploadRequests(HttpServletResponse response){
        uploadRequestsService.uploadRequests(response);
    }

}
