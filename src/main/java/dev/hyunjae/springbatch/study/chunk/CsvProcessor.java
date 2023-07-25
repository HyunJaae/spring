package dev.hyunjae.springbatch.study.chunk;

import dev.hyunjae.springbatch.study.dto.AdDto;
import dev.hyunjae.springbatch.study.entity.Ad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Configuration
@Transactional
public class CsvProcessor implements ItemProcessor<AdDto, Ad> {
    @Override
    public Ad process(AdDto adDto) {
        return adDto.getStt().equals("COM") ? null : new Ad(adDto);
    }
}
