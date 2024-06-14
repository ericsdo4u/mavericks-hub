package com.maverickshube.maverickshube.services;



import com.maverickshube.maverickshube.dtos.CreateUserRequest;
import com.maverickshube.maverickshube.dtos.CreateUserResponse;
import com.maverickshube.maverickshube.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    CreateUserResponse register(CreateUserRequest request);

    User getUserById(long id);

}
