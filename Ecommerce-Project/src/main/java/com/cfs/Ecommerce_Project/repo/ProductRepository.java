package com.cfs.Ecommerce_Project.repo;

import com.cfs.Ecommerce_Project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
