package com.ead.authuser.controllers;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import com.ead.authuser.specifications.SpecificationTemplate;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//heateos é uma biblioteca que implementa o conceito de HATEOAS (Hypermedia as the Engine of Application State) para APIs RESTful em Java.
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//notação para ser um bean controlado pelo spring
@RestController
//notação para quem pode acessar e o tempo maximo de acesso, posso fazer por método também.
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService usarService;
    @Autowired
    private UserService userService;

    @GetMapping
    //a notação PageableDefault é para podermos enviar os dados já com paginação.
    public ResponseEntity<Page<UserModel>> getAllUsers(SpecificationTemplate.UserEspec spec,
                                                       @PageableDefault(page = 0, size = 10, sort = "UserId", direction = Sort.Direction.ASC) Pageable pageable){
        //Este método faz a busca das informação ja contendo a paginação e com isso retornamos a pagina com 10 items que foi o que especificamos por padrão, caso a pagina não venha ele retorna por padrão a pagina 0.
        Page<UserModel> userModelPage = userService.findAll(spec, pageable);
        //Adicionando links em cada user retornado na paginação.
        if(!userModelPage.isEmpty()){
            for(UserModel user : userModelPage.toList()){
                user.add(linkTo(methodOn(UserController.class).getOneUser(user.getUserId())).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(userModelPage);
    };

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "userId")UUID userId){
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }else{
            //para casos que o optional tenha algum dado devemos usar get.
            return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
        }
    };

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "userId")UUID userId){
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }else{
            userService.delete(userModelOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
        }
    };
    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable(value = "userId") UUID userId,
                                             @RequestBody  @Validated(UserDto.UserView.UserPut.class)
                                             @JsonView(UserDto.UserView.UserPut.class) UserDto userDto){
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if(!userModelOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } else{
            var userModel = userModelOptional.get();
            userModel.setFullName(userDto.getFullName());
            userModel.setPhoneNumber(userDto.getPhoneNumber());
            userModel.setCpf(userDto.getCpf());
            userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
            userService.save(userModel);
            return  ResponseEntity.status(HttpStatus.OK).body(userModel);
        }
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<Object> updatePassword(@PathVariable(value = "userId") UUID userId,
                                                 @RequestBody @Validated(UserDto.UserView.PasswordPut.class)
                                                 @JsonView(UserDto.UserView.PasswordPut.class) UserDto userDto){
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if(!userModelOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } if(!userModelOptional.get().getPassword().equals(userDto.getOldPassword())){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Mismatched old password!");
        } else{
            var userModel = userModelOptional.get();
            userModel.setPassword(userDto.getPassword());
            userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
            userService.save(userModel);
            return  ResponseEntity.status(HttpStatus.OK).body("Password updated successfully.");
        }
    }


    @PutMapping("/{userId}/image")
    public ResponseEntity<Object> updateImage(@PathVariable(value = "userId") UUID userId,
                                              @RequestBody @Validated(UserDto.UserView.ImagePut.class)
                                              @JsonView(UserDto.UserView.ImagePut.class) UserDto userDto){
        Optional<UserModel> userModelOptional = userService.findById(userId);
        if(!userModelOptional.isPresent()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } else{
            var userModel = userModelOptional.get();
            userModel.setImageUrl(userDto.getImageUrl());
            userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
            userService.save(userModel);
            return  ResponseEntity.status(HttpStatus.OK).body(userModel);
        }
    }
}
