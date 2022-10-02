package truongpc.springshop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class BillItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private double byPrice;
    @ManyToOne
    @CollectionTable(name = "bill_id")
    Bill bill;
    @ManyToOne
    @CollectionTable(name = "product_id")
    Product product;
}
