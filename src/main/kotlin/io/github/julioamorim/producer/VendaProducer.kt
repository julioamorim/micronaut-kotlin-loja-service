package io.github.julioamorim.producer

import io.micronaut.configuration.kafka.annotation.KafkaClient
import io.micronaut.configuration.kafka.annotation.KafkaKey
import io.micronaut.configuration.kafka.annotation.Topic


@KafkaClient
interface VendaProducer {

    @Topic("msvendas")
    fun publicarVenda(@KafkaKey id: String, vendaJSON: String)
}