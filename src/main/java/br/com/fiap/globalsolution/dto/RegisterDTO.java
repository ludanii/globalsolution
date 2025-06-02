package br.com.fiap.globalsolution.dto;

import br.com.fiap.globalsolution.model.UserRole;

public record RegisterDTO(
        String username,
        String password,
        UserRole role
) {
}
