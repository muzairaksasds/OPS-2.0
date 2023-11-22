package com.ops.authentication.OPSAuthentication.Repository;

import com.ops.authentication.OPSAuthentication.model.AuthRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRequestRepository extends JpaRepository<AuthRequest, Long> {
    // Additional query methods can be defined here if needed
}