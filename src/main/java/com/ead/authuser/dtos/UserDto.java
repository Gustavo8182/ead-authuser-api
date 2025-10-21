package com.ead.authuser.dtos;

import com.ead.authuser.validation.UsernameConstraint;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

//Esta notação é do lombok que nos libera de cria gets e sets
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    //São interfaces que cada parâmetro vai ter acesso, assim especificando o que cada um pode acessar de acordo com o que vai fazer.
    public interface UserView {
        public static interface RegistrationPost {}
        public static interface UserPut {}
        public static interface PasswordPut {}
        public static interface ImagePut {}
    }

    private UUID userId;
    //Esta notação vem da biblioteca de validação que impede de receber parâmetros objeto não seja nulo, vazio ou composto apenas por espaços em branco
    @NotBlank(groups = UserView.RegistrationPost.class)
    //Esta notação serve para ser especifícado o tamanho mínimo e maximo do campo.
    @Size(min = 4, max = 50, groups = UserView.RegistrationPost.class)
    //notação personalizada para validar campo de username.
    @UsernameConstraint(groups = UserView.RegistrationPost.class)
    //Esta notação é para identificar a qual interface este atributo pertence e deve ser monitorado
    @JsonView(UserView.RegistrationPost.class)
    private String username;

    @NotBlank(groups = UserView.RegistrationPost.class)
    //temos validações especificas na biblíoteca como esta para Email.
    @Email(groups = UserView.RegistrationPost.class)
    @JsonView(UserView.RegistrationPost.class)
    private String email;

    @NotBlank(groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class})
    @Size(min = 6, max = 20, groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class})
    @JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
    private String password;

    @NotBlank(groups = UserView.PasswordPut.class)
    @Size(min = 6, max = 20, groups = UserView.PasswordPut.class)
    @JsonView({UserView.PasswordPut.class})
    private String oldPassword;

    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String fullName;

    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String phoneNumber;


    @JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
    private String cpf;

    @NotBlank(groups = UserView.ImagePut.class)
    @JsonView({UserView.ImagePut.class})
    private String imageUrl;
}
