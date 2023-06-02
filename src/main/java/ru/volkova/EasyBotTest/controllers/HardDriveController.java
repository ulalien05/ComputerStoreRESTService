package ru.volkova.EasyBotTest.controllers;

import ru.volkova.EasyBotTest.dto.HardDriveDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ru.volkova.EasyBotTest.models.HardDrive;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.volkova.EasyBotTest.services.HardDriveService;
import ru.volkova.EasyBotTest.util.ItemErrorResponse;
import ru.volkova.EasyBotTest.util.ItemNotCreatedException;
import ru.volkova.EasyBotTest.util.ItemNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hard_drive")
@RequiredArgsConstructor
public class HardDriveController {

    private final HardDriveService hardDriveService;

    private final ModelMapper modelMapper;

    @GetMapping()
    public List<HardDriveDTO> getAllHardDrives(){
        return hardDriveService.findAll().stream()
                .map(this::convertToHardDriveDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public HardDriveDTO getHardDrive(@PathVariable("id") Integer id){
        return convertToHardDriveDTO(hardDriveService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid HardDriveDTO hardDriveDTO,
                                             BindingResult bindingResult){
        checkForErrors(bindingResult);
        hardDriveService.save(convertToHardDrive(hardDriveDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid HardDriveDTO hardDriveDTO,
                                             @PathVariable("id") Integer id,
                                             BindingResult bindingResult){
        checkForErrors(bindingResult);
        hardDriveService.update(id, convertToHardDrive(hardDriveDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private void checkForErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            errors.forEach(err -> errorMessage.append(err.getField()).append(" - ")
                    .append(err.getDefaultMessage()).append("\n"));
            throw new ItemNotCreatedException(errorMessage.toString());
        }
    }


    @ExceptionHandler
    private ResponseEntity<ItemErrorResponse> handleException(ItemNotFoundException ex){
        ItemErrorResponse response = new ItemErrorResponse(
                "Item with this id does not exist.", LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ItemErrorResponse> handleException(ItemNotCreatedException ex){
        ItemErrorResponse response = new ItemErrorResponse(
                ex.getMessage(), LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private HardDrive convertToHardDrive(HardDriveDTO hardDriveDTO){
        return modelMapper.map(hardDriveDTO, HardDrive.class);
    }

    private HardDriveDTO convertToHardDriveDTO(HardDrive hardDrive){
        return modelMapper.map(hardDrive, HardDriveDTO.class);
    }
}
