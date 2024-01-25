package com.cydeo.POJO;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Fruit {

    private String name;
    private  double price;
    /**
     * price : 3.49
     */

    private double priceX;

    public double getPriceX() {
        return priceX;
    }

    public void setPriceX(double priceX) {
        this.priceX = priceX;
    }

    // we need to use getters and setters


}
