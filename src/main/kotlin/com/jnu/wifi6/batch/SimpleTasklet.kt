package com.jnu.wifi6.batch

import org.slf4j.LoggerFactory
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.stereotype.Component

@Component
@StepScope
class SimpleTasklet : Tasklet {
    val logger = LoggerFactory.getLogger(this.javaClass.simpleName)
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        logger.info("SimpleTasklet execute")
        return RepeatStatus.FINISHED
    }
}