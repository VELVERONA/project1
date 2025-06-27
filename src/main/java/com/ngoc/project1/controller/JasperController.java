package com.ngoc.project1.controller;


import com.ngoc.project1.repository.UsersRepo;
import com.ngoc.project1.service.JasperExportService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class JasperController {


    @Autowired
    private JasperExportService jasperExportService;
    @Autowired
    private UsersRepo repo;

    @GetMapping("/export/pdf")
    public void getUsersPdf(HttpServletResponse response) throws JRException,IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDate + ".pdf";
        response.setHeader(headerKey, headerValue);

        jasperExportService.generateReport(response);
    }


}
