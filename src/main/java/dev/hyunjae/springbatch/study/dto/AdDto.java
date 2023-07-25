package dev.hyunjae.springbatch.study.dto;

import lombok.Data;

@Data
public class AdDto {
    private String basicDate; // 기준날짜
    private Long adId; // 직접광고 ID
    private Long impCnt; // 노출수
    private Long clickCnt; // 클릭수
    private Long impAvg; // 평균 노출수
    private Long clickAvg; // 평균 클릭수
    private Long adSpend; //광고비
    private String stt; // 요청 상태
}
