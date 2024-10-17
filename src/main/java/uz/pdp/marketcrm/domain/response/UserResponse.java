package uz.pdp.marketcrm.domain.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {
    private String username;
    private String email;
}
