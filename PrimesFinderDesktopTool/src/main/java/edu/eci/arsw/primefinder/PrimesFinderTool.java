package edu.eci.arsw.primefinder;

import edu.eci.arsw.mouseutils.MouseMovementMonitor;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class PrimesFinderTool {

	public static void main(String[] args) {
		            
            int maxPrim=1000;
            
            PrimesResultSet prs=new PrimesResultSet("john");            
            boolean val = false;
            //Solucion del primer punto comentada para mostrar solucion del segundo 
            /**
            for(int i = 0; i <= 4;i++){
                PrimeFinder pf = new PrimeFinder(new BigInteger(Integer.toString((i*(1000/4))+1)), new BigInteger(Integer.toString((i+1)*2500)), prs);            
                pf.run();
                val = true;
            }
            **/
            
            
            
            
            while(!val){
                try {
                    //check every 10ms if the idle status (10 seconds without mouse
                    //activity) was reached. 
                    CopyOnWriteArrayList<PrimeFinder> thre = new CopyOnWriteArrayList();                                         
                    for(int i = 0; i <= 4;i++){
                        PrimeFinder pf = new PrimeFinder(new BigInteger(Integer.toString((i*(1000/4))+1)), new BigInteger(Integer.toString((i+1)*2500)), prs);                                    
                        thre.add(pf);     
                        pf.start();                        
                        
                    }
                    Thread.sleep(10);
                    if (MouseMovementMonitor.getInstance().getTimeSinceLastMouseMovement()>10000){
                        System.out.println("Idle CPU ");
                        for(PrimeFinder p : thre){
                            p.resume();
                        }
                    }
                    else{                        
                        for(PrimeFinder p : thre){
                            p.stop();
                        }                        
                    }
                    val = true;
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(PrimesFinderTool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println("Prime numbers found:");
            
            System.out.println(prs.getPrimes());            
            
            
            
            
	}
	
}


