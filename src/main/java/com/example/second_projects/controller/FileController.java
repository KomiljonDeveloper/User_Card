package com.example.second_projects.controller;

import com.example.second_projects.dto.FileDto;
import com.example.second_projects.dto.ResponseDto;
import com.example.second_projects.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("file")
public class FileController {

   private final FileService fileService;
   @PostMapping("/upload")
   public ResponseDto<FileDto> upload(@RequestBody MultipartFile file){
         return this.fileService.upload(file);
   }
   @GetMapping("/download/{fileId}")
   public ResponseDto<FileDto> download(@PathVariable Integer fileId){
         return this.fileService.download(fileId);
   }
   @PutMapping("/update/{fileId}")
   public ResponseDto<FileDto> update(@RequestBody MultipartFile file,@PathVariable Integer fileId){
         return this.fileService.update(file,fileId);
   }
   @DeleteMapping("/delete")
   public ResponseDto<FileDto> delete(@RequestParam(name = "fileId") Integer fileId){
         return this.fileService.delete(fileId);
   }


}
