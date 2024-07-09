package com.maverickshube.maverickshube.services;



import com.maverickshube.maverickshube.dtos.request.CreateUserRequest;
import com.maverickshube.maverickshube.dtos.response.CreateUserResponse;
import com.maverickshube.maverickshube.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    CreateUserResponse register(CreateUserRequest request);

    User getUserById(long id);

    User getUserByUsername(String username);
}
