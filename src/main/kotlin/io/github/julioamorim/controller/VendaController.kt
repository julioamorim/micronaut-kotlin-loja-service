package io.github.julioamorim.controller

import io.github.julioamorim.DTO.input.VendaInput
import io.github.julioamorim.service.VendaService
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/vendas")
class VendaController(
    val vendaService: VendaService
) {

    @Post
    fun realizarVenda(@Body vendaInput: VendaInput) {
        vendaService.realizarVenda(vendaInput)
    }
}