package com.tm.tsmatelier.controller.v1

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/test")
class TestSecurityController {
    /**
     * Endpoint 1: Totalmente público.
     * Qualquer pessoa, autenticada ou não, pode acessar.
     */
    @GetMapping("/public")
    fun getPublicResource(): ResponseEntity<String> = ResponseEntity.ok("Este é um endpoint público e acessível a todos!")

    /**
     * Endpoint 2: Protegido para usuários com a role 'CUSTOMER'.
     * A anotação @PreAuthorize verifica a permissão ANTES de executar o método.
     * @AuthenticationPrincipal injeta os detalhes do usuário autenticado no método.
     */
    @GetMapping("/user")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    fun getUserResource(
        @AuthenticationPrincipal userDetails: UserDetails,
    ): ResponseEntity<String> = ResponseEntity.ok("Olá, ${userDetails.username}! Você tem acesso ao recurso de USUÁRIO.")

    /**
     * Endpoint 3: Protegido para usuários com a role 'ADMIN'.
     * Somente usuários com a autoridade 'ADMIN' podem acessar.
     */
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    fun getAdminResource(
        @AuthenticationPrincipal userDetails: UserDetails,
    ): ResponseEntity<String> = ResponseEntity.ok("Olá, admin ${userDetails.username}! Você tem acesso ao recurso de ADMINISTRADOR.")
}
