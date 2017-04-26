package tn.insat.Client;

import java.util.concurrent.Semaphore;

/**
 * Created by Khalil on 26/04/2017.
 */
public class SemaphoreClass {
    static public Semaphore available = new Semaphore(0,true);
}
