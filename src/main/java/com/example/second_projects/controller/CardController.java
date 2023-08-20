package com.example.second_projects.controller;


import com.example.second_projects.dto.CardDto;
import com.example.second_projects.dto.ResponseDto;
import com.example.second_projects.dto.SimpleCrud;
import com.example.second_projects.service.CardService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("card")
public record CardController(CardService cardService) implements SimpleCrud<Integer, CardDto> {


    @Override
    @PostMapping("/create")
    public ResponseDto<CardDto> create(@RequestBody CardDto dto) {
        return this.cardService.create(dto);
    }

    @Override
    @DeleteMapping("/delete/{user_id}")
    public ResponseDto<CardDto> delete(@PathVariable Integer user_id) {
        return this.cardService.delete(user_id);
    }

    @Override
    @PutMapping("/update/{user_id}")
    public ResponseDto<CardDto> update(@RequestBody CardDto dto,@PathVariable Integer user_id) {
        return this.cardService.update(dto, user_id);
    }
    @GetMapping("/get/{user_id}")
    @Override
    public ResponseDto<CardDto> get(@PathVariable Integer user_id) {
        return this.cardService.get(user_id);
    }
}