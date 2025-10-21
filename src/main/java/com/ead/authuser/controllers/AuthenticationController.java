package com.ead.authuser.controllers;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

//notação para ser um bean controlado pelo spring
@RestController
//notação para quem pode acessar e o tempo maximo de acesso, posso fazer por método também.
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody @Validated(UserDto.UserView.RegistrationPost.class)
                                                   @JsonView(UserDto.UserView.RegistrationPost.class) UserDto userDto) {
        if(userService.existsByUsername(userDto.getUsername())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: UserName existente ");
        }
        if(userService.existsByEmail(userDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email existente ");
        }
        // no java 11 é possível não especificar dos dois lados o tipo da variável
        var userModel = new UserModel();
        //Com este método eu transformo o userdto em usermodel
        BeanUtils.copyProperties(userDto, userModel);
        // agora tenho que presence os campos que o userdto não abrange para fechar a conversão do dto para model.
        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.STUDANT);
        //com este método eu adiciono o formato da data em UTC
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        userService.save(userModel);
        //neste caso a resposta Http deve ser created para ser mais específica.
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }


}
