package ru.vlsu.ispi.services;

import org.springframework.stereotype.Service;
import ru.vlsu.ispi.models.ProductGroup;
import ru.vlsu.ispi.repositories.ProductGroupRepository;

import java.util.List;

@Service
public class ProductGroupService {
    private final ProductGroupRepository productGroupRepository;

    public ProductGroupService(ProductGroupRepository productGroupRepository) {
        this.productGroupRepository = productGroupRepository;
    }

    public List<ProductGroup> groupList() {
        return productGroupRepository.findAll();
    }
    public ProductGroup getProductGroup(Long productGroupId) {
        return productGroupRepository.findById(productGroupId).get();
    }
    public ProductGroup show(long id)
    {
        return productGroupRepository.findById(id).orElse(null);
    }
    public void save(ProductGroup productGroup){
        productGroupRepository.save(productGroup);
    }
    public void delete(long id)
    {
        productGroupRepository.deleteById(id);
    }
    public ProductGroup findById(long id)
    {
        return productGroupRepository.findById(id).orElse(null);
    }
}
