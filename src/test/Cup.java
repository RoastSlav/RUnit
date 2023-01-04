package test;

public class Cup {
    String liquidType;
    double liquidAmount;

    public Cup(String liquidType, double liquidAmount) {
        this.liquidType = liquidType;
        this.liquidAmount = liquidAmount;
    }

    public void setLiquidType(String liquidType) {
        this.liquidType = liquidType;
    }

    public void setLiquidAmount(double liquidAmount) {
        this.liquidAmount = liquidAmount;
    }

    public String getLiquidType() {
        return liquidType;
    }

    public double getLiquidAmount() {
        return liquidAmount;
    }
}
