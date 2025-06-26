package com.ngoc.project1.controller;

import com.lowagie.text.DocumentException;
import com.ngoc.project1.entity.OurUsers;
import com.ngoc.project1.repository.UsersRepo;
import com.ngoc.project1.service.UserManagermentService;
import com.ngoc.project1.service.UsersPdfService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UserPdfController {
    @Autowired
    private UsersRepo repo;

    @GetMapping("admin/users/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDate = dateFormat.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDate + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<OurUsers> usersList = repo.findAll(Sort.by("email").ascending());

        UsersPdfService exporter = new UsersPdfService(usersList);
        exporter.export(response);

    }
}
