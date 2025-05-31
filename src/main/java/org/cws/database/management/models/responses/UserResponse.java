package org.cws.database.management.models.responses;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserResponse {

    private BigDecimal cwsUserId;
    private String loginId;
    private String firstName;
    private String lastName;
    private String activeYn;
    private String supervisorYn;
    private String email;
    private String enteredBy;
    private Date createTime;
    private String updatedBy;
    private Date updateTime;
}