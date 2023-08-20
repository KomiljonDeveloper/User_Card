package com.example.second_projects.service;

import com.example.second_projects.dto.ErrorDto;
import com.example.second_projects.dto.ResponseDto;
import com.example.second_projects.dto.SimpleCrud;
import com.example.second_projects.dto.UserDto;
import com.example.second_projects.service.mapper.UserMapper;
import com.example.second_projects.modul.User;
import com.example.second_projects.repository.UserRepository;
import com.example.second_projects.service.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements SimpleCrud<Integer, UserDto> {

    private final UserRepository userRepository;
    private  final UserMapper userMapper;
    private final UserValidation userValidation;


    @Override
    public ResponseDto<UserDto> create(UserDto dto) {

        List<ErrorDto> errors = this.userValidation.validation(dto);
        try {
            if (errors.isEmpty()) {
                User user = userMapper.toUser(dto);
                user.setCreatedAt(LocalDateTime.now());
                userRepository.save(user);
                List<User> all = userRepository.findAll();
                Optional<User> max = all.stream().max(Comparator.comparingInt(User::getUserId));
                if (max.isPresent()) {
                    dto.setCreatedAt(user.getCreatedAt());
                    dto.setUserId(max.get().getUserId());
                }
                return ResponseDto.<UserDto>builder()
                        .success(true)
                        .massage("OK")
                        .date(dto)
                        .build();
            } else {
                return ResponseDto.<UserDto>builder()
                        .code(-3)
                        .massage("Validation Error!")
                        .errors(errors)
                        .build();
            }


        }

            catch (Exception e){
            return ResponseDto.<UserDto>builder()
                    .code(-2)
                    .massage(String.format("User while saving error %s",e.getMessage()))
                    .build();
        }
    }



    @Override
    public ResponseDto<UserDto> delete(Integer user_id) {
        try {
          return userRepository.findByUserIdAndDeletedAtIsNull(user_id).map(user -> {
               user.setDeletedAt(LocalDateTime.now());
               userRepository.save(user);
               return ResponseDto.<UserDto>builder()
                       .success(true)
                       .massage("OK")
                       .date(userMapper.toDtoNotCard(user))
                       .build();
           }).orElse(ResponseDto.<UserDto>builder()
                   .code(-1)
                   .massage("User not found!")
                   .build());

        }catch (Exception e){
            return ResponseDto.<UserDto>builder()
                    .code(-2)
                    .massage(String.format("User while saving error %s",e.getMessage()))
                    .build();
        }    }

    @Override
    public ResponseDto<UserDto> update(UserDto dto, Integer user_id) {
        try {
           return userRepository.findByUserIdAndDeletedAtIsNull(user_id).map(user -> {
                user.setUpdatedAt(LocalDateTime.now());
                userMapper.toUpdateFromDtoToEntity(user,dto);
                userRepository.save(user);
                return ResponseDto.<UserDto>builder()
                        .success(true)
                        .massage("OK")
                        .date(userMapper.toDtoNotCard(user))
                        .build();

            }).orElse(ResponseDto.<UserDto>builder()
                    .code(-1)
                    .massage("User not found!")
                    .build());


        }catch (Exception e){
            return ResponseDto.<UserDto>builder()
                    .code(-2)
                    .massage(String.format("User while saving error %s",e.getMessage()))
                    .build();
        }    }

    @Override
    public ResponseDto<UserDto> get(Integer user_id) {
         return this.userRepository.findByUserIdAndDeletedAtIsNull(user_id).map(user -> {
             UserDto userDto = userMapper.toDtoWithCard(user);
             userDto.setUserId(user.getUserId());
             return ResponseDto.<UserDto>builder()
                     .massage("Ok")
                     .success(true)
                     .date(userDto)
                     .build();
         }).orElse(ResponseDto.<UserDto>builder()
                 .code(-1)
                 .massage("User not found!")
                 .build());

        }

    }


