package com.seguridad_ciudadana.config;

import com.seguridad_ciudadana.service.comisaria.ComisariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class DataLoader {
/*
    @Autowired
    private ComisariaService comisariaService;

    @EventListener(ApplicationReadyEvent.class)
    public void cargarDatos() {
        try {
            // Load file from resources folder
            File file = new ClassPathResource("Relación de comisarías básicas 1318.xlsx").getFile();

            // Create MultipartFile from File
            MultipartFile multipartFile = new CustomMultipartFile(file);

            // Call the method to process the file
            comisariaService.cargarDatosDesdeCSV(multipartFile);
        } catch (IOException e) {
            e.printStackTrace(); // Log specific IOException
        } catch (Exception e) {
            e.printStackTrace(); // Log other exceptions
        }
    }*/
}
