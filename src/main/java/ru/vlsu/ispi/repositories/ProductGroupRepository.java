package ru.vlsu.ispi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlsu.ispi.models.ProductGroup;

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long> {
}
