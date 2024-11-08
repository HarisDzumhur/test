package com.example.app.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.app.exception.AccessDeniedException;
import com.example.app.exception.AccountBlockedException;
import com.example.app.exception.AccountDeletedException;
import com.example.app.exception.AnimalException;
import com.example.app.exception.EquipmentException;
import com.example.app.exception.EquipmentTypeException;
import com.example.app.exception.GoogleAccountException;
import com.example.app.exception.GroupException;
import com.example.app.exception.HuntingCalendarException;
import com.example.app.exception.IncorrectPasswordException;
import com.example.app.exception.InvalidTokenException;
import com.example.app.exception.MailException;
import com.example.app.exception.MarkerException;
import com.example.app.exception.NotificationException;
import com.example.app.exception.RequestAlreadySentException;
import com.example.app.exception.SectionException;
import com.example.app.exception.TagException;
import com.example.app.exception.UserAlreadyExistsException;
import com.example.app.exception.UserAlreadyInGroupException;
import com.example.app.exception.UserNotFoundException;
import com.example.app.exception.UserUpdateException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<String> handleIncorrectPasswordException(IncorrectPasswordException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<String> handleInvalidTokenException(InvalidTokenException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MailException.class)
    public ResponseEntity<String> handleMailException(MailException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
    
    @ExceptionHandler(EquipmentException.class)
    public ResponseEntity<String> handleEquipmentNameException(EquipmentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
    
    @ExceptionHandler(UserUpdateException.class)
    public ResponseEntity<String> handleUserUpdateException(UserUpdateException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
    
    @ExceptionHandler(NotificationException.class)
    public ResponseEntity<String> handleNotificationException(NotificationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
    
    @ExceptionHandler(GroupException.class)
    public ResponseEntity<String> handleGroupException(GroupException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
    
    @ExceptionHandler(SectionException.class)
    public ResponseEntity<String> handleSectionException(SectionException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
    
    @ExceptionHandler(EquipmentTypeException.class)
    public ResponseEntity<String> handleEquipmentTypeException(EquipmentTypeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(AccountBlockedException.class)
    public ResponseEntity<String> handleAccountBlockedException(AccountBlockedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.LOCKED);
    }
    @ExceptionHandler(AccountDeletedException.class)
    public ResponseEntity<String> handleAccountDeletedException(AccountDeletedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.GONE);
    }
    
    @ExceptionHandler(GoogleAccountException.class)
    public ResponseEntity<String> handleGoogleAccountException(GoogleAccountException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }
    
    @ExceptionHandler(RequestAlreadySentException.class)
    public ResponseEntity<String> handleRequestAlreadySentException(RequestAlreadySentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }
    
    @ExceptionHandler(UserAlreadyInGroupException.class)
    public ResponseEntity<String> handleUserAlreadyInGroupException(UserAlreadyInGroupException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.PRECONDITION_FAILED);
    }
    
    @ExceptionHandler(HuntingCalendarException.class)
    public ResponseEntity<String> handleHuntingCalendarException(HuntingCalendarException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
    
    @ExceptionHandler(AnimalException.class)
    public ResponseEntity<String> handleAnimalException(AnimalException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
    
    @ExceptionHandler(MarkerException.class)
    public ResponseEntity<String> handleMarkerException(MarkerException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(TagException.class)
    public ResponseEntity<String> handleTagException(TagException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}
