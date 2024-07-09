package com.maverickshube.maverickshube.services;


import com.maverickshube.maverickshube.dtos.request.CreateUserRequest;
import com.maverickshube.maverickshube.dtos.response.CreateUserResponse;
import com.maverickshube.maverickshube.exceptions.UserNotFoundException;
import com.maverickshube.maverickshube.models.User;
import com.maverickshube.maverickshube.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MavericksHubUserService implements UserService {


    private final UserRepository userRepository;
    @Autowired
    public MavericksHubUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public CreateUserResponse register(CreateUserRequest request) {
        ModelMapper modelMapper = new ModelMapper();
        User user = modelMapper.map(request, User.class);
        user = userRepository.save(user);

        var response = modelMapper.map(user, CreateUserResponse.class);
        response.setMessage("user registered successfully");
        return response;
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id)
        .orElseThrow(()->new UserNotFoundException(String.format("user with id %d not found", id)));
    }

    @Override
    public User getUserByUsername(String username) {
        User user =  userRepository.findByEmail(username).orElseThrow(()-> new UserNotFoundException("user not found"));
        return user;
    }
}