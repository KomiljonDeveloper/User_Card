package com.example.second_projects.service.mapper;

import com.example.second_projects.dto.CardDto;
import com.example.second_projects.dto.UserDto;
import com.example.second_projects.modul.Card;
import com.example.second_projects.modul.Gender;
import com.example.second_projects.modul.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-19T11:31:26+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.7 (JetBrains s.r.o.)"
)
@Component
public class CardMapperImpl extends CardMapper {

    @Override
    public Card toEntity(CardDto dto) {
        if ( dto == null ) {
            return null;
        }

        Card card = new Card();

        card.setCardNumber( dto.getCardNumber() );
        card.setCardName( dto.getCardName() );
        card.setType( dto.getType() );
        card.setAmount( dto.getAmount() );
        card.setUserId( dto.getUserId() );

        return card;
    }

    @Override
    public CardDto toDtoWithUser(Card card) {
        if ( card == null ) {
            return null;
        }

        CardDto.CardDtoBuilder cardDto = CardDto.builder();

        cardDto.cardNumber( card.getCardNumber() );
        cardDto.userId( card.getUserId() );
        cardDto.cardId( card.getCardId() );
        cardDto.cardName( card.getCardName() );
        cardDto.type( card.getType() );
        cardDto.amount( card.getAmount() );
        cardDto.createdAt( card.getCreatedAt() );
        cardDto.deletedAt( card.getDeletedAt() );
        cardDto.updatedAt( card.getUpdatedAt() );

        cardDto.users( userMapper.toDtoNotCard(userRepository.findByUserIdAndDeletedAtIsNull(card.getUserId()).get()) );

        return cardDto.build();
    }

    @Override
    public CardDto toDtoNotUser(Card card) {
        if ( card == null ) {
            return null;
        }

        CardDto.CardDtoBuilder cardDto = CardDto.builder();

        cardDto.userId( card.getUserId() );
        cardDto.cardId( card.getCardId() );
        cardDto.cardNumber( card.getCardNumber() );
        cardDto.cardName( card.getCardName() );
        cardDto.type( card.getType() );
        cardDto.amount( card.getAmount() );
        cardDto.createdAt( card.getCreatedAt() );
        cardDto.deletedAt( card.getDeletedAt() );
        cardDto.updatedAt( card.getUpdatedAt() );

        return cardDto.build();
    }

    @Override
    public void toUpdateFromDtoToEntity(Card card, CardDto cardDto) {
        if ( cardDto == null ) {
            return;
        }

        if ( cardDto.getCardId() != null ) {
            card.setCardId( cardDto.getCardId() );
        }
        if ( cardDto.getCardNumber() != null ) {
            card.setCardNumber( cardDto.getCardNumber() );
        }
        if ( cardDto.getCardName() != null ) {
            card.setCardName( cardDto.getCardName() );
        }
        if ( cardDto.getType() != null ) {
            card.setType( cardDto.getType() );
        }
        if ( cardDto.getAmount() != null ) {
            card.setAmount( cardDto.getAmount() );
        }
        if ( cardDto.getUserId() != null ) {
            card.setUserId( cardDto.getUserId() );
        }
        if ( cardDto.getUsers() != null ) {
            if ( card.getUsers() == null ) {
                card.setUsers( new User() );
            }
            userDtoToUser( cardDto.getUsers(), card.getUsers() );
        }
        if ( cardDto.getCreatedAt() != null ) {
            card.setCreatedAt( cardDto.getCreatedAt() );
        }
        if ( cardDto.getDeletedAt() != null ) {
            card.setDeletedAt( cardDto.getDeletedAt() );
        }
        if ( cardDto.getUpdatedAt() != null ) {
            card.setUpdatedAt( cardDto.getUpdatedAt() );
        }
    }

    protected void userDtoToUser(UserDto userDto, User mappingTarget) {
        if ( userDto == null ) {
            return;
        }

        mappingTarget.setUserId( userDto.getUserId() );
        mappingTarget.setFirstname( userDto.getFirstname() );
        mappingTarget.setLastname( userDto.getLastname() );
        mappingTarget.setEmail( userDto.getEmail() );
        mappingTarget.setPassword( userDto.getPassword() );
        if ( userDto.getGender() != null ) {
            mappingTarget.setGender( Enum.valueOf( Gender.class, userDto.getGender() ) );
        }
        else {
            mappingTarget.setGender( null );
        }
        mappingTarget.setCreatedAt( userDto.getCreatedAt() );
        mappingTarget.setDeletedAt( userDto.getDeletedAt() );
        mappingTarget.setUpdatedAt( userDto.getUpdatedAt() );
    }
}
