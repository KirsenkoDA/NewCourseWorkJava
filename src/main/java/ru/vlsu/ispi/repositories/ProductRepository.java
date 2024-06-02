package ru.vlsu.ispi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.vlsu.ispi.models.Product;
import ru.vlsu.ispi.models.ProductGroup;
import ru.vlsu.ispi.models.Wallet;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    Page<Product> findAll(Pageable pageable);
    Page<Product> findByProductGroup(ProductGroup productGroup, Pageable pageable);
    Page<Product> findAllByProductGroup(ProductGroup productGroup, Pageable pageable);

    @Query("DELETE FROM Product p WHERE p.id = :productId")
    void delete(@Param("productId") Long productId);
}
