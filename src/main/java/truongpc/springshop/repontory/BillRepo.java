package truongpc.springshop.repontory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import truongpc.springshop.entity.Bill;

public interface BillRepo extends JpaRepository<Bill, Integer> {

}
