package com.example.second_projects.service.mapper;

import com.example.second_projects.dto.UserDto;
import com.example.second_projects.modul.User;
import org.mapstruct.*;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class UserMapper {
    @Mapping(target = "cards", ignore = true)
    @Mapping(target = "gender", source = "gender.title")
    public abstract UserDto toDtoNotCard(User user);

    @Mapping(target = "gender", source = "gender.title")
    @Mapping(target = "cards", expression = "java(cardService.cardDtoSet(user.getUserId()))")
    public abstract UserDto toDtoWithCard(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void toUpdateFromDtoToEntity(@MappingTarget User user, UserDto userDto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    public abstract User toUser(UserDto dto);
}
