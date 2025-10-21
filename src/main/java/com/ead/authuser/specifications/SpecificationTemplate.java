package com.ead.authuser.specifications;

import com.ead.authuser.models.UserModel;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

//essa classe define um template de especificação para consultas dinâmicas na entidade UserModel.
public class SpecificationTemplate {

    //A notação @And agrupa várias especificações em uma única especificação composta.
    @And(
    // Esta notação cria uma especificação para consultas dinâmicas e o spec é quem define a forma de validar, se vai ser equals, like, etc.
            {@Spec(path = "userType", spec = Equal.class),
                    @Spec(path = "userStatus", spec = Equal.class),
                    @Spec(path = "Email", spec = Like.class)
      })
    public interface UserEspec extends Specification<UserModel> {
    }
}
