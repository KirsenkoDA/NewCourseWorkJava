package ru.vlsu.ispi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlsu.ispi.models.Image;
import ru.vlsu.ispi.models.Product;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByUrl(String url);
    List<Image> findAllByProduct(Product product);
}
