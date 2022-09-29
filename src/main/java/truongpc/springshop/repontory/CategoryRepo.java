package truongpc.springshop.repontory;

import org.springframework.data.jpa.repository.JpaRepository;
import truongpc.springshop.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
