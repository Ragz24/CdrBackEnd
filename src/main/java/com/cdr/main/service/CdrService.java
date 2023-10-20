package com.cdr.main.service;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.cdr.main.functionality.BillingCdrGeneration;
import com.cdr.main.functionality.DataCdrGeneration;
import com.cdr.main.functionality.EventCdrGeneration;
import com.cdr.main.functionality.InternationRoamingCdrGeneration;
import com.cdr.main.functionality.LocationCdrGeneration;
import com.cdr.main.functionality.MmsCdrGeneration;
import com.cdr.main.functionality.SmsCdrGeneration;
import com.cdr.main.functionality.VideoCdrGeneration;
import com.cdr.main.functionality.VoIPCdrGeneration;
import com.cdr.main.functionality.VoiceCdrGeneration;
import com.cdr.main.repository.CdrRepository;

@Service
public class CdrService {
	
	
	/************************User Registration********************/
	@Autowired
	CdrRepository repoistory; 
	
	public void createNewUser(Registeration registerObject)
	{   
		System.out.println("data created successfully");
		
		repoistory.save(registerObject);
	}
     
	
	/****************Checking Existing User Registration*************/
	public Registeration checkExistingUser(String phoneNumber)
	{
       	return repoistory.findByphoneNumber(phoneNumber);
	}  
	
	
    /**************************VOICE CDR**************************/
	public List<VoiceCdr> generateVoiceCdrList(int quantity) {
	    List<VoiceCdr> voiceCdrList = new ArrayList<>();
	    VoiceCdrGeneration object = new VoiceCdrGeneration();

	    try {
	        File cdr = new File("voiceCdr.txt");
	        if (cdr.exists()) {
	            cdr.delete();
	            cdr = new File("voiceCdr.txt");
	        }

	        FileOutputStream fis = new FileOutputStream(cdr, true); 
	        DataOutputStream dos = new DataOutputStream(fis);

	        for (int i = 0; i < quantity; i++) {
	            VoiceCdr voice = object.generateVoiceCDR();
	            voiceCdrList.add(voice);
	            dos.writeUTF(voice.toString());
	        }

	        dos.close(); 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return voiceCdrList;
	}


    
    
    /**************************SMS CDR**************************/
	public List<SmsCdr> generateSmsCdrList(int quantity) {
	    List<SmsCdr> smsCdrList = new ArrayList<>();
	    SmsCdrGeneration object = new SmsCdrGeneration();

	    try {
	        File cdr = new File("smsCdr.txt");
	        if (cdr.exists()) {
	            cdr.delete();
	            cdr = new File("smsCdr.txt");
	        }

	        FileOutputStream fis = new FileOutputStream(cdr, true); 
	        DataOutputStream dos = new DataOutputStream(fis);

	        for (int i = 0; i < quantity; i++) {
	            SmsCdr smsCdr = object.generateSmsCDR();
	            smsCdrList.add(smsCdr);
	            dos.writeUTF(smsCdr.toString());
	        }

	        dos.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return smsCdrList;
	}

    
    /**************************DATA CDR**************************/
	public List<DataCdr> generateDataCdrList(int quantity) {
	    List<DataCdr> dataCdrList = new ArrayList<>();
	    DataCdrGeneration object = new DataCdrGeneration();

	    try {
	        File cdr = new File("dataCdr.txt");
	        if (cdr.exists()) {
	            cdr.delete();
	            cdr = new File("dataCdr.txt");
	        }

	        FileOutputStream fis = new FileOutputStream(cdr, true); 
	        DataOutputStream dos = new DataOutputStream(fis);

	        for (int i = 0; i < quantity; i++) {
	            DataCdr dataCdr = object.generateDataCDR();
	            dataCdrList.add(dataCdr);
	            dos.writeUTF(dataCdr.toString());
	        }

	        dos.close(); 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return dataCdrList;
	}

    /**************************MMS CREATION**************************/
	public List<MmsCdr> generateMmsCdrList(int quantity) {
	    List<MmsCdr> mmsCdrList = new ArrayList<>();
	    MmsCdrGeneration object = new MmsCdrGeneration();

	    try {
	        File cdr = new File("mmsCdr.txt");
	        if (cdr.exists()) {
	            cdr.delete();
	            cdr = new File("mmsCdr.txt");
	        }

	        FileOutputStream fis = new FileOutputStream(cdr, true); 
	        DataOutputStream dos = new DataOutputStream(fis);

	        for (int i = 0; i < quantity; i++) {
	            MmsCdr mmsCdr = object.generateMmsCDR();
	            mmsCdrList.add(mmsCdr);
	            dos.writeUTF(mmsCdr.toString());
	        }

	        dos.close(); 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return mmsCdrList;
	}

    
    /**************************INTERNATIONAL ROAMING CDR**************************/
	public List<InternationalRoamingCdr> generateRoamingCdrList(int quantity) {
	    List<InternationalRoamingCdr> roamingCdrList = new ArrayList<>();
	    InternationRoamingCdrGeneration object = new InternationRoamingCdrGeneration();

	    try {
	        File cdr = new File("roamingCdr.txt");
	        if (cdr.exists()) {
	            cdr.delete();
	            cdr = new File("roamingCdr.txt");
	        }

	        FileOutputStream fis = new FileOutputStream(cdr, true); 
	        DataOutputStream dos = new DataOutputStream(fis);

	        for (int i = 0; i < quantity; i++) {
	            InternationalRoamingCdr roamingCdr = object.generateRoamingCDR();
	            roamingCdrList.add(roamingCdr);
	            dos.writeUTF(roamingCdr.toString());
	        }

	        dos.close(); 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return roamingCdrList;
	}

    
    /**************************VOIP CDR**************************/
	public List<VoIPCdr> generateVoIPCdrList(int quantity) {
	    List<VoIPCdr> voipCdrList = new ArrayList<>();
	    VoIPCdrGeneration object = new VoIPCdrGeneration();

	    try {
	        File cdr = new File("voIPCdr.txt");
	        if (cdr.exists()) {
	            cdr.delete();
	            cdr = new File("voIPCdr.txt");
	        }

	        FileOutputStream fis = new FileOutputStream(cdr, true); 
	        DataOutputStream dos = new DataOutputStream(fis);

	        for (int i = 0; i < quantity; i++) {
	            VoIPCdr voip = object.generateVoipCDR();
	            voipCdrList.add(voip);
	            dos.writeUTF(voip.toString());
	        }

	        dos.close(); 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return voipCdrList;
	}

    /**************************LOCATION CDR**************************/
	public List<LocationCdr> generateLocationCdrList(int quantity) {
	    List<LocationCdr> locationCdrList = new ArrayList<>();
	    LocationCdrGeneration object = new LocationCdrGeneration();

	    try {
	        File cdr = new File("locationCdr.txt");
	        if (cdr.exists()) {
	            cdr.delete();
	            cdr = new File("locationCdr.txt");
	        }

	        FileOutputStream fis = new FileOutputStream(cdr, true); 
	        DataOutputStream dos = new DataOutputStream(fis);

	        for (int i = 0; i < quantity; i++) {
	            LocationCdr location = object.generateLocationCDR();
	            locationCdrList.add(location);
	            dos.writeUTF(location.toString());
	        }

	        dos.close(); 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return locationCdrList;
	}

    
    /**************************VIDEO CDR**************************/
	public List<VideoCdr> generateVideoCdrList(int quantity) {
	    List<VideoCdr> videoCdrList = new ArrayList<>();
	    VideoCdrGeneration object = new VideoCdrGeneration();

	    try {
	        File cdr = new File("videoCdr.txt");
	        if (cdr.exists()) {
	            cdr.delete();
	            cdr = new File("videoCdr.txt");
	        }

	        FileOutputStream fis = new FileOutputStream(cdr, true); 
	        DataOutputStream dos = new DataOutputStream(fis);

	        for (int i = 0; i < quantity; i++) {
	            VideoCdr video = object.generateVideoCDR();
	            videoCdrList.add(video);
	            dos.writeUTF(video.toString());
	        }

	        dos.close(); 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return videoCdrList;
	}

    
    /**************************VOICE CALL BILLING CDR**************************/
	public List<BillingCdr> generateBillingCdrList(int quantity) {
	    List<BillingCdr> billingCdrList = new ArrayList<>();
	    BillingCdrGeneration object = new BillingCdrGeneration();

	    try {
	        File cdr = new File("billingCdr.txt");
	        if (cdr.exists()) {
	            cdr.delete();
	            cdr = new File("billingCdr.txt");
	        }

	        FileOutputStream fis = new FileOutputStream(cdr, true); 
	        DataOutputStream dos = new DataOutputStream(fis);

	        for (int i = 0; i < quantity; i++) {
	            BillingCdr billing = object.generateBillingCDR();
	            billingCdrList.add(billing);
	            dos.writeUTF(billing.toString());
	        }

	        dos.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return billingCdrList;
	}

    
    
    /**************************Event CDR**************************/
	public List<EventCdr> generateEventCdrList(int quantity) {
	    List<EventCdr> eventCdrList = new ArrayList<>();
	    EventCdrGeneration object = new EventCdrGeneration();
	    try {
	        File cdr = new File("eventCdr.txt"); 
	        if (cdr.exists()) {
	            cdr.delete();
	            cdr = new File("eventCdr.txt");
	        }

	        FileOutputStream fis = new FileOutputStream(cdr, true); 
	        DataOutputStream dos = new DataOutputStream(fis);

	        for (int i = 0; i < quantity; i++) {
	            EventCdr event = object.generateEventCallBillingCDR();
	            eventCdrList.add(event);
	            dos.writeUTF(event.toString());
	        }

	        dos.close(); 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return eventCdrList;
	}

}
