package dev.hyunjae.springbatch.study.chunk;

import dev.hyunjae.springbatch.study.entity.Ad;
import dev.hyunjae.springbatch.study.repository.AdRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CsvWriter implements ItemWriter<Ad> {

    private final AdRepository adRepository;

    public CsvWriter(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    @Override
    public void write(List<? extends Ad> list) {
        adRepository.saveAll(new ArrayList<Ad>(list));
    }
}
