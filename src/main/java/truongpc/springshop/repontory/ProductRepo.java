package truongpc.springshop.repontory;

import jdk.jfr.Registered;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import truongpc.springshop.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
