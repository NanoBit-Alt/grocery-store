package com.market.app.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name = "order_table")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonProperty("user_id")
    private long userId;

    @JoinColumn(name = "item_ordered_id")
    @JsonProperty("item_ordered_id")
    private long itemOrderedId;

    private String location;

    @JsonProperty("shipping_details")
    private String shippingDetails;

}