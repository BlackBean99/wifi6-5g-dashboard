package com.jnu.wifi6.batch

import com.jnu.wifi6.domain.Clients
import org.springframework.stereotype.Component
import org.springframework.batch.item.ItemWriter
@Component
class ApiItemWriter  : ItemWriter<Clients> {
    override fun write(items: MutableList<out Clients>) {
        // items에 읽어온 데이터가 들어있습니다.
        // 데이터를 원하는 방식으로 처리하고 저장하는 로직을 여기에 구현

    }
}