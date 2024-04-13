package ru.vlsu.ispi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.vlsu.ispi.models.Image;
import ru.vlsu.ispi.models.ImageLob;
import ru.vlsu.ispi.models.Product;
import ru.vlsu.ispi.models.ProductGroup;
import ru.vlsu.ispi.repositories.ImageLobRepository;
import ru.vlsu.ispi.repositories.ImageRepository;
import ru.vlsu.ispi.repositories.ProductRepository;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;
    private final ImageLobRepository imageLobRepository;
    private final ImageLobService imageLobService;

    public ProductService(ProductRepository productRepository, ImageRepository imageRepository, ImageLobRepository imageLobRepository, ImageLobService imageLobService) {
        this.productRepository = productRepository;
        this.imageRepository = imageRepository;
        this.imageLobRepository = imageLobRepository;
        this.imageLobService = imageLobService;
    }

    public Product show(long id)
    {
        return productRepository.findById(id).orElse(null);
    }
    public void save(Product product)
    {
        productRepository.save(product);
    }
//    public Image getDefaultImage(Image defaultPhoto)
//    {
//        Image image = new Image();
//        image.setSize(defaultPhoto.getSize());
//        image.setPreviewImage(true);
//        image.setOriginalFileName(defaultPhoto.getOriginalFileName());
//        image.setContentType(defaultPhoto.getContentType());
//        image.setName(defaultPhoto.getName());
//        return image;
//    }
//    public void saveLob(ImageLob imageLob)
//    {
//        imageLobService.save(imageLob);
//    }

//    public void save(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
//        Image defaultPhoto = imageRepository.getById(-1L);//Ищем служебную запись с картинкой defaultPhoto
//        Image image1;
//        Image image2;
//        Image image3;
//        ImageLob imageLob1;
//        ImageLob imageLob2;
//        ImageLob imageLob3;
//        byte[] bytes1;
//        byte[] bytes2;
//        byte[] bytes3;
//        if(product.getId() != null)
//        {
//            List<Image> images = show(product.getId()).getImages();
//            product.setImages(images);
//        }
//        if(file1.getSize() != 0)//Если картинка выбрана
//        {
//            bytes1 = file1.getBytes();
//            image1 = toImageEntity(file1);//Сохранение характеристик фото
//            image1.setPreviewImage(true);
//            if(product.getImages().size() > 0)//Картинка номер 1 в базе существует(обновляем)
//            {
//                image1.setId(product.getImages().get(0).getId());
//                product.updateImageFromProduct(image1, 0);
//            }
//            else//Картинки номер 1 в базе не существует(добавляем)
//            {
//                product.addImageToProduct(image1);
//            }
//        }
//        else
//        {//Если картинка не выбрана, то сохраняется картинка по умолчанию
//            image1 = getDefaultImage(defaultPhoto);
//            bytes1 = imageLobRepository.findById(defaultPhoto.getId()).get().getBytes();
//            product.addImageToProduct(image1);
//        }
//        if(file2.getSize() != 0)//Если картинка выбрана
//        {
//            bytes2 = file2.getBytes();
//            image2 = toImageEntity(file2);//Сохранение характеристик фото
//            if(product.getImages().size() > 1)//Картинка номер 2 в базе существует(обновляем)
//            {
//                image2.setId(product.getImages().get(1).getId());
//                product.updateImageFromProduct(image2, 1);
//            }
//            else//Картинки номер 2 в базе не существует(добавляем)
//            {
//                product.addImageToProduct(image2);
//            }
//        }
//        else
//        {//Если картинка не выбрана, то сохраняется картинка по умолчанию
//            image2 = getDefaultImage(defaultPhoto);
//            bytes2 = imageLobRepository.findById(defaultPhoto.getId()).get().getBytes();
//            product.addImageToProduct(image2);
//        }
//        if(file3.getSize() != 0)//Если картинка выбрана
//        {
//            bytes3 = file3.getBytes();
//            image3 = toImageEntity(file3);//Сохранение характеристик фото
//            if(product.getImages().size() > 2)//Картинка номер 2 в базе существует(обновляем)
//            {
//                image3.setId(product.getImages().get(2).getId());
//                product.updateImageFromProduct(image3, 2);
//            }
//            else//Картинки номер 2 в базе не существует(добавляем)
//            {
//                product.addImageToProduct(image3);
//            }
//        }
//        else
//        {//Если картинка не выбрана, то сохраняется картинка по умолчанию
//            image3 = getDefaultImage(defaultPhoto);
//            bytes3 = imageLobRepository.findById(defaultPhoto.getId()).get().getBytes();
//            product.addImageToProduct(image3);
//        }
//        Product productFromDb = productRepository.save(product);
//        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
//
//        productRepository.save(product);
//
//        imageLob1 = toImageLobEntity(bytes1, image1.getId());
//        saveLob(imageLob1);
//        imageLob2 = toImageLobEntity(bytes2, image2.getId());
//        saveLob(imageLob2);
//        imageLob3 = toImageLobEntity(bytes3, image3.getId());
//        saveLob(imageLob3);
//    }
//    //Создание image с помощью file
//    private Image toImageEntity(MultipartFile file) throws IOException {
//        Image image = new Image();
//        image.setName(file.getName());
//        image.setOriginalFileName(file.getOriginalFilename());
//        image.setContentType(file.getContentType());
//        image.setSize(file.getSize());
//        //image.setBytes(file.getBytes());
//        return image;
//    }
//    //Создание imageLob с помощью file
//    private ImageLob toImageLobEntity(byte[] bytes, Long id) throws IOException {
//        ImageLob imageLob = new ImageLob();
//        imageLob.setId(id);
//        imageLob.setBytes(bytes);
//        return imageLob;
//    }
    public void delete(long id)
    {
//        if(productCharacteristicRepository.findByProduct_Id(id) != null)
//        {
//            productCharacteristicRepository.deleteAllByProduct(productRepository.getById(id));
//        }
        productRepository.deleteById(id);
    }
    public Page<Product> findPaginated(int pageNo, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return productRepository.findAll(pageable);
    }
    public Page<Product> findPaginatedByProductGroup(int pageNo, int pageSize, ProductGroup productGroup)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return productRepository.findByProductGroup(pageable,productGroup);
    }
}
