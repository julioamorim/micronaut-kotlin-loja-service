package io.github.julioamorim.service

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.julioamorim.DTO.input.VendaInput
import io.github.julioamorim.DTO.output.Parcela
import io.github.julioamorim.DTO.output.Venda
import io.github.julioamorim.producer.VendaProducer
import jakarta.inject.Singleton
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

@Singleton
class VendaService(
    private val veiculoService: VeiculoService,
    private val vendaProducer: VendaProducer,
    private val objectMapper: ObjectMapper
) {

    fun realizarVenda(vendaInput: VendaInput): Venda {
        val veiculo = veiculoService.getVeiculo(vendaInput.veiculo)
        var parcelas: List<Parcela> = ArrayList<Parcela>()
        var valorParcela = vendaInput.valor.divide(vendaInput.quantidadeParcelas.toBigDecimal())
        var dataVencimento = LocalDate.now().plusMonths(1)

        for (i in 1..vendaInput.quantidadeParcelas) {
            var parcela = Parcela(valorParcela, dataVencimento.toString())
            parcelas = parcelas.plus(parcela)
            dataVencimento = dataVencimento.plusMonths(1)
        }

        var venda = Venda(vendaInput.cliente, veiculo, vendaInput.valor, parcelas)

        print(venda)
        confirmarVenda(venda)

        return venda
    }

    fun confirmarVenda(venda: Venda) {
        var vendaJSON = objectMapper.writeValueAsString(venda)
        vendaProducer.publicarVenda(UUID.randomUUID().toString(), vendaJSON)
    }
}