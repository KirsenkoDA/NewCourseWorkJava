package ru.vlsu.ispi.repositories;

import org.springframework.stereotype.Repository;
import ru.vlsu.ispi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
}
