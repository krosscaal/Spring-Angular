/*
 * Krossby Adhemar Camacho Alviz
 *
 */

package br.com.krossft.SpringAngular.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public ResponseEntity<String> hello() {
    return ResponseEntity.ok().body("Olá teste de requisição");
  }

}
