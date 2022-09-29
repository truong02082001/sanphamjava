package truongpc.springshop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private String description;
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

}
