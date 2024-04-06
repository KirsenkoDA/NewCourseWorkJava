package ru.vlsu.ispi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vlsu.ispi.models.ImageLob;

public interface ImageLobRepository extends JpaRepository<ImageLob, Long> {
}
