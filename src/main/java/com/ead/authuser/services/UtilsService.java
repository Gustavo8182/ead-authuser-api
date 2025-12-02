package com.ead.authuser.services;

import org.springframework.data.domain.Pageable;

import java.util.UUID;

//esta classe monta a url para pegar os cursos de um usuario
public interface UtilsService {
    //método que monta a url para pegar todos os cursos de um usuário
    String createUrlGetAllCoursesByUser(UUID userId, Pageable pageable);
}
