//package ru.vlsu.ispi.services;
//
//import org.springframework.stereotype.Service;
//import ru.vlsu.ispi.models.Image;
//import ru.vlsu.ispi.models.Product;
//import ru.vlsu.ispi.repositories.ImageRepository;
//
//import java.util.List;
//
//@Service
//public class ImageService {
//    private final ImageRepository imageRepository;
//
//    public ImageService(ImageRepository imageRepository) {
//        this.imageRepository = imageRepository;
//    }
//
//    public void save(Image image)
//    {
//        imageRepository.save(image);
//    }
//    public Image findByUrl(String url)
//    {
//        Image image = imageRepository.findAllByUrl(url).get(0);
//        return image;
//    }
//    public List<Image> findAllByProduct(Product product)
//    {
//        return imageRepository.findAllByProduct(product);
//    }
//    public Image show(Long id)
//    {
//        return imageRepository.findById(id).orElse(null);
//    }
//}
