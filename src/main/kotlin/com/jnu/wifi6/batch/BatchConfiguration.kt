package com.jnu.wifi6.batch

import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class MyBatchConfig(
    private val entityManagerFactory: EntityManagerFactory,
): DefaultBatchConfiguration() {
    companion object { const val chuckSize = 3 }

    @Bean
    fun myJob(jobRepository: JobRepository, step: Step): Job {
        return JobBuilder("myJob", jobRepository)
            .start(step)
            .build()
    }

    @Bean
    fun myStep(jobRepository: JobRepository, transactionManager: PlatformTransactionManager, entityManagerFactory: EntityManagerFactory): Step {
        return StepBuilder("myStep", jobRepository)
            .chunk<Member, String>(chuckSize, transactionManager)
            // chunk 단위만큼 데이터가 쌓이면 writer에 전달하고, writer는 저장
            // 마지막 chunk에서는 사이즈 만큼 안채워져도 실행됨
            .reader(reader(null))
            .processor(processor(null))
            .writer(writer(null))
            .build()
    }

    @Bean
    @StepScope // Bean의 생성 시점이 스프링 애플리케이션이 실행되는 시점이 아닌 @JobScope, @StepScope가 명시된 메서드가 실행될 때까지 지연
    fun reader(@Value("#{jobParameters[requestDate]}") requestDate: String?): JpaPagingItemReader<Member> {
        println("==> reader: $requestDate")
        return JpaPagingItemReaderBuilder<Member>()
            .name("reader")
            .entityManagerFactory(entityManagerFactory)
            .pageSize(chuckSize)
            .queryString("SELECT m FROM Member m")
            .build()
    }

    @Bean
    @StepScope
    fun processor(@Value("#{jobParameters[requestDate]}") requestDate: String?): ItemProcessor <Member, String> {
        println("==> processor: $requestDate")
        return ItemProcessor<Member, String> { item: Member ->
            item.name
        }
    }

    @Bean
    @StepScope
    fun writer(@Value("#{jobParameters[requestDate]}") requestDate: String?): ItemWriter<String> {
        println("==> writer: $requestDate")
        return ItemWriter<String> { items ->
            for (item in items) {
                println("name: $item")
            }
        }
    }

}