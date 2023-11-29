package com.ops.authentication.OPSAuthentication.Repository;

import com.ops.authentication.OPSAuthentication.model.response.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends JpaRepository<UserResponse,Long> {
}
