package cn.az.boot.controller;

import cn.az.boot.entity.Student;
import cn.az.boot.service.RedisService;
import cn.az.boot.service.StudentService;
import cn.az.boot.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

/**
 * @author azusachino
 * @version 12/10/2019
 */
@RestController
@RequestMapping("/api")
public class StudentController {

    final StudentService studentService;

    final RedisService redisService;

    @Autowired
    public StudentController(StudentService studentService, RedisService redisService) {
        this.studentService = studentService;
        this.redisService = redisService;
    }

    @RequestMapping("/add")
    public ResponseEntity<String> add(@RequestBody Student student) {
        Optional<?> optional = studentService.findById(student.getId());
        if (optional.isPresent()) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("Already Exist");
        } else {
            if (Objects.nonNull(studentService.save(student))) {
                redisService.set(student.getId(), JsonUtil.toString(student).orElse(""));
            }
            return ResponseEntity.ok("ok da");
        }
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<String> get(@PathVariable("id") String id) {
        Optional<? extends Student> o = JsonUtil.toBean(redisService.get(id), Student.class);
        if (o.isPresent()) {
            return ResponseEntity.ok(JsonUtil.toString(o.get()).orElse(""));
        } else {
            Optional<? extends Student> o2 = studentService.findById(id);
            return o2.map(student -> ResponseEntity.ok(JsonUtil.toString(student).orElse("")))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("find nothing"));
        }
    }
}
