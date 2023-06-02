package ru.volkova.EasyBotTest.controllers;

import ru.volkova.EasyBotTest.dto.ComputerMonitorDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ru.volkova.EasyBotTest.models.ComputerMonitor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.volkova.EasyBotTest.services.ComputerMonitorService;
import ru.volkova.EasyBotTest.util.ItemErrorResponse;
import ru.volkova.EasyBotTest.util.ItemNotCreatedException;
import ru.volkova.EasyBotTest.util.ItemNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/monitor")
@RequiredArgsConstructor
public class ComputerMonitorController {

    private final ComputerMonitorService monitorService;

    private final ModelMapper modelMapper;

    @GetMapping()
    public List<ComputerMonitorDTO> getAllComputerMonitors(){
        return monitorService.findAll().stream()
                .map(this::convertToMonitorDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ComputerMonitorDTO getComputerMonitor(@PathVariable("id") Integer id){
        return convertToMonitorDTO(monitorService.findById(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid ComputerMonitorDTO monitorDTO,
                                             BindingResult bindingResult){
        checkForErrors(bindingResult);
        monitorService.save(convertToMonitor(monitorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid ComputerMonitorDTO monitorDTO,
                                             @PathVariable("id") Integer id,
                                             BindingResult bindingResult){
        checkForErrors(bindingResult);
        monitorService.update(id, convertToMonitor(monitorDTO));
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

    private ComputerMonitor convertToMonitor(ComputerMonitorDTO computerMonitorDTO){
        return modelMapper.map(computerMonitorDTO, ComputerMonitor.class);
    }

    private ComputerMonitorDTO convertToMonitorDTO(ComputerMonitor computerMonitor){
        return modelMapper.map(computerMonitor, ComputerMonitorDTO.class);
    }
}
