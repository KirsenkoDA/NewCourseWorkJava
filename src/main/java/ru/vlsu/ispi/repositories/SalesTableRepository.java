package ru.vlsu.ispi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlsu.ispi.models.SalesTable;
import ru.vlsu.ispi.models.Status;
import ru.vlsu.ispi.models.User;

@Repository
public interface SalesTableRepository extends JpaRepository<SalesTable, Long> {
    @Override
    Page<SalesTable> findAll(Pageable pageable);
    Page<SalesTable> findAllByUser(Pageable pageable, User user);
    Page<SalesTable> findByUser(Pageable pageable, User user);
    SalesTable findByStatusAndUser(Status status, User user);
}
