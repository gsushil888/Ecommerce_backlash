package com.backlashprogramming.ecommerce.EcommerceByBacklash.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "order_quantities")
public class OrderQuantities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name = "orders_id", nullable = false)
    private Orders orders;

    public OrderQuantities() {
    }

    public OrderQuantities(Long id, Product product, Integer quantity, Orders orders) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrderQuantities{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ", orders=" + orders +
                '}';
    }
}