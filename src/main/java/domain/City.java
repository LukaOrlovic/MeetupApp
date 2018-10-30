/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author orlov
 */
public class City {
    
    private int serialNumber;
    private String name;

    public City() {
    }

    public City(int redniBroj, String naziv) {
        this.serialNumber = redniBroj;
        this.name = naziv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }
    
    
}
