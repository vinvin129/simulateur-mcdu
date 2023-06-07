package fr.ptut2022.simulateurmcdu.mcdu.rest;

import fr.ptut2022.simulateurmcdu.mcdu.Mcdu;
import fr.ptut2022.simulateurmcdu.mcdu.models.Donnee;
import fr.ptut2022.simulateurmcdu.mcdu.models.LSKKey;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/setting/emit/")
public class SettingRestController {

    /**
     * Endpoint permettant de récupérer l'état de connexion du mcdu.
     * @return True si le mcdu est connecté, false sinon.
     */
    @Tag(name = "Générales", description = "Informations générales")
    @Operation(
            summary = "Etat de connexion du MCDU",
            description = "Endpoint permettant de récupérer l'état de connexion du mcdu.")
    @ApiResponse(content = { @Content(schema = @Schema(implementation = Boolean.class), mediaType = "application/json") },
            description = "True si le mcdu est connecté, false sinon.")
    @GetMapping("mcduConnected")
    public boolean mcduConnected() {
        return Mcdu.INSTANCE.isConnected();
    }

    /**
     * Endpoint permettant de récupérer la liste des valeurs en fonction des touches LSK.
     * @return Une map de valeur en fonction des touches lsk
     */
    @Tag(name = "Mapping", description = "Manipulation et récupération des valeurs des touches LSK")
    @Operation(
            summary = "Recupérer la liste des valeurs des touches LSK",
            description = "Endpoint permettant de récupérer la liste des valeurs en fonction des touches LSK.")
    @ApiResponse(content = { @Content(mediaType = "application/json") },
            description = "Une map de valeur en fonction des touches lsk.")
    @GetMapping("mapping")
    public Map<LSKKey, Donnee> getMapping() {
        return Mcdu.INSTANCE.getMapping();
    }

    /**
     * Endpoint permettant de récupérer la valeur d'une touche LSK.
     * @param key la touche LSK.
     * @return La donnée liée à la touche LSK.
     */
    @Tag(name = "Mapping")
    @Operation(
            summary = "Récupérer la valeur d'une touche LSK",
            description = "Endpoint permettant de récupérer la valeur d'une touche LSK.")
    @ApiResponse(content = { @Content(schema = @Schema(implementation = Donnee.class), mediaType = "application/json") },
            description = "La donnée liée à la touche LSK.")
    @GetMapping("mapping/{key}")
    public Donnee getMapping(@PathVariable("key") LSKKey key) {
        return Mcdu.INSTANCE.getActualDonnee(key).orElse(null);
    }

    /**
     * Endpoint permettant de modifier toute la liste des valeurs en fonction des touches LSK.
     * @param mapping La liste des valeurs en fonction des touches LSK.
     * @return Un message de mise à jour.
     */
    @Tag(name = "Mapping")
    @Operation(
            summary = "Modifier toutes les valeurs en fonction des touches LSK",
            description = "Endpoint permettant de modifier toute la liste des valeurs en fonction des touches LSK.")
    @ApiResponse(content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") },
            description = "Un message de mise à jour.")
    @PostMapping(value = "mapping", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateMapping(@RequestBody Map<LSKKey, Donnee> mapping) {
        mapping.forEach(Mcdu.INSTANCE::changementMapping);
        return ResponseEntity.ok("Mcdu updated");
    }

    /**
     * Endpoint permettant de modifier la valeur d'une touche LSK.
     * @param donnee La donnée correspondant à la touche LSK.
     * @param key La touche LSK.
     * @return Message de confirmation.
     */
    @Tag(name = "Mapping")
    @Operation(
            summary = "Modifier la valeur d'une touche LSK",
            description = "Endpoint permettant de modifier la valeur d'une touche LSK.")
    @ApiResponse(content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") },
            description = "Message de confirmation.")
    @PostMapping(value = "mapping/{key}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateMapping(@RequestBody Donnee donnee, @PathVariable("key") LSKKey key) {
        Mcdu.INSTANCE.changementMapping(key, donnee);
        return ResponseEntity.ok("key " + key + " updated");
    }
}
