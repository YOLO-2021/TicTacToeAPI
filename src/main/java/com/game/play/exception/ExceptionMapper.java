package com.game.play.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.game.play.model.ErrorInfo;

@RestControllerAdvice
public class ExceptionMapper {

	
	@ExceptionHandler(InvalidMoveException.class)
	public ResponseEntity<ErrorInfo> InvalidMoveException(TicTacToeException ex) {
		
		ErrorInfo info = new ErrorInfo(ex.getMessage());
		return new ResponseEntity<>(info,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(GameNotFoundException.class)
	public ResponseEntity<ErrorInfo> gameNotFoundException(TicTacToeException ex) {
		
		ErrorInfo info = new ErrorInfo(ex.getMessage());
		return new ResponseEntity<>(info,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> generalException(Exception ex) {
		
		ErrorInfo info = new ErrorInfo(ex.getMessage());
		return new ResponseEntity<>(info,HttpStatus.BAD_REQUEST);
		
	}
	
	
}
