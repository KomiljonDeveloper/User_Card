package com.example.second_projects.service;

import com.example.second_projects.dto.CardDto;
import com.example.second_projects.dto.ErrorDto;
import com.example.second_projects.dto.ResponseDto;
import com.example.second_projects.dto.SimpleCrud;
import com.example.second_projects.service.mapper.CardMapper;
import com.example.second_projects.modul.Card;
import com.example.second_projects.repository.CardRepository;
import com.example.second_projects.service.validation.CardValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CardService implements SimpleCrud<Integer, CardDto> {
    private final UserService userService;
    private final CardMapper cardMapper;
    private final CardRepository cardRepository;
    private final CardValidation cardValidation;

    @Override
    public ResponseDto<CardDto> create(CardDto dto) {

        List<ErrorDto> validation = this.cardValidation.validation(dto);
        if (userService.get(dto.getUserId()).getDate() == null) {
            return ResponseDto.<CardDto>builder()
                    .code(-1)
                    .massage("User not found!")
                    .build();
        }
        try {
            if (validation.isEmpty()) {
                Card card = cardMapper.toEntity(dto);
                card.setCreatedAt(LocalDateTime.now());
                cardRepository.save(card);
                return ResponseDto.<CardDto>builder()
                        .massage("OK")
                        .success(true)
                        .date(cardMapper.toDtoWithUser(card))
                        .build();

            }
            return ResponseDto.<CardDto>builder()
                    .code(-3)
                    .massage("Validation error!")
                    .errors(validation)
                    .build();

        } catch (Exception e) {

            return ResponseDto.<CardDto>builder()
                    .code(-2)
                    .massage(String.format("User while saving error %s", e.getMessage()))
                    .build();

        }
    }

    @Override
    public ResponseDto<CardDto> delete(Integer user_id) {

        try {
            return cardRepository.findByCardIdAndDeletedAtIsNull(user_id).map(card -> {
                card.setDeletedAt(LocalDateTime.now());
                cardRepository.save(card);
                return ResponseDto.<CardDto>builder()
                        .success(true)
                        .massage("OK")
                        .date(cardMapper.toDtoNotUser(card))
                        .build();

            }).orElse(ResponseDto.<CardDto>builder()
                    .code(-1)
                    .massage("Card is not found!")
                    .build());
        } catch (Exception e) {

            return ResponseDto.<CardDto>builder()
                    .code(-2)
                    .massage(String.format("User while saving error %s", e.getMessage()))
                    .build();

        }
    }

    @Override
    public ResponseDto<CardDto> update(CardDto dto, Integer user_id) {


        try {
            return cardRepository.findByCardIdAndDeletedAtIsNull(user_id).map(card -> {
                card.setUpdatedAt(LocalDateTime.now());
                cardMapper.toUpdateFromDtoToEntity(card, dto);
                cardRepository.save(card);
                return ResponseDto.<CardDto>builder()
                        .success(true)
                        .massage("OK")
                        .date(cardMapper.toDtoNotUser(card))
                        .build();

            }).orElse(ResponseDto.<CardDto>builder()
                    .code(-1)
                    .massage("Card is not found!")
                    .build());

        } catch (Exception e) {

            return ResponseDto.<CardDto>builder()
                    .code(-2)
                    .massage(String.format("User while saving error %s", e.getMessage()))
                    .build();

        }
    }

    @Override
    public ResponseDto<CardDto> get(Integer user_id) {
        return cardRepository.findByCardIdAndDeletedAtIsNull(user_id).map(card ->
                ResponseDto.<CardDto>builder()
                .massage("OK")
                .success(true)
                .date(cardMapper.toDtoWithUser(card))
                .build()).orElse(

                ResponseDto.<CardDto>builder()
                        .code(-1)
                        .massage("Card is not found!")
                        .build());


    }
    public Set<CardDto> cardDtoSet(Integer id) {
        List<Card> all = this.cardRepository.findByUserIdAndDeletedAtIsNull(id);
        Set<CardDto> cardDtoSet = new HashSet<>();
        for (Card card : all) {
            cardDtoSet.add(this.cardMapper.toDtoNotUser(card));
        }
        return cardDtoSet;
    }
}
