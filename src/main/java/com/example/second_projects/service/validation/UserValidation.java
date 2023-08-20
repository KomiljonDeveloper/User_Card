package com.example.second_projects.service.validation;

import com.example.second_projects.dto.ErrorDto;
import com.example.second_projects.dto.UserDto;
import com.example.second_projects.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidation {
    @Autowired
    UserRepository userRepository;
    public List<ErrorDto> validation(UserDto dto){
        List<ErrorDto> errorList = new ArrayList<>();

      if (!checkEmail(dto.getEmail())) {
            errorList.add(new ErrorDto("email","This email is already registered or email is invalid!"));
        }
        return errorList;
    }



    private boolean checkEmail(String email) {
        String[] split = email.split("@");
        if (split.length==2 && !this.userRepository.existsByEmail(email)){
            return split[1].equals("gmail.com");
        }else
        {
            return false;
        }

    }

    private boolean checkPassword(String password){
        return password.length() >= 6;
    }



}
