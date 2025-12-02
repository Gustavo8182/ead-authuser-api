package com.ead.authuser.clients;

import com.ead.authuser.dtos.CourseDto;
import com.ead.authuser.dtos.ResponsePageDto;
import com.ead.authuser.services.UtilsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Log4j2
@Component
//Esta classe faz a comunicação com o microserviço de cursos para obter os cursos associados a um usuário específico.
public class CourseClient {

    //Injeção do RestTemplate para fazer requisições HTTP.
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UtilsService utilsService;

    //URL base do microserviço de cursos, injetada a partir das propriedades da aplicação.
    @Value("${ead.api.url.course}")
    String REQUEST_URL_COURSE;

    //Método para obter todos os cursos associados a um usuário específico, com suporte à paginação.
    public Page<CourseDto> getAllCoursesByUser(UUID userId, Pageable pageable){
        List<CourseDto> searchResult = null;
        ResponseEntity<ResponsePageDto<CourseDto>> result = null;
        //Cria a URL completa para a requisição, incluindo os parâmetros de paginação.
        String url = REQUEST_URL_COURSE + utilsService.createUrlGetAllCoursesByUser(userId, pageable);
        log.debug("Request URL: {} ", url);
        log.info("Request URL: {} ", url);
        try{
            //Define o tipo de resposta esperada usando ParameterizedTypeReference para lidar com tipos genéricos.
            ParameterizedTypeReference<ResponsePageDto<CourseDto>> responseType = new ParameterizedTypeReference<ResponsePageDto<CourseDto>>() {};
            //Faz a requisição GET ao microserviço de cursos.
            result = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
            //Extrai o conteúdo da resposta.
            searchResult = result.getBody().getContent();
            log.debug("Response Number of Elements: {} ", searchResult.size());
        } catch (HttpStatusCodeException e){
            log.error("Error request /courses {} ", e);
        }
        log.info("Ending request /courses userId {} ", userId);
        return result.getBody();
    }
}
