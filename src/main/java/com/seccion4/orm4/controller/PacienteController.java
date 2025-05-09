package com.seccion4.orm4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seccion4.orm4.model.Paciente;
import com.seccion4.orm4.service.PacienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {
    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> getPacientes() {
        List<Paciente> pacientes = pacienteService.findAll();
        if (!pacientes.isEmpty()) {
            return new ResponseEntity<>(pacientes, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PostMapping
    public ResponseEntity<Paciente> postPaciente(@RequestBody Paciente paciente) {
        Paciente buscado = pacienteService.findxId(paciente.getId());
        if (buscado == null) {
            return new ResponseEntity<>(pacienteService.save(paciente), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
        }

    }

}
