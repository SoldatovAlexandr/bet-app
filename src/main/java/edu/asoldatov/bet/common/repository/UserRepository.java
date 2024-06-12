package edu.asoldatov.bet.common.repository;

import edu.asoldatov.bet.common.model.User;
import edu.asoldatov.bet.common.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByStatus(UserStatus status);

}
