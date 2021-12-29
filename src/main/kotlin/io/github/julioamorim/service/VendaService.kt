package io.github.julioamorim.service

import io.github.julioamorim.DTO.input.VendaInput
import io.github.julioamorim.http.VeiculoHttp
import jakarta.inject.Singleton

@Singleton
class VendaService(
    private val veiculoHttp: VeiculoHttp
) {

    fun realizarVenda(vendaInput: VendaInput) {
        val veiculo = veiculoHttp.findById(vendaInput.veiculo)
        print(veiculo)
    }
}