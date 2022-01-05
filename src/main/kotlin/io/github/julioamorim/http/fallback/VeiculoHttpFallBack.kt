package io.github.julioamorim.http.fallback

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.julioamorim.DTO.output.Veiculo
import io.github.julioamorim.http.VeiculoHttp
import io.micronaut.retry.annotation.Fallback
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig

@Fallback
class VeiculoHttpFallBack(
    private val objectMapper: ObjectMapper
) : VeiculoHttp {
    override fun findById(id: Long): Veiculo {
        val jedisPool = JedisPool(JedisPoolConfig(), "127.0.0.1", 6379)
        val jedis = jedisPool.resource
        val veiculoJSON = jedis.get(id.toString())
        val veiculo = objectMapper.readValue(veiculoJSON, Veiculo::class.java)
        return veiculo
    }

}