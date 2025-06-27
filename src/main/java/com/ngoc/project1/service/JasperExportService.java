package com.ngoc.project1.service;


import com.ngoc.project1.entity.OurUsers;
import com.ngoc.project1.repository.UsersRepo;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JasperExportService {

    @Autowired
    private UsersRepo repo;

    public void generateReport (HttpServletResponse response) throws JRException , IOException {
        List<OurUsers> users = repo.findAll();

        InputStream reportStream = new ClassPathResource("/ReportTemplate/Users3.jrxml").getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(users);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("users", dataSource);
        InputStream imageStream = getClass().getResourceAsStream("/ReportTemplate/invoice_logo.png");
        if (imageStream == null) {
            throw new RuntimeException("could not load image");
        }
        parameters.put("cherry", ImageIO.read(imageStream));

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }

}
