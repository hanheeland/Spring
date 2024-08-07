package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// VO 객체 vs DTO 객체
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SampleDTO {
   private String name;
   private int age;
}
