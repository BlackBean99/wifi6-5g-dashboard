package com.jnu.wifi6.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tbl_clients")
class Clients(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = 0,

    @Column(updatable = true)
    val name:String
){

}