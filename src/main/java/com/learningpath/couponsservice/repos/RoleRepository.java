package com.learningpath.couponsservice.repos;

import com.learningpath.couponsservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
