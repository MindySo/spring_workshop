package com.ssafy.exam.model.dto;

public class FoodNutri {

    private double energy_Qy;         // 에너지 (kcal)
    private double prot_Qy;           // 단백질 (g)
    private double ntrfs_Qy;          // 지질/지방 (g)
    private double carbohydrate_Qy;   // 탄수화물 (g)
    private double na_Qy;             // 나트륨 (mg)
    private double ptss_Qy;           // 칼륨 (mg)
    private double clci_Qy;           // 칼슘 (mg)
    private double irn_Qy;            // 철 (mg)
    private double vtmn_C_Qy;         // 비타민C (mg)
    private double vtmn_B1_Qy;        // 비타민B1 (mg)

    // 기본 생성자
    public FoodNutri() {}

    // getter & setter
    public double getEnergy_Qy() {
        return energy_Qy;
    }

    public void setEnergy_Qy(double energy_Qy) {
        this.energy_Qy = energy_Qy;
    }

    public double getProt_Qy() {
        return prot_Qy;
    }

    public void setProt_Qy(double prot_Qy) {
        this.prot_Qy = prot_Qy;
    }

    public double getNtrfs_Qy() {
        return ntrfs_Qy;
    }

    public void setNtrfs_Qy(double ntrfs_Qy) {
        this.ntrfs_Qy = ntrfs_Qy;
    }

    public double getCarbohydrate_Qy() {
        return carbohydrate_Qy;
    }

    public void setCarbohydrate_Qy(double carbohydrate_Qy) {
        this.carbohydrate_Qy = carbohydrate_Qy;
    }

    public double getNa_Qy() {
        return na_Qy;
    }

    public void setNa_Qy(double na_Qy) {
        this.na_Qy = na_Qy;
    }

    public double getPtss_Qy() {
        return ptss_Qy;
    }

    public void setPtss_Qy(double ptss_Qy) {
        this.ptss_Qy = ptss_Qy;
    }

    public double getClci_Qy() {
        return clci_Qy;
    }

    public void setClci_Qy(double clci_Qy) {
        this.clci_Qy = clci_Qy;
    }

    public double getIrn_Qy() {
        return irn_Qy;
    }

    public void setIrn_Qy(double irn_Qy) {
        this.irn_Qy = irn_Qy;
    }

    public double getVtmn_C_Qy() {
        return vtmn_C_Qy;
    }

    public void setVtmn_C_Qy(double vtmn_C_Qy) {
        this.vtmn_C_Qy = vtmn_C_Qy;
    }

    public double getVtmn_B1_Qy() {
        return vtmn_B1_Qy;
    }

    public void setVtmn_B1_Qy(double vtmn_B1_Qy) {
        this.vtmn_B1_Qy = vtmn_B1_Qy;
    }

	@Override
	public String toString() {
		return "NutritionDto [energy_Qy=" + energy_Qy + ", prot_Qy=" + prot_Qy + ", ntrfs_Qy=" + ntrfs_Qy
				+ ", carbohydrate_Qy=" + carbohydrate_Qy + ", na_Qy=" + na_Qy + ", ptss_Qy=" + ptss_Qy + ", clci_Qy="
				+ clci_Qy + ", irn_Qy=" + irn_Qy + ", vtmn_C_Qy=" + vtmn_C_Qy + ", vtmn_B1_Qy=" + vtmn_B1_Qy + "]";
	}
    
}
