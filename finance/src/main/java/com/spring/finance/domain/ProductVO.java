package com.spring.finance.domain;

import lombok.Data;

@Data
public class ProductVO {

   private String M_ID;
   private String PRD_ID;
   private String PRD_INFO;
   private double PRD_TRANSFER;
   private String PRD_NAME;
   private String PRD_ACC;
   private String PRD_REG_DT;
   
   public String getM_ID() {
      return M_ID;
   }
   public void setM_ID(String m_ID) {
      M_ID = m_ID;
   }
   public ProductVO(String m_ID, String pRD_ID, String pRD_INFO, double pRD_TRANSFER, String pRD_NAME, String pRD_ACC,
         String pRD_REG_DT) {
      super();
      M_ID = m_ID;
      PRD_ID = pRD_ID;
      PRD_INFO = pRD_INFO;
      PRD_TRANSFER = pRD_TRANSFER;
      PRD_NAME = pRD_NAME;
      PRD_ACC = pRD_ACC;
      PRD_REG_DT = pRD_REG_DT;
   }
   public String getPRD_ID() {
      return PRD_ID;
   }
   public void setPRD_ID(String pRD_ID) {
      PRD_ID = pRD_ID;
   }
   public String getPRD_INFO() {
      return PRD_INFO;
   }
   public void setPRD_INFO(String pRD_INFO) {
      PRD_INFO = pRD_INFO;
   }
   public double getPRD_TRANSFER() {
      return PRD_TRANSFER;
   }
   public void setPRD_TRANSFER(double pRD_TRANSFER) {
      PRD_TRANSFER = pRD_TRANSFER;
   }
   public String getPRD_NAME() {
      return PRD_NAME;
   }
   public void setPRD_NAME(String pRD_NAME) {
      PRD_NAME = pRD_NAME;
   }
   public String getPRD_ACC() {
      return PRD_ACC;
   }
   public void setPRD_ACC(String pRD_ACC) {
      PRD_ACC = pRD_ACC;
   }
   public String getPRD_REG_DT() {
      return PRD_REG_DT;
   }
   public void setPRD_REG_DT(String pRD_REG_DT) {
      PRD_REG_DT = pRD_REG_DT;
   }
   public ProductVO() {
      super();
      // TODO Auto-generated constructor stub
   }
   
   @Override
   public String toString() {
      return "ProductVO [M_ID=" + M_ID + ", PRD_ID=" + PRD_ID + ", PRD_INFO=" + PRD_INFO + ", PRD_TRANSFER="
            + PRD_TRANSFER + ", PRD_NAME=" + PRD_NAME + ", PRD_ACC=" + PRD_ACC + ", PRD_REG_DT=" + PRD_REG_DT + "]";
   }

}