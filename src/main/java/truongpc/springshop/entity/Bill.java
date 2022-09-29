package truongpc.springshop.entity;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.DATE)
    private Date byDate;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
