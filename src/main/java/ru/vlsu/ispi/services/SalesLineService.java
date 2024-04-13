package ru.vlsu.ispi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vlsu.ispi.models.SalesLine;
import ru.vlsu.ispi.models.SalesTable;
import ru.vlsu.ispi.repositories.SalesLineRepository;
import ru.vlsu.ispi.repositories.UserRepository;

import java.util.List;

@Service

public class SalesLineService {
    private final SalesLineRepository salesLineRepository;
    private final UserRepository userRepository;

    public SalesLineService(SalesLineRepository salesLineRepository, UserRepository userRepository) {
        this.salesLineRepository = salesLineRepository;
        this.userRepository = userRepository;
    }

    public Page<SalesLine> findPaginated(int pageNo, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return salesLineRepository.findAll(pageable);
    }
    public Page<SalesLine> findBySalesTable(int pageNo, int pageSize, SalesTable salesTable)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        var res = salesLineRepository.findBySalesTable(pageable, salesTable);
        return res;
    }
    public List<SalesLine> findBySalesTableList(SalesTable salesTable)
    {
        return salesLineRepository.findBySalesTableOrderByQuantity(salesTable);
    }
    public void save(SalesLine salesLine){
        salesLineRepository.save(salesLine);
    }
}
