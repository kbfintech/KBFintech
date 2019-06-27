package com.spring.finance.domain;

import lombok.Data;

@Data
public class MemberVO {
   
   private String M_ID;
   private String M_PW; 
   private String M_NAME; 
   private int M_POINT; 
   private String EMAIL;
   private String PHONE;
   private String PR_REG_IDX;
   private int OPENPAGE;
   
}