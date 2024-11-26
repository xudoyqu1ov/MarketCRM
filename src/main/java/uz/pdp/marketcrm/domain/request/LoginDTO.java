package uz.pdp.marketcrm.domain.request;

import lombok.Getter;
import lombok.Setter;
import uz.pdp.marketcrm.domain.enumurators.UserRole;

@Getter
@Setter

public class LoginDTO {
    private String username;
    private String password;
    private UserRole role;

}
