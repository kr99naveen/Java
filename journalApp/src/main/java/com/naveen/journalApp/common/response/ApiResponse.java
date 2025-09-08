package com.naveen.journalApp.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)  // Exclude null fields from JSON
@Data                // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor   // No-arg constructor
@AllArgsConstructor  // All-arg constructor
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    //custom constructors
    public ApiResponse(boolean success, String message) {
        this(success, message, null);
    }
}


//import com.fasterxml.jackson.annotation.JsonInclude;
//
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class ApiResponse<T> {
//
//    private boolean success;
//    private String message;
//    private T data;
//
//    // Constructors
//    public ApiResponse() {}
//
//    public ApiResponse(boolean success, String message, T data) {
//        this.success = success;
//        this.message = message;
//        this.data = data;
//    }
//
//    public ApiResponse(boolean success, String message) {
//        this(success, message, null);
//    }
//
//    // Getters and Setters
//    public boolean isSuccess() {
//        return success;
//    }
//
//    public void setSuccess(boolean success) {
//        this.success = success;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public T getData() {
//        return data;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }
//}
