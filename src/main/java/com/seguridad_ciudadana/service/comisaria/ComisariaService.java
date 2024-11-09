package com.seguridad_ciudadana.service.comisaria;
import com.opencsv.CSVReader;
import com.seguridad_ciudadana.entity.Comisaria;
import com.seguridad_ciudadana.repository.ComisariaRepositor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.Arrays;

@Service
public class ComisariaService {

    @Autowired
    private ComisariaRepositor comisariaRepository;

    public void cargarDatosDesdeCSV(MultipartFile file) throws Exception {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] linea;
            reader.readNext();
            while ((linea = reader.readNext()) != null) {
                System.out.println(Arrays.toString(linea));

                if (linea.length > 9) {
                    if ("AREQUIPA".equalsIgnoreCase(linea[3])) {
                        try {
                            Comisaria comisaria = new Comisaria();
                            try {
                                Float latitud = Float.parseFloat(linea[4].split(",")[0]);
                                Float longitud = Float.parseFloat(linea[4].split(",")[1]);
                                comisaria.setLatitud(latitud);
                                comisaria.setLongitud(longitud);
                            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                                System.out.println("Invalid latitude/longitude in line: " + Arrays.toString(linea));
                                continue;
                            }

                            comisaria.setCodigoInei(Integer.parseInt(linea[0]));
                            comisaria.setCodigoCpnp(Integer.parseInt(linea[1]));
                            comisaria.setDepartamento(linea[2]);
                            comisaria.setProvincia(linea[3]);
                            comisaria.setDistrito(linea[4]);
                            comisaria.setMacregpol(linea[6]);
                            comisaria.setRegpol(linea[7]);
                            comisaria.setDivpol(linea[8]);
                            comisaria.setNombre(linea[9]);

                            comisariaRepository.save(comisaria);
                        } catch (NumberFormatException e) {
                            System.out.println("Error parsing row: " + Arrays.toString(linea));
                        }
                    }
                } else {
                    System.out.println("Skipping line with insufficient columns: " + Arrays.toString(linea));
                }
            }
        }
    }
}
