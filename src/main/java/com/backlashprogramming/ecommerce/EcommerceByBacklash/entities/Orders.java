package com.backlashprogramming.ecommerce.EcommerceByBacklash.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private LocalUser user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.REMOVE, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<OrderQuantities> quantities = new ArrayList<>();


    public Orders() {
    }

    public Orders(Long id, LocalUser user, Address address, List<OrderQuantities> quantities) {
        this.id = id;
        this.user = user;
        this.address = address;
        this.quantities = quantities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalUser getUser() {
        return user;
    }

    public void setUser(LocalUser user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<OrderQuantities> getQuantities() {
        return quantities;
    }

    public void setQuantities(List<OrderQuantities> quantities) {
        this.quantities = quantities;
    }

}