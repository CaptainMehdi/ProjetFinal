package com.example.myappcore.service;

import com.example.myappcore.dto.FichierDto;
import com.example.myappcore.dto.HoraireDto;
import com.example.myappcore.model.Fichier;
import com.example.myappcore.model.Horaire;
import com.example.myappcore.repository.FichierRepository;
import com.example.myappcore.repository.HoraireRepository;
import com.example.myappcore.utils.Jour;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class HoraireService {
    private HoraireRepository horaireRepository;
    private ActivitePiscineService activitePiscineService;
    private FichierRepository fichierRepository;
    public HoraireService(HoraireRepository horaireRepository, ActivitePiscineService activitePiscineService, FichierRepository fichierRepository) {
        this.horaireRepository = horaireRepository;
        this.activitePiscineService = activitePiscineService;
        this.fichierRepository = fichierRepository;
    }

    public HoraireDto save(HoraireDto horaireDto){
        if(horaireDto != null){
            Horaire horaire = new Horaire();
            horaire.setTo(horaireDto.getTo());
            horaire.setFrom(horaireDto.getFrom());
            horaire.setActivitePiscine(activitePiscineService.getActivitiesById(horaireDto.getActivitePiscineId()));
            horaire.setBassin(horaireDto.getBassin());
            horaire.setLongueur(horaireDto.getLongueur());
            horaire.setDay(horaireDto.getDay());
            horaireRepository.save(horaire);
            return horaireDto;
        }
        return null;
    }

    public List<HoraireDto> getAllHoraire(){
        List<Horaire> horaires = horaireRepository.findAll();
        List<HoraireDto> horaireDtos = new ArrayList<>();

        if(!horaires.isEmpty()) {
            for (Horaire horaire : horaires) {
                HoraireDto horaireDto = new HoraireDto();
                horaireDto.setId(horaire.getId());
                horaireDto.setFrom(horaire.getFrom());
                horaireDto.setTo(horaire.getTo());
                horaireDto.setActivitePiscineId(horaire.getActivitePiscine().getId());
                horaireDto.setBassin(horaire.getBassin());
                horaireDto.setLongueur(horaire.getLongueur());
                horaireDto.setDay(horaire.getDay());
                horaireDtos.add(horaireDto);
            }
        }

        return horaireDtos;
    }

    public FichierDto getFileExcelHoraire() throws IOException {
        System.out.println("Creating File Excel...");
        List<Horaire> horaires = horaireRepository.findAll();

        // Group horaires by day of the week
        Map<Jour, List<HoraireDto>> horairesByDay = new TreeMap<>();
        for (Horaire horaire : horaires) {
            HoraireDto horaireDto = new HoraireDto(horaire);
            horairesByDay.computeIfAbsent(horaireDto.getDay(), k -> new ArrayList<>()).add(horaireDto);
        }

        // Create Excel workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        for (Map.Entry<Jour, List<HoraireDto>> entry : horairesByDay.entrySet()) {
            Jour day = entry.getKey();
            List<HoraireDto> horairesForDay = entry.getValue();
            Sheet sheet = workbook.createSheet(day.toString());

            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Moniteur");
            headerRow.createCell(1).setCellValue("De");
            headerRow.createCell(2).setCellValue("A");

            // Add more headers as needed

            // Add horaires data
            int rowNum = 1;
            for (HoraireDto horaire : horairesForDay) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(horaire.getFrom());
                row.createCell(1).setCellValue(horaire.getTo());
                // Add more cell values as needed
            }

            // Autofit columns
            for (int i = 0; i < 3; i++) { // Adjust the range if you add more columns
                sheet.autoSizeColumn(i);
            }
        }

        // Write workbook to ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] excelBytes = outputStream.toByteArray();
        outputStream.close();
        workbook.close();

        // Create Fichier object
        Fichier fichier = new Fichier();
        fichier.setData(excelBytes);
        fichier.setNom("YourExcelFileName.xlsx");

        // Save Fichier object to database
        fichierRepository.save(fichier);

        return new FichierDto(fichier);
    }
}
