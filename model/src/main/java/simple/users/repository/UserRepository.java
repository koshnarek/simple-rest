package simple.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import simple.users.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
