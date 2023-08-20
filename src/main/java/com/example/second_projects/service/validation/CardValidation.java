package com.example.second_projects.service.validation;

import com.example.second_projects.dto.CardDto;
import com.example.second_projects.dto.ErrorDto;
import com.example.second_projects.repository.CardRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CardValidation {
    @Autowired
    CardRepository cardRepository;
    public List<ErrorDto> validation(CardDto dto){
        List<ErrorDto> errorList = new ArrayList<>();

        if (StringUtils.isBlank(dto.getCardName())){
            errorList.add(new ErrorDto("cardName","This data column cannot be empty."));
        }

        if (StringUtils.isBlank(dto.getType())){
            errorList.add(new ErrorDto("CardType","This data column cannot be empty."));
        }

        if (dto.getCardNumber()==null){
            errorList.add(new ErrorDto("CardNumber","This data column cannot be empty."));
        }else if (!checkCardNumber(dto.getCardNumber())){
            errorList.add(new ErrorDto("CardNumber","Card number must be at least 8 digits long"));
        }

        if (dto.getAmount()==null){
            errorList.add(new ErrorDto("Amount","This data column cannot be empty."));
        } else if (!checkAmount(dto.getAmount())) {
            errorList.add(new ErrorDto("Amount","The minimum amount of money on the card must exceed 20,000 soums."));
        }
        return errorList;
    }



    private boolean checkAmount(Double amount) {
        return amount >= 20000;

    }

    private boolean checkCardNumber(String password){
        return password.length() >= 8;
    }



}
