package fr.ludovicanselin.immopredict_application_api.controller;

import fr.ludovicanselin.immopredict_application_api.model.ApiPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class VersionController {

    @GetMapping("/version")
    public ResponseEntity<ApiPayload> getVersion() {
        ApiPayload payload = new ApiPayload();
        payload.setData(null);
        payload.setMessage("Version 1.0.0-DEV");

        return new ResponseEntity<>(payload, HttpStatus.OK);
    }

}