package ru.vlsu.ispi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlsu.ispi.models.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
