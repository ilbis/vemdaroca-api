package com.vemdaroca.vemdarocaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vemdaroca.vemdarocaapi.model.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
}
