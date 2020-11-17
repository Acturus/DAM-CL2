package com.cibertec.damcl2.Helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class UserRow {
    private String nombre;
    private String aparteno;
    private String amaterno;
    private String email;
    private String celular;
    private String fechaNac;
    private String ubicacion;
    private String fechaReg;
}
