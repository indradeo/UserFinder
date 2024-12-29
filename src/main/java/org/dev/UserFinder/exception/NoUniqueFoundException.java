package org.dev.UserFinder.exception;

import lombok.Getter;

@Getter
public class NoUniqueFoundException extends RuntimeException{

        private String message;

       public NoUniqueFoundException(String message){
            this.message= message;
        }
}
