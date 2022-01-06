package com.learningpath.couponsservice.controller;

import com.learningpath.couponsservice.model.Coupon;
import com.learningpath.couponsservice.repos.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/couponapi")
public class CouponController {
    @Autowired
    private CouponRepository couponRepository;

    @PostMapping("/coupons")
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon)
    {
        Coupon createCoupon = couponRepository.save(coupon);
        return  new ResponseEntity<Coupon>(createCoupon, HttpStatus.CREATED);
    }
    @GetMapping("/coupons/{code}")
    public ResponseEntity<Coupon> getCoupon(@PathVariable String code)
    {
        Optional<Coupon> coupon = couponRepository.findByCode(code);
        ResponseEntity<Coupon> responseEntity;
        if(coupon.isPresent())
            responseEntity = new ResponseEntity(coupon.get(),HttpStatus.FOUND);
        else responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @GetMapping("/coupons")
    public ResponseEntity<Coupon> coupons()
    {
        List<Coupon> coupon = couponRepository.findAll();
        ResponseEntity<Coupon> responseEntity;
        if(!coupon.isEmpty())
            responseEntity = new ResponseEntity(coupon,HttpStatus.FOUND);
        else responseEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}
