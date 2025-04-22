package com.luciana.challenge_factorIT.controllers;

import com.luciana.challenge_factorIT.dtos.responses.VipResponseDTO;
import com.luciana.challenge_factorIT.entities.Vip;
import com.luciana.challenge_factorIT.services.CartService;
import com.luciana.challenge_factorIT.services.UserService;
import com.luciana.challenge_factorIT.services.VipService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;
    private final CartService cartService;
    private final VipService vipService;

    public UserController(UserService userService, CartService cartService, VipService vipService) {
        this.userService = userService;
        this.cartService = cartService;
        this.vipService = vipService;
    }
   // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/vip")

    public ResponseEntity<List<VipResponseDTO>> getVipUsers() {
        try {
            List<VipResponseDTO> users = userService.getVipsUsers();
            return new ResponseEntity<List<VipResponseDTO>>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vip/by-month")
    public ResponseEntity<List<VipResponseDTO>> getVipUsersByMonth(@RequestParam @Valid boolean active,
                                                                   @RequestParam @Valid int year,
                                                                   @RequestParam @Valid int month
    ) {
        try {
            List<Vip> vips = vipService.findByActiveAndDate(active, year, month);
            List<VipResponseDTO> users = vips.stream()
                    .map(vip -> userService.mapToDTO(vip))
                    .toList();

            return new ResponseEntity<List<VipResponseDTO>>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/vip/update")
    @Operation(
            summary = "Simula la ejecución de un 'job' para actualizar los usuarios VIP.",
            description = "Este endpoint auxiliar permite simular la ejecución de un 'job', que se ejecutaría el 01 de " +
                    "cada mes, necesario para actualizar el rol de los usuarios. Si los usuarios superan en el mes " +
                    "los $10.000,00 en compras, al mes siguiente se convierten en VIP, pero si son VIP y no cumplen " +
                    "con esta condición el mes siguiente dejan de ser VIP. El endponit no requiere autorización por token."
    )
    public void updateVipStatusForMonth() {
        cartService.updateVipStatusForMonth();
    }

    @PostMapping("/create")
    @Operation(
            summary = "Crea 6 usuarios auxiliares hardcodeados.",
            description = "Este endpoint auxiliar permite cargar usuarios en la base de datos con la password encriptada, no requiere autorización por token."
    )
    public void createUsersAux () {
        userService.createUsers();
    }
}
