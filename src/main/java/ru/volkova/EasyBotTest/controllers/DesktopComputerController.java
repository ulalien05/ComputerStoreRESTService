package ru.volkova.EasyBotTest.controllers;

import ru.volkova.EasyBotTest.dto.DesktopComputerDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ru.volkova.EasyBotTest.models.DesktopComputer;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.volkova.EasyBotTest.services.DesktopComputerService;
import ru.volkova.EasyBotTest.util.ItemErrorResponse;
import ru.volkova.EasyBotTest.util.ItemNotCreatedException;
import ru.volkova.EasyBotTest.util.ItemNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/desktop")
@RequiredArgsConstructor
public class DesktopComputerController {

    private final DesktopComputerService desktopComputerService;

    private final ModelMapper modelMapper;

    @GetMapping()
    public List<DesktopComputerDTO> getAllDesktopComputers(){
        return desktopComputerService.findAll().stream()
                .map(this::convertToComputerDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DesktopComputerDTO getDesktopComputer(@PathVariable("id") Integer id){
        return convertToComputerDTO(desktopComputerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid DesktopComputerDTO computerDTO,
                                             BindingResult bindingResult){
        checkForErrors(bindingResult);
        desktopComputerService.save(convertToComputer(computerDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid DesktopComputerDTO desktopComputerDTO,
                                             @PathVariable("id") Integer id,
                                             BindingResult bindingResult){
        checkForErrors(bindingResult);
        desktopComputerService.update(id, convertToComputer(desktopComputerDTO));
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

    private DesktopComputer convertToComputer(DesktopComputerDTO desktopComputerDTO){
        return modelMapper.map(desktopComputerDTO, DesktopComputer.class);
    }

    private DesktopComputerDTO convertToComputerDTO(DesktopComputer computer){
        return modelMapper.map(computer, DesktopComputerDTO.class);
    }
}
