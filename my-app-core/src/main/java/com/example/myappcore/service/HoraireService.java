package com.example.myappcore.service;

import com.example.myappcore.dto.BainsLibreDto;
import com.example.myappcore.dto.CoursDto;
import com.example.myappcore.dto.FichierDto;
import com.example.myappcore.dto.HoraireDto;
import com.example.myappcore.model.*;
import com.example.myappcore.repository.FichierRepository;
import com.example.myappcore.repository.HoraireRepository;
import com.example.myappcore.utils.Jour;
import com.example.myappcore.utils.Type;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HoraireService {
    private HoraireRepository horaireRepository;
    private ActivitePiscineService activitePiscineService;
    private FichierRepository fichierRepository;
    private CoursService coursService;

    private BainsLibreService bainsLibreService;

    public HoraireService(HoraireRepository horaireRepository, ActivitePiscineService activitePiscineService, FichierRepository fichierRepository, CoursService coursService, BainsLibreService bainsLibreService) {
        this.horaireRepository = horaireRepository;
        this.activitePiscineService = activitePiscineService;
        this.fichierRepository = fichierRepository;
        this.coursService = coursService;
        this.bainsLibreService = bainsLibreService;
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
            horaire.setNom(horaireDto.getNom());
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
                horaireDto.setNom(horaire.getNom());
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

        Map<Jour, List<HoraireDto>> horairesByDay = new TreeMap<>();
        for (Horaire horaire : horaires) {
            HoraireDto horaireDto = new HoraireDto(horaire);
            horairesByDay.computeIfAbsent(horaireDto.getDay(), k -> new ArrayList<>()).add(horaireDto);
        }

        Workbook workbook = new XSSFWorkbook();
        for (Map.Entry<Jour, List<HoraireDto>> entry : horairesByDay.entrySet()) {
            Jour day = entry.getKey();
            List<HoraireDto> horairesForDay = entry.getValue();
            Sheet sheet = workbook.createSheet(day.toString());

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Activite");
            headerRow.createCell(1).setCellValue("Bassin");
            headerRow.createCell(2).setCellValue("De");
            headerRow.createCell(3).setCellValue("A");
            headerRow.createCell(4).setCellValue("Section");


            int rowNum = 1;
            for (HoraireDto horaire : horairesForDay) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(horaire.getNom());
                row.createCell(1).setCellValue(horaire.getBassin().name());
                row.createCell(2).setCellValue(horaire.getFrom());
                row.createCell(3).setCellValue(horaire.getTo());

                String concatenatedValues = horaire.getLongueur().stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", "));
                row.createCell(4).setCellValue(concatenatedValues);

            }

            for (int i = 0; i < 3; i++) {
                sheet.autoSizeColumn(i);
            }
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        byte[] excelBytes = outputStream.toByteArray();
        outputStream.close();
        workbook.close();

        Fichier fichier = new Fichier();
        fichier.setData(excelBytes);
        fichier.setNom("Horaire.xlsx");

        fichierRepository.save(fichier);

        return new FichierDto(fichier);
    }
}
