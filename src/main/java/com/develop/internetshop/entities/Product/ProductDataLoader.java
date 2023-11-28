package com.develop.internetshop.entities.Product;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.develop.internetshop.entities.Category.Category;
import com.develop.internetshop.entities.Category.CategoryRepository;

import jakarta.annotation.PostConstruct;

/**
 * ProductDataLoader
 */
@Component
public class ProductDataLoader {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    
    public ProductDataLoader(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @PostConstruct
    private void loadData() {
        productRepository.saveAll(
            List.of(
                new Product(
                    null, "MacBook Air", 
                    "Ноутбуки Apple MacBook (Эппл Макбук) купить в интернет магазине ТехноБазар. Ноутбуки Apple MacBook (Эппл Макбук) цены, большой каталог, новинки", 
                    "macbook-air-m2", 
                    "Ноутбук Apple MacBook Air в тонком и легком корпусе из алюминия золотистого цвета – стильный и надежный помощник для решения разных задач. Он оснащен процессором Apple M1 с 8 ядрами и встроенным графическим ускорителем, 8 ГБ памяти ОЗУ, накопителем SSD на 256 ГБ для обеспечения быстродействия системы. Работает ноутбук под управлением ОС macOS.\r\n" + //
                            "В Apple MacBook Air установлен экран 13.3 дюйма с панелью IPS (2560x1600 пикселей), который впечатляет изображением с красочностью и четкостью деталей. Тихая клавиатура с белой подсветкой LED и эргономичной раскладкой позволяет с удобством работать в программах. Акустическая система со стереодинамиками воспроизводит чистый насыщенный звук. С боковых сторон расположены порты USB Type-A, USB Type-C, Thunderbolt 3. Кроме этого, реализованы средства беспроводного подключения Wi-Fi и Bluetooth. Одного заряда аккумулятора хватает для 18 часов автономности мобильного компьютера.", 
                    
                    categoryRepository.findById(1l).get(), 
                    "320", 
                    100.000f, 
                    10f, 
                    new Date(), 
                    new Date(), 
                    new Date(), 
                    new Date(), 
                    new Date()
                )
            )
        );
    }
}
