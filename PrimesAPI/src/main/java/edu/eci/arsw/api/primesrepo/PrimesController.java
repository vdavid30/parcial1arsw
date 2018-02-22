package edu.eci.arsw.api.primesrepo;

import edu.eci.arsw.api.primesrepo.model.FoundPrime;
import edu.eci.arsw.api.primesrepo.service.PrimeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Santiago Carrillo
 * 2/22/18.
 */
@RestController
public class PrimesController
{
    @Autowired
    PrimeService primeService;


    @RequestMapping( value = "/primes", method = GET )
    public List<FoundPrime> getPrimes(){
        System.out.println("Solicita todos los primos");
        return primeService.getFoundPrimes();
    }
    
    @RequestMapping( value = "/primes", method = POST )
    public void postPrimes(FoundPrime fp)    {
        System.out.println("Ingresa  un numero especifico de primo");
        primeService.addFoundPrime(fp);
    }
    
    @RequestMapping( value = "/primes/{primenumber}", method = GET )
    public FoundPrime getPrime(String primenumber){
        System.out.println("Consulta un numero especifico de primo");
        return primeService.getPrime(primenumber);
    }        
}
