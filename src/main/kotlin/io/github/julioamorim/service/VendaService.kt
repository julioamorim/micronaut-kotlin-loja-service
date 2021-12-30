package io.github.julioamorim.service

import io.github.julioamorim.DTO.input.VendaInput
import io.github.julioamorim.DTO.output.Parcela
import io.github.julioamorim.DTO.output.Venda
import jakarta.inject.Singleton
import java.time.LocalDate

@Singleton
class VendaService(
    private val veiculoService: VeiculoService
) {

    fun realizarVenda(vendaInput: VendaInput) {
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
    }
}