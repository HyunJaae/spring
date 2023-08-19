package dev.hyunjae.springbatch.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CustomExitCodeJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job customExitCodeJob() {
        return jobBuilderFactory.get("customExitCodeJob")
                .start(customExitCodeStep1())
                    .on("FAILED")
                    .end()
                .from(customExitCodeStep1())
                    .on("COMPLETED WITH SKIPS")
                    .to(errorPrint1())
                    .on("*")
                    .end()
                .from(customExitCodeStep1())
                    .on("*")
                    .to(customExitCodeStep2())
                    .on("*")
                    .end()
                .end().build();
    }

    @Bean
    public Step customExitCodeStep1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> This is Step1");
                    contribution.setExitStatus(new ExitStatus("COMPLETED WITH SKIPS"));
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step customExitCodeStep2() {
        return stepBuilderFactory.get("step2")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>> This is Step2");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step errorPrint1() {
        return stepBuilderFactory.get("errorStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info(">>>>>> SKIPS errorStep");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
