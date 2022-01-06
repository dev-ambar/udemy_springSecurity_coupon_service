package com.learningpath.couponsservice.controller;

import com.learningpath.couponsservice.model.Coupon;
import com.learningpath.couponsservice.repos.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CouponServiceController {

    @Autowired
    private CouponRepository couponRepo;

    @GetMapping("/showCoupons")
    public String showCoupons()
    {
        return "showCoupons";
    }

    @PostMapping ("/couponDetails")
    public ModelAndView couponDetails(String code)
    {
        Optional<Coupon> c = couponRepo.findByCode(code);
        if(c.isPresent())
            return new ModelAndView("couponDetails").addObject(c.get());
        else {
            String notFound = "Coupon code not found";
            return new ModelAndView("showCoupons").addObject(notFound);
        }
    }

    @GetMapping("/createCoupon")
    public String createCoupon()
    {
        return "createCoupon";
    }

    @GetMapping("/index")
    public String index()
    {
        return "index";
    }

    @PostMapping("/saveCoupon")
    public String saveCoupon(Coupon coupon)
    {
        couponRepo.save(coupon);
        return "createCouponResponse";
    }
}
