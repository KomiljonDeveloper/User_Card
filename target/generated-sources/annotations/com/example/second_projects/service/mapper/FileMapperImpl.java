package com.example.second_projects.service.mapper;

import com.example.second_projects.dto.FileDto;
import com.example.second_projects.modul.FileModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-18T14:20:59+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.7 (JetBrains s.r.o.)"
)
@Component
public class FileMapperImpl extends FileMapper {

    @Override
    public FileDto toDto(FileModel fileModel) {
        if ( fileModel == null ) {
            return null;
        }

        FileDto.FileDtoBuilder fileDto = FileDto.builder();

        fileDto.fileId( fileModel.getFileId() );
        fileDto.fileName( fileModel.getFileName() );
        fileDto.path( fileModel.getPath() );
        fileDto.ext( fileModel.getExt() );
        fileDto.createdAt( fileModel.getCreatedAt() );
        fileDto.updatedAt( fileModel.getUpdatedAt() );
        fileDto.deletedAt( fileModel.getDeletedAt() );

        return fileDto.build();
    }

    @Override
    public void updateToDtoFromEntity(FileDto fileDto, FileModel fileModel) {
        if ( fileDto == null ) {
            return;
        }

        if ( fileDto.getFileName() != null ) {
            fileModel.setFileName( fileDto.getFileName() );
        }
        if ( fileDto.getPath() != null ) {
            fileModel.setPath( fileDto.getPath() );
        }
        if ( fileDto.getExt() != null ) {
            fileModel.setExt( fileDto.getExt() );
        }
    }
}
