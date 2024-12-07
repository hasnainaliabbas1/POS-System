package model;




/*
public class Product {
    private int id;
    private String name;
    private String category;
    private double originalPrice;
    private double salePrice;
    private double pricePerUnit;
    private double pricePerCarton;
    private int vendorId;
    private int stockQuantity;
      public Product(){

      }

    public Product(int vendorId, String name, String category, double originalPrice, double salePrice, double pricePerUnit, double pricePerCarton) {
        this.vendorId = vendorId;
        this.name = name;
        this.category = category;
        this.originalPrice = originalPrice;
        this.salePrice = salePrice;
        this.pricePerUnit = pricePerUnit;
        this.pricePerCarton = pricePerCarton;
    }



    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for category
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Getter and Setter for originalPrice
    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    // Getter and Setter for salePrice
    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    // Getter and Setter for pricePerUnit
    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    // Getter and Setter for pricePerCarton
    public double getPricePerCarton() {
        return pricePerCarton;
    }

    public void setPricePerCarton(double pricePerCarton) {
        this.pricePerCarton = pricePerCarton;
    }

    // Getter and Setter for vendorId
    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

}




 */

















public class Product {
    private int id;
    private String name;
    private String category;
    private double originalPrice;
    private double salePrice;
    private double pricePerUnit;
    private double pricePerCarton;
    private int vendorId;
    private int stockQuantity;

    public Product() {
    }

    public Product(int vendorId, String name, String category, double originalPrice, double salePrice, double pricePerUnit, double pricePerCarton, int stockQuantity) {
        this.vendorId = vendorId;
        this.name = name;
        this.category = category;
        this.originalPrice = originalPrice;
        this.salePrice = salePrice;
        this.pricePerUnit = pricePerUnit;
        this.pricePerCarton = pricePerCarton;
        this.stockQuantity = stockQuantity; // Initialize stockQuantity
    }



    // Getter and Setter for id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for category
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Getter and Setter for originalPrice
    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    // Getter and Setter for salePrice
    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    // Getter and Setter for pricePerUnit
    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    // Getter and Setter for pricePerCarton
    public double getPricePerCarton() {
        return pricePerCarton;
    }

    public void setPricePerCarton(double pricePerCarton) {
        this.pricePerCarton = pricePerCarton;
    }

    // Getter and Setter for vendorId
    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    // Getter and Setter for stockQuantity
    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity; // Add this setter
    }
}





















