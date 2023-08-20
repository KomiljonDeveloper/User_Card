package com.example.second_projects.service.mapper;

import com.example.second_projects.dto.FileDto;
import com.example.second_projects.modul.FileModel;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class FileMapper {

    public abstract FileDto toDto(FileModel fileModel);

    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "fileId",ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void updateToDtoFromEntity(FileDto fileDto,@MappingTarget FileModel fileModel);


}
