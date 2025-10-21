package com.ead.authuser.repositories;

import com.ead.authuser.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

//para utilizar as especificações do JPA para consultas dinâmicas temos que extender a interface JpaSpecificationExecutor
public interface UserRepository extends JpaRepository<UserModel, UUID>, JpaSpecificationExecutor<UserModel> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);


}
