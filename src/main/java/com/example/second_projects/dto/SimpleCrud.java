package com.example.second_projects.dto;

public interface SimpleCrud<K,V> {
    ResponseDto<V> create(V dto);
    ResponseDto<V> delete(K user_id);
    ResponseDto<V> update(V dto,K user_id);
    ResponseDto<V> get(K user_id);

}
