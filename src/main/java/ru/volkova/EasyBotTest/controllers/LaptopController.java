package ru.volkova.EasyBotTest.controllers;

import ru.volkova.EasyBotTest.dto.LaptopDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ru.volkova.EasyBotTest.models.Laptop;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.volkova.EasyBotTest.services.LaptopService;
import ru.volkova.EasyBotTest.util.ItemErrorResponse;
import ru.volkova.EasyBotTest.util.ItemNotCreatedException;
import ru.volkova.EasyBotTest.util.ItemNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/laptop")
@RequiredArgsConstructor
public class LaptopController {

    private final LaptopService laptopService;

    private final ModelMapper modelMapper;

    @GetMapping()
    public List<LaptopDTO> getAllLaptops(){
        return laptopService.findAll().stream()
                .map(this::convertToLaptopDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public LaptopDTO getDesktopComputer(@PathVariable("id") Integer id){
        return convertToLaptopDTO(laptopService.findById(id));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid LaptopDTO laptopDTO,
                                             BindingResult bindingResult){
        checkForErrors(bindingResult);
        laptopService.save(convertToLaptop(laptopDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody @Valid LaptopDTO laptopDTO,
                                             @PathVariable("id") Integer id,
                                             BindingResult bindingResult){
        checkForErrors(bindingResult);
        laptopService.update(id, convertToLaptop(laptopDTO));
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

    private Laptop convertToLaptop(LaptopDTO laptopDTO){
        return modelMapper.map(laptopDTO, Laptop.class);
    }

    private LaptopDTO convertToLaptopDTO(Laptop laptop){
        return modelMapper.map(laptop, LaptopDTO.class);
    }
}
