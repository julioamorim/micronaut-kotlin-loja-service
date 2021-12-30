package io.github.julioamorim.service

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.julioamorim.DTO.output.Veiculo
import io.github.julioamorim.http.VeiculoHttp
import jakarta.inject.Singleton
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

@Singleton
class VeiculoService(
    private val veiculoHttp: VeiculoHttp,
    private val objectMapper: ObjectMapper
) {

    fun getVeiculo(id: Long): Veiculo {
        val veiculo = veiculoHttp.findById(id)
        gravarCache(veiculo)
        return veiculo
    }

    fun gravarCache(veiculo: Veiculo) {
        val jedisPool = JedisPool(JedisPoolConfig(), "127.0.0.1", 6379)
        val jedis = jedisPool.resource
        var veiculoJson = objectMapper.writeValueAsString(veiculo)
        jedis.set(veiculo.id.toString(), veiculoJson)

    }
}