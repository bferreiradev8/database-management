package org.cws.database.management.repositories;

import lombok.extern.slf4j.Slf4j;
import org.cws.database.management.models.dtos.UserDTO;
import org.cws.database.management.models.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<UserResponse> getUser(BigDecimal cwsUserId, String loginId){
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("CWS_USER_GET_PRC")
                .declareParameters(
                        new SqlParameter("CWS_USER_ID_IN", Types.INTEGER),
                        new SqlParameter("LOGIN_ID_IN", Types.VARCHAR),
                        new SqlOutParameter("REF_CUR_OUT", Types.REF_CURSOR),
                        new SqlOutParameter("ERROR_OUT", Types.VARCHAR))
                .returningResultSet("REF_CUR_OUT", new RowMapper<UserResponse>() {
                    @Override
                    public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                        UserResponse userResponse = new UserResponse();
                        userResponse.setCwsUserId(rs.getBigDecimal("CWS_USER_ID"));
                        userResponse.setLoginId(rs.getString("LOGIN_ID"));
                        userResponse.setFirstName(rs.getString("FIRST_NAME"));
                        userResponse.setLastName(rs.getString("LAST_NAME"));
                        userResponse.setActiveYn(rs.getString("ACTIVE_YN"));
                        userResponse.setSupervisorYn(rs.getString("SUPERVISOR_YN"));
                        userResponse.setEmail(rs.getString("EMAIL"));
                        userResponse.setEnteredBy(rs.getString("ENTERED_BY"));
                        userResponse.setCreateTime(rs.getTimestamp("CREATE_TIME"));
                        userResponse.setUpdatedBy(rs.getString("UPDATED_BY"));
                        userResponse.setUpdateTime(rs.getTimestamp("UPDATE_TIME"));
                        return userResponse;
                    }
                });

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CWS_USER_ID_IN", cwsUserId);
        mapSqlParameterSource.addValue("LOGIN_ID_IN", loginId);

        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        if (results.get("ERROR_OUT") != null){
            if (results.get("ERROR_OUT").toString() != null){
                throw new RuntimeException(results.get("ERROR_OUT").toString());
            }
            else{
                throw new RuntimeException("Error calling SP");
            }
        }
        List<UserResponse> userResponseList = (List<UserResponse>) results.get("REF_CUR_OUT");
        return userResponseList;
    }

    public BigDecimal saveOrUpdateUser(UserDTO userDTO){
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("CWS_USER_A_D_U_PRC")
                .declareParameters(
                        new SqlInOutParameter("CWS_USER_ID_INOUT", Types.INTEGER),
                        new SqlParameter("LOGIN_ID_IN", Types.VARCHAR),
                        new SqlParameter("FIRST_NAME_IN", Types.VARCHAR),
                        new SqlParameter("LAST_NAME_IN", Types.VARCHAR),
                        new SqlParameter("ACTIVE_YN_IN", Types.VARCHAR),
                        new SqlParameter("SUPERVISOR_YN_IN", Types.VARCHAR),
                        new SqlParameter("EMAIL_IN", Types.VARCHAR),
                        new SqlParameter("ENTERED_BY_IN", Types.VARCHAR),
                        new SqlParameter("UPDATED_BY_IN", Types.VARCHAR),
                        new SqlParameter("ACTION_IN", Types.VARCHAR),
                        new SqlOutParameter("ERROR_OUT", Types.VARCHAR)
                );

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("CWS_USER_ID_INOUT", userDTO.getCwsUserId());
        mapSqlParameterSource.addValue("LOGIN_ID_IN", userDTO.getLoginId());
        mapSqlParameterSource.addValue("FIRST_NAME_IN", userDTO.getFirstName());
        mapSqlParameterSource.addValue("LAST_NAME_IN", userDTO.getLastName());
        mapSqlParameterSource.addValue("ACTIVE_YN_IN", userDTO.getActiveYn());
        mapSqlParameterSource.addValue("SUPERVISOR_YN_IN", userDTO.getSupervisorYn());
        mapSqlParameterSource.addValue("EMAIL_IN", userDTO.getEmail());
        mapSqlParameterSource.addValue("ENTERED_BY_IN", userDTO.getEnteredBy());
        mapSqlParameterSource.addValue("UPDATED_BY_IN", userDTO.getUpdatedBy());
        mapSqlParameterSource.addValue("ACTION_IN", userDTO.getAction().getActionIn());

        Map<String, Object> results = simpleJdbcCall.execute(mapSqlParameterSource);
        if (results.get("ERROR_OUT") != null){
            if (results.get("ERROR_OUT").toString() != null){
                throw new RuntimeException(results.get("ERROR_OUT").toString());
            }
            else{
                throw new RuntimeException("Error calling SP");
            }
        }
        return new BigDecimal(results.get("CWS_USER_ID_INOUT").toString());
    }
}