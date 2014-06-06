package simple.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import simple.users.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
