package dev.hyunjae.springbatch.study.entity;

import dev.hyunjae.springbatch.study.dto.AdDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // pk

    private String basicDate; // 기준날짜
    private Long adId; // 직접광고 ID
    private Long impCnt; // 노출수
    private Long clickCnt; // 클릭수
    private Long impAvg; // 평균 노출수
    private Long clickAvg; // 평균 클릭수
    private Long adSpend; //광고비
    private String stt; // 요청 상태

    public Ad(AdDto adDto) {
         this.basicDate = adDto.getBasicDate();
         this.adId = adDto.getAdId();
         this.impCnt = adDto.getImpCnt();
         this.clickCnt = adDto.getClickCnt();
         this.impAvg = adDto.getImpAvg();
         this.clickAvg = adDto.getClickAvg();
         this.adSpend = adDto.getAdSpend();
         this.stt = adDto.getStt();
    }
}
