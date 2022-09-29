package truongpc.springshop.repontory;

import org.springframework.data.jpa.repository.JpaRepository;
import truongpc.springshop.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
