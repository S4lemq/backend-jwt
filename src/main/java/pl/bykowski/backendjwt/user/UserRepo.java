package pl.bykowski.backendjwt.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<MyUser, Long> {

    Optional<MyUser> findByEmail(String email);
}
