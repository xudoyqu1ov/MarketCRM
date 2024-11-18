package uz.pdp.marketcrm.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.marketcrm.domain.entity.UserEntity;
import uz.pdp.marketcrm.exception.BaseException;
import uz.pdp.marketcrm.domain.request.LoginDTO;
import uz.pdp.marketcrm.domain.request.UserRequest;
import uz.pdp.marketcrm.domain.response.JwtResponse;
import uz.pdp.marketcrm.domain.response.UserResponse;
import uz.pdp.marketcrm.repository.UserRepository;
import uz.pdp.marketcrm.service.JwtService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        UserEntity userEntity = UserEntity.builder()
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .email(userRequest.getEmail())
                .build();

        userRepository.save(userEntity);


        return UserResponse.builder()
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .build();

    }

    @Override
    public UserResponse updateUser(UUID id, UserRequest userRequest) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new BaseException("User not found"));

        userEntity.setUsername(userRequest.getUsername());
        userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userEntity.setEmail(userRequest.getEmail());
        userRepository.save(userEntity);

        return UserResponse.builder()
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .build();

    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse findById(UUID id) {

        UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new BaseException("User not found"));

        return UserResponse.builder()
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .build();
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public JwtResponse login(LoginDTO loginDTO) {
        UserEntity userEntity = userRepository.findByUsername(loginDTO.getUsername()).orElseThrow(() -> new BaseException("user not found"));
        if (!passwordEncoder.matches(loginDTO.getPassword(), userEntity.getPassword())) {
            throw new BaseException("wrong password");
        }

        return new JwtResponse(jwtService.generateAccessToken(userEntity),
                jwtService.generateRefreshToken(userEntity));
    }
}
