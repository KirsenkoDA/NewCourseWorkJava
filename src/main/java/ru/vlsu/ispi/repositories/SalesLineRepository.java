package ru.vlsu.ispi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlsu.ispi.models.SalesLine;
import ru.vlsu.ispi.models.SalesTable;

import java.util.List;

@Repository
public interface SalesLineRepository extends JpaRepository<SalesLine, Long> {
    @Override
    Page<SalesLine> findAll(Pageable pageable);
    Page<SalesLine> findBySalesTable(Pageable pageable, SalesTable salesTable);
    List<SalesLine> findBySalesTableOrderByQuantity(SalesTable salesTable);
}
