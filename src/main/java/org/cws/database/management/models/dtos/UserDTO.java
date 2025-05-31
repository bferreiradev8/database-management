package org.cws.database.management.models.dtos;

import lombok.*;
import org.cws.database.management.models.ActionInEnum;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDTO {

    private BigDecimal cwsUserId;
    private String loginId;
    private String firstName;
    private String lastName;
    private String activeYn;
    private String supervisorYn;
    private String email;
    private String enteredBy;
    private String updatedBy;
    private ActionInEnum action;
}
