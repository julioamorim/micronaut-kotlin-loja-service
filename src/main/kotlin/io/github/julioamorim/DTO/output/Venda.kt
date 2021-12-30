package io.github.julioamorim.DTO.output

import java.math.BigDecimal

data class Venda(
    val cliente: String,
    val veiculo: Veiculo,
    val venda:BigDecimal,
    val parcelas: List<Parcela>
)
