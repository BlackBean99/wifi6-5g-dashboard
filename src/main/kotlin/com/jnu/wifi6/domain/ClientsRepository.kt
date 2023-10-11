package com.jnu.wifi6.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientsRepository : JpaRepository<Clients, Long>
