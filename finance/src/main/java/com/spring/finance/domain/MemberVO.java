package com.spring.finance.domain;

public class MemberVO {
   
   private String M_ID;
   private String M_PW; 
   private String M_NAME; 
   private int M_POINT; 
   private String EMAIL;
   
   
   public String getM_ID() {
      return M_ID;
   }
   public void setM_ID(String m_ID) {
      M_ID = m_ID;
   }
   public String getM_PW() {
      return M_PW;
   }
   public void setM_PW(String m_PW) {
      M_PW = m_PW;
   }
   public String getM_NAME() {
      return M_NAME;
   }
   public void setM_NAME(String m_NAME) {
      M_NAME = m_NAME;
   }
   public int getM_POINT() {
      return M_POINT;
   }
   public void setM_POINT(int m_POINT) {
      M_POINT = m_POINT;
   }
   public String getEMAIL() {
      return EMAIL;
   }
   public void setEMAIL(String eMAIL) {
      EMAIL = eMAIL;
   }
   @Override
   public String toString() {
      return "MemberVO [M_ID=" + M_ID + ", M_PW=" + M_PW + ", M_NAME=" + M_NAME + ", M_POINT=" + M_POINT + ", EMAIL="
            + EMAIL + "]";
   }
   public MemberVO() {
      super();
      // TODO Auto-generated constructor stub
   } 
   
   
}