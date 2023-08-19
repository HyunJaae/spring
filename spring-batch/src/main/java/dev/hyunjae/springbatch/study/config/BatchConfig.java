package dev.hyunjae.springbatch.study.config;

import dev.hyunjae.springbatch.study.chunk.CsvProcessor;
import dev.hyunjae.springbatch.study.chunk.CsvReader;
import dev.hyunjae.springbatch.study.chunk.CsvWriter;
import dev.hyunjae.springbatch.study.dto.AdDto;
import dev.hyunjae.springbatch.study.entity.Ad;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class BatchConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CsvReader csvReader;
    private final CsvProcessor csvProcessor;
    private final CsvWriter csvWriter;

    private static final int CHUNK_SIZE = 10;

    @Bean
    public Job csvFileItemReaderJob() throws MalformedURLException {
        return jobBuilderFactory.get("csvFileItemReaderJob")
                .start(csvFileItemReaderStep(null))
                .build();
    }

    @Bean
    @JobScope
    public Step csvFileItemReaderStep(@Value("#{jobParameters[requestDate]}") String requestDate) throws MalformedURLException {
        return stepBuilderFactory.get("csvFileItemReaderStep")
                .<AdDto, Ad>chunk(CHUNK_SIZE)
                .reader(csvReader.csvFileItemReader())
                .processor(csvProcessor)
                .writer(csvWriter)
                .build();
    }
}
