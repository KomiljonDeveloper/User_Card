package com.example.second_projects.service;

import com.example.second_projects.dto.FileDto;
import com.example.second_projects.dto.ResponseDto;
import com.example.second_projects.service.mapper.FileMapper;
import com.example.second_projects.modul.FileModel;
import com.example.second_projects.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileService {
    @Autowired
    private  FileRepository fileRepository;
    @Autowired
    private  FileMapper fileMapper;

    public ResponseDto<FileDto> upload(MultipartFile file) {
        try {

            String[] split = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
            return ResponseDto.<FileDto>builder()
                    .success(true)
                    .massage("OK")
                    .date(this.fileMapper.toDto(
                            this.fileRepository.save(FileModel.builder()
                                    .fileName(split[split.length - 2])
                                    .ext(split[split.length - 1])
                                    .path(saveFile(file))
                                    .createdAt(LocalDateTime.now())
                                    .build())))
                    .build();
        } catch (Exception e) {
            return ResponseDto.<FileDto>builder()
                    .code(-3)
                    .massage("File saving error massage :" + e.getMessage())
                    .build();
        }
    }

    public ResponseDto<FileDto> download(Integer fileId) {


        return this.fileRepository.findByFileIdAndDeletedAtIsNull(fileId).map(fileModel -> {
                FileDto dto = this.fileMapper.toDto(fileModel);
            try {
                dto.setData(Files.readAllBytes(Path.of(fileModel.getPath())));
            } catch (IOException e) {
                return ResponseDto.<FileDto>builder()
                        .massage("While database error massage :"+e.getMessage())
                        .code(-3)
                        .build();
            }
            return ResponseDto.<FileDto>builder()
                        .massage("Ok")
                        .success(true)
                        .date(dto)
                        .build();

        }).orElse(  ResponseDto.<FileDto>builder()
                .code(-1)
                .massage("File not found!")
                .build()
);


    }

    public ResponseDto<FileDto> update(MultipartFile file, Integer fileId) {

           return this.fileRepository.findByFileIdAndDeletedAtIsNull(fileId).map(fileModel -> {
                String[] split = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
                FileDto dto = this.fileMapper.toDto(
                        FileModel.builder()
                                .fileName(split[split.length - 2])
                                .ext(split[split.length - 1])
                                .path(saveFile(file))
                                .updatedAt(LocalDateTime.now())
                                .build());
                this.fileMapper.updateToDtoFromEntity(dto, fileModel);
                this.fileRepository.save(fileModel);
                FileDto dto1 = this.fileMapper.toDto(fileModel);
               try {
                   dto1.setData(Files.readAllBytes(Path.of(fileModel.getPath())));
               } catch (IOException ex) {
                   return ResponseDto.<FileDto>builder()
                           .massage("While database saving error massage : "+ex.getMessage())
                           .code(-3)
                           .build();
               }
               return ResponseDto.<FileDto>builder()
                        .success(true)
                        .massage("OK")
                        .date(dto1)
                        .build();

            }).orElse(ResponseDto.<FileDto>builder()
                   .code(-1)
                   .massage("File not found!")
                   .build());
    }

    public ResponseDto<FileDto> delete(Integer fileId) {

       return this.fileRepository.findByFileIdAndDeletedAtIsNull(fileId).map(fileModel -> {

            File file = new File(fileModel.getPath());
            if (file.exists()){
                file.delete();
                fileModel.setDeletedAt(LocalDateTime.now());
                this.fileRepository.save(fileModel);
                return ResponseDto.<FileDto>builder()
                        .massage("On file deleted!")
                        .success(true)
                        .date(this.fileMapper.toDto(fileModel))
                        .build();
            }return ResponseDto.<FileDto>builder()
                   .code(-1)
                   .massage("File already deleted!")
                   .build();
        }).orElse(ResponseDto.<FileDto>builder()
                .code(-1)
                .massage("File Not found!")
                .build());

    }

    public String saveFile(MultipartFile file) {

        try {
            String folder = String.format("%s/%s", "upload", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            File sfile = new File(folder);
            if (!sfile.exists()) {
                sfile.mkdirs();
            }
            String fileName = String.format("%s/%s", folder, UUID.randomUUID());

            Files.copy(file.getInputStream(), Path.of(fileName));
            return fileName;
        } catch (Exception e) {
            return "While file saving error :" + e.getMessage();
        }


    }

}
