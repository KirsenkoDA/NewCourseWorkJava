package ru.vlsu.ispi.services;

import org.springframework.stereotype.Service;
import ru.vlsu.ispi.models.ProductGroup;
import ru.vlsu.ispi.models.Status;
import ru.vlsu.ispi.repositories.StatusRepository;

import java.util.List;

@Service
public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> statusList() {
        return statusRepository.findAll();
    }
    public Status show(long id)
    {
        return statusRepository.findById(id).orElse(null);
    }
    public void save(Status status){
        statusRepository.save(status);
    }
    public void delete(long id) { statusRepository.deleteById(id); }
}
