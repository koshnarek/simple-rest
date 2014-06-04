package uol.cubus.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uol.cubus.users.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
