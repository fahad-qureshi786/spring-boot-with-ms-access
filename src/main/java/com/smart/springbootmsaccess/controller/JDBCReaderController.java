package com.smart.springbootmsaccess.controller;

import com.smart.springbootmsaccess.model.Book;
import com.smart.springbootmsaccess.service.JDBCReaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/reader")
@Slf4j
public class JDBCReaderController {
    @Autowired
    private JDBCReaderService jdbcReaderService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllData() {
        try {
            List<Book> bookList = jdbcReaderService.getAllBooks();
            return ResponseEntity.ok(bookList);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
        }
    }


    @GetMapping("/exportDataToExcelCSV")
    public ResponseEntity<?> exportDataToExcelCSV(HttpServletResponse response) {
        try {
            List<Book> bookList = jdbcReaderService.exportDataToExcelCSV(response);
            return ResponseEntity.ok(bookList);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new String(e.getMessage()));
        }
    }


}
