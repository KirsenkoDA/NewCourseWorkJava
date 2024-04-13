package ru.vlsu.ispi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlsu.ispi.models.Status;
@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
}
