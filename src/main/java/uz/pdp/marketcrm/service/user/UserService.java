package uz.pdp.marketcrm.service.user;

import uz.pdp.marketcrm.domain.request.LoginDTO;
import uz.pdp.marketcrm.domain.request.UserRequest;
import uz.pdp.marketcrm.domain.response.JwtResponse;
import uz.pdp.marketcrm.domain.response.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserResponse saveUser(UserRequest userRequest);
    UserResponse updateUser(UUID id, UserRequest userRequest);
    void deleteUser(UUID id);
    UserResponse findById(UUID id);
    List<UserResponse> getAllUsers();
    JwtResponse login(LoginDTO loginDTO);

}
