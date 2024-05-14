package com.TRA.tra24Springboot.Repositories;

import com.TRA.tra24Springboot.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository <User,Integer> {
}
