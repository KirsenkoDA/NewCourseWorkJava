package ru.vlsu.ispi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vlsu.ispi.models.SalesTable;
import ru.vlsu.ispi.models.Status;
import ru.vlsu.ispi.models.User;
import ru.vlsu.ispi.repositories.SalesTableRepository;
import ru.vlsu.ispi.repositories.UserRepository;

@Service
public class SalesTableService {
    private final SalesTableRepository salesTableRepository;
    private final UserRepository userRepository;

    public SalesTableService(SalesTableRepository salesTableRepository, UserRepository userRepository) {
        this.salesTableRepository = salesTableRepository;
        this.userRepository = userRepository;
    }

    public Page<SalesTable> findPaginated(int pageNo, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return salesTableRepository.findAll(pageable);
    }
    public Page<SalesTable> findPaginatedByUser(int pageNo, int pageSize, User user)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return salesTableRepository.findAllByUser(pageable, user);
    }
    public Page<SalesTable> findByUser(int pageNo, int pageSize, User user)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return salesTableRepository.findByUser(pageable, user);
    }
    public SalesTable findByStatusAndUser(Status status, User user)
    {
        return salesTableRepository.findByStatusAndUser(status, user);
    }
    public void save(SalesTable salesTable){
        salesTableRepository.save(salesTable);
    }
    public SalesTable show(Long id)
    {
        SalesTable salesTable = salesTableRepository.findById(id).orElse(null);
        return  salesTable;
    }
}
