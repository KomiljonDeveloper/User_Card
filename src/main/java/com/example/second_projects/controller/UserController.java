package com.example.second_projects.controller;

import com.example.second_projects.dto.ResponseDto;
import com.example.second_projects.dto.SimpleCrud;
import com.example.second_projects.dto.UserDto;
import com.example.second_projects.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public record UserController(UserService userService) implements SimpleCrud<Integer, UserDto> {
    @PostMapping("/create")
    @Override
    public ResponseDto<UserDto> create(@RequestBody @Valid UserDto dto) {
        return this.userService.create(dto);
    }
    @DeleteMapping("/delete/{user_id}")
    @Override
    public ResponseDto<UserDto> delete(@PathVariable Integer user_id) {
        return this.userService.delete(user_id);
    }
    @PutMapping("/update/{user_id}")
    @Override
    public ResponseDto<UserDto> update(@RequestBody UserDto dto,@PathVariable Integer user_id) {
        return this.userService.update(dto,user_id);
    }
    @GetMapping("/get/{user_id}")
    @Override
    public ResponseDto<UserDto> get(@PathVariable Integer user_id) {
        return this.userService.get(user_id);
    }
}
