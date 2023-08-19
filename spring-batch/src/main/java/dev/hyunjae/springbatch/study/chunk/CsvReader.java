package dev.hyunjae.springbatch.study.chunk;

import dev.hyunjae.springbatch.study.dto.AdDto;
import dev.hyunjae.springbatch.study.entity.Ad;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileUrlResource;

import java.net.MalformedURLException;

@Configuration
public class CsvReader {
    @Bean
    public FlatFileItemReader<AdDto> csvFileItemReader() throws MalformedURLException {
        /* file read */
        FlatFileItemReader<AdDto> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setResource(new FileUrlResource("/Users/devlee/Downloads/test.csv"));
        flatFileItemReader.setLinesToSkip(1); // header line skip
        flatFileItemReader.setEncoding("UTF-8"); // encoding

        /* read 하는 데이터를 내부적으로 LineMapper 을 통해 Mapping */
        DefaultLineMapper<AdDto> defaultLineMapper = new DefaultLineMapper<>();

        /* delimitedLineTokenizer : setNames 를 통해 각각의 데이터의 이름 설정 */
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(",");
        delimitedLineTokenizer.setNames("basicDate", "adId", "impCnt", "clickCnt", "impAvg", "clickAvg", "adSpend", "stt");
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);

        /* beanWrapperFieldSetMapper : Tokenizer 에서 가지고온 데이터들을 VO로 바인드하는 역할 */
        BeanWrapperFieldSetMapper<AdDto> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(AdDto.class);

        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        /* lineMapper 지정 */
        flatFileItemReader.setLineMapper(defaultLineMapper);

        return flatFileItemReader;
    }
}
