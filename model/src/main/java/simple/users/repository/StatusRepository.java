package simple.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import simple.users.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}
