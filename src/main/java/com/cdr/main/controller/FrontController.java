package com.cdr.main.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdr.main.entity.BillingCdr;
import com.cdr.main.entity.DataCdr;
import com.cdr.main.entity.EventCdr;
import com.cdr.main.entity.InternationalRoamingCdr;
import com.cdr.main.entity.LocationCdr;
import com.cdr.main.entity.MmsCdr;
import com.cdr.main.entity.Registeration;
import com.cdr.main.entity.SmsCdr;
import com.cdr.main.entity.VideoCdr;
import com.cdr.main.entity.VoIPCdr;
import com.cdr.main.entity.VoiceCdr;
import com.cdr.main.service.CdrService;

@RestController
public class FrontController 
{
   @Autowired 
   CdrService service;   
   
   /***User Registration***/ 
   @CrossOrigin(origins="http://localhost:4200/")
   @PostMapping("/register")
   public String registerUser(@RequestBody Registeration registerObject) {
       System.out.println("Front controller - User registration");
       service.createNewUser(registerObject);
       return "DATA CREATED";
   }
   
   /***Check Existing User***/
   @CrossOrigin(origins="http://localhost:4200/")
   @GetMapping("/existingUser/{phoneNumber}")
   @ResponseBody
   public Registeration checkExistingUser(@PathVariable("phoneNumber") String phoneNumber) {
       return service.checkExistingUser(phoneNumber);
   }

   /***Voice CDR***/
   @CrossOrigin(origins="http://localhost:4200/")
   @GetMapping("/voice/{quantity}")
   public List<VoiceCdr> generateVoiceCdr(@PathVariable("quantity") int quantity) {
       return service.generateVoiceCdrList(quantity);
   }
   
   /***SMS CDR***/
   @CrossOrigin(origins="http://localhost:4200/")
   @GetMapping("/sms/{quantity}")
   public List<SmsCdr> sms_creation(@PathVariable("quantity") int quantity) {
       return service. generateSmsCdrList(quantity);

	}  
   
   /***Data CDR***/
   @CrossOrigin(origins="http://localhost:4200/")
   @GetMapping("/data/{quantity}")
    public List<DataCdr> data_creation(@PathVariable("quantity") int quantity) {
       return service.generateDataCdrList(quantity);
    } 
   
   /***MMS CDR***/
   @CrossOrigin(origins="http://localhost:4200/")
   @GetMapping("/mms/{quantity}")
   public List<MmsCdr> generateMmsCdr(@PathVariable("quantity") int quantity) {
	    return service.generateMmsCdrList(quantity);
	}
   
   /***International Roaming CDR***/
   @CrossOrigin(origins="http://localhost:4200/")
   @GetMapping("/roaming/{quantity}")
   public List<InternationalRoamingCdr> generateRoamingCdr(@PathVariable("quantity") int quantity) {
	    return service.generateRoamingCdrList(quantity);
	}
   
   /***VoIP CDR***/
   @CrossOrigin(origins="http://localhost:4200/")
   @GetMapping("/voip/{quantity}")
   public List<VoIPCdr> generateVoIPCdr(@PathVariable("quantity") int quantity) {
	    return service.generateVoIPCdrList(quantity);
	}
   
   /***Location CDR***/
   @CrossOrigin(origins="http://localhost:4200/")
   @GetMapping("/location/{quantity}")
   public List<LocationCdr> generateLocationCdr(@PathVariable("quantity") int quantity) {
	    return service.generateLocationCdrList(quantity);
	}
   
   /***Video CDR***/
   @CrossOrigin(origins="http://localhost:4200/")
   @GetMapping("/video/{quantity}")
   public List<VideoCdr> generateVideoCdr(@PathVariable("quantity") int quantity) {
	    return service.generateVideoCdrList(quantity);
	}
   
   /***Billing CDR***/
   @CrossOrigin(origins="http://localhost:4200/")
   @GetMapping("/billing/{quantity}")
   public List<BillingCdr> generateBillingCdr(@PathVariable("quantity") int quantity) {
	    return service.generateBillingCdrList(quantity);
	}
   
   /**Event CDR***/
   @CrossOrigin(origins="http://localhost:4200/")
   @GetMapping("/event/{quantity}")
   public List<EventCdr> generateEventCdr(@PathVariable("quantity") int quantity) {
	    return service.generateEventCdrList(quantity);
	}
   
   
}