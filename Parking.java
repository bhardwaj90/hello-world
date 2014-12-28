/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aakash
 */

import java.io.*;

public class Parking{
    
    private static final String entercar="EnterCar";
    private static final String entertruck="EnterTruck";
    private static final String exitcar="ExitCar";
    private static final String exittruck="ExitTruck";
    private static final String Quit="Quit";
    private static final String Report="Report";
    
    public static void main(String [] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the number of spaces: ");
        String s=br.readLine();
        int N;
        try{ 
            N=Integer.parseInt(s);
        }
        catch (Exception e){ System.out.println("WTF are you inputting? get the fuck out, exiting"); return;
        
        }
        
        ParkingLot lot= new ParkingLot(N);
        boolean running =true;
        while(running){
            s=br.readLine();
            switch (s){
            
                case Quit: running = false; break;
                case entercar: lot.EnterCar(); break;
                case entertruck: lot.EnterTruck(); break;
                case Report:  PrintReport(lot.getReport());
                default:
                    if (s.startsWith(exitcar)){
                        int time= Integer.parseInt(s.substring(exitcar.length()));
                        lot.ExitCar(time);
                    }
                    else if(s.startsWith(exittruck)){
                        int time=Integer.parseInt(s.substring(exittruck.length()));
                        lot.ExitTruck(time);
                    }
                    else{
                        System.out.println("Wrong input man");
                    }
                    
                    
            }
                   
             
        }
    }
    
    private static void PrintReport (int[] arr){
        
        System.out.println("Cars entered: "+ arr[0]);
        System.out.println("Trucks entered: "+ arr[1]);
        System.out.println("Cars exited: "+ arr[2]);
        System.out.println("Trucks exited: "+ arr[3]);
        System.out.println("Cars parking: "+ arr[4]);
        System.out.println("Trucks parking: "+ arr[5]);
        System.out.println("Spaces available: "+ arr[6]);
        System.out.println("Fees paid: "+ arr[7]);
    }
}

 class ParkingLot {
  
    private final int totalSpaces;
    private int carsParked=0;
    private int trucksParked=0;
    
    private int carsEntered=0;
    private int carsExited=0;
    
    private int FeeCollection=0;
    
    private int trucksEntered=0;
    private int trucksExited=0;
    
    public ParkingLot(int Spaces){
        totalSpaces=Spaces;
        
    }    
    
    public void EnterCar(){
        carsParked++;
        carsEntered++;
    }
    
    public void ExitCar(int time)
    {
        carsParked--;
        carsExited++;
        FeeCollection+=car.getCostPerHour()*time;
    }
    
    public void EnterTruck(){
        trucksParked++;
        trucksEntered++;
    }
    
    public void ExitTruck(int time){
        trucksParked--;
        trucksExited++;
        FeeCollection+=car.getCostPerHour()*time;
    }
    /* populates values in an array with following order
    Cars Entered: 1 
Trucks Entered: 1 
Cars Exited: 1 
Trucks Exited: 0 
Parking Cars: 0 
Parking Trucks: 1 
Spaces available: 13 
Fees paid: $4 
    */
    public int[] getReport(){
        int [] ans=new int[8];
        ans[0]=carsEntered;
        ans[1]=trucksEntered;
        ans[2]=carsExited;
        ans[3]=trucksExited;
        ans[4]=carsParked;
        ans[5]=trucksParked;
        ans[6]= totalSpaces -(carsParked*car.getSpacesOccupied() + trucksParked*truck.getSpacesOccupied());
        ans[7]=FeeCollection;
        return ans;
    }
    
}

class car{
    private static final int _costHr=2;
    private static final int _spacesOccupied=1;
    public static final int getCostPerHour(){
        return _costHr;
    }
    
    public static final int getSpacesOccupied(){
        return _spacesOccupied;
    }
}

class truck {
    private static final int _costHr=3;
    private static final int _spacesOccupied=2;
    
    public static final int getCostPerHour(){
        return _costHr;
    }
    public static final int getSpacesOccupied(){
        return _spacesOccupied;
    }
}

