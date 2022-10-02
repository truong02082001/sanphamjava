package truongpc.springshop.repontory;

import org.springframework.data.jpa.repository.JpaRepository;
import truongpc.springshop.entity.BillItems;

public interface BillItemsRepo extends JpaRepository<BillItems, Integer> {
}
