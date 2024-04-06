package ru.vlsu.ispi.services;

import org.springframework.stereotype.Service;
import ru.vlsu.ispi.models.ImageLob;
import ru.vlsu.ispi.repositories.ImageLobRepository;

@Service
public class ImageLobService {
    private final ImageLobRepository imageLobRepository;

    public ImageLobService(ImageLobRepository imageLobRepository) {
        this.imageLobRepository = imageLobRepository;
    }

    public void save(ImageLob imageLob){
        imageLobRepository.save(imageLob);
    }
}
