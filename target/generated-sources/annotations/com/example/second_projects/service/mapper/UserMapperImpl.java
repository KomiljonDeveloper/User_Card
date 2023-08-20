package com.example.second_projects.service.mapper;

import com.example.second_projects.dto.UserDto;
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
public class UserMapperImpl extends UserMapper {

    @Override
    public UserDto toDtoNotCard(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.gender( userGenderTitle( user ) );
        userDto.userId( user.getUserId() );
        userDto.firstname( user.getFirstname() );
        userDto.lastname( user.getLastname() );
        userDto.email( user.getEmail() );
        userDto.password( user.getPassword() );
        userDto.createdAt( user.getCreatedAt() );
        userDto.deletedAt( user.getDeletedAt() );
        userDto.updatedAt( user.getUpdatedAt() );

        return userDto.build();
    }

    @Override
    public UserDto toDtoWithCard(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.gender( userGenderTitle( user ) );
        userDto.userId( user.getUserId() );
        userDto.firstname( user.getFirstname() );
        userDto.lastname( user.getLastname() );
        userDto.email( user.getEmail() );
        userDto.password( user.getPassword() );
        userDto.createdAt( user.getCreatedAt() );
        userDto.deletedAt( user.getDeletedAt() );
        userDto.updatedAt( user.getUpdatedAt() );

        userDto.cards( cardService.cardDtoSet(user.getUserId()) );

        return userDto.build();
    }

    @Override
    public void toUpdateFromDtoToEntity(User user, UserDto userDto) {
        if ( userDto == null ) {
            return;
        }

        if ( userDto.getUserId() != null ) {
            user.setUserId( userDto.getUserId() );
        }
        if ( userDto.getFirstname() != null ) {
            user.setFirstname( userDto.getFirstname() );
        }
        if ( userDto.getLastname() != null ) {
            user.setLastname( userDto.getLastname() );
        }
        if ( userDto.getEmail() != null ) {
            user.setEmail( userDto.getEmail() );
        }
        if ( userDto.getPassword() != null ) {
            user.setPassword( userDto.getPassword() );
        }
        if ( userDto.getGender() != null ) {
            user.setGender( Enum.valueOf( Gender.class, userDto.getGender() ) );
        }
        if ( userDto.getCreatedAt() != null ) {
            user.setCreatedAt( userDto.getCreatedAt() );
        }
        if ( userDto.getDeletedAt() != null ) {
            user.setDeletedAt( userDto.getDeletedAt() );
        }
        if ( userDto.getUpdatedAt() != null ) {
            user.setUpdatedAt( userDto.getUpdatedAt() );
        }
    }

    @Override
    public User toUser(UserDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setUserId( dto.getUserId() );
        user.setFirstname( dto.getFirstname() );
        user.setLastname( dto.getLastname() );
        user.setEmail( dto.getEmail() );
        user.setPassword( dto.getPassword() );
        if ( dto.getGender() != null ) {
            user.setGender( Enum.valueOf( Gender.class, dto.getGender() ) );
        }

        return user;
    }

    private String userGenderTitle(User user) {
        if ( user == null ) {
            return null;
        }
        Gender gender = user.getGender();
        if ( gender == null ) {
            return null;
        }
        String title = gender.getTitle();
        if ( title == null ) {
            return null;
        }
        return title;
    }
}
