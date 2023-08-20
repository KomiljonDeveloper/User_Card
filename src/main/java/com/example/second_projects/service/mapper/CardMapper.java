package com.example.second_projects.service.mapper;


import com.example.second_projects.dto.CardDto;
import com.example.second_projects.modul.Card;
import com.example.second_projects.repository.UserRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CardMapper {
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected UserMapper userMapper;


    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    @Mapping(target = "users",ignore = true)
    @Mapping( target = "cardId",ignore = true)
    public abstract Card toEntity(CardDto dto);
    @Mapping(target ="cardNumber",source = "card.cardNumber")
    @Mapping(target = "users",expression = "java(userMapper.toDtoNotCard(userRepository.findByUserIdAndDeletedAtIsNull(card.getUserId()).get()))")
    public abstract CardDto toDtoWithUser(Card card);
    @Mapping(target = "users",ignore = true)
    public abstract CardDto toDtoNotUser(Card card);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract void toUpdateFromDtoToEntity(@MappingTarget Card card, CardDto cardDto);


}
