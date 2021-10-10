import java.io.*;
import java.util.*;
class Vaccine {
    public static ArrayList<Vaccine> vaccines=new ArrayList<Vaccine>();
    String name;
    int no_of_doses;
    int gap;
    Vaccine(String s,int n,int g) {
        vaccines.add(this);
        name=s;
        no_of_doses=n;
        gap=g;
    }
    void display_vaccine() {
        System.out.println("Vaccine name:"+name+", Number of doses:"+no_of_doses+", Gap Between Doses:"+gap);
    }
}

class Hospital {
    public static ArrayList<Hospital> hospital=new ArrayList<Hospital>();
    static int number=100000;
    String name;
    int pincode;
    int id;
    Hospital(String s,int p) {
        hospital.add(this);
        name=s;
        pincode=p;
        id=number;
        number++;
    }
    void display_hospital() {
        System.out.println("Hospital Name:"+name+", Pincode:"+pincode+", Unique ID:"+id);
    }
}

class Citizen {
    public static ArrayList<Citizen> citizens=new ArrayList<Citizen>();
    String name;
    int age;
    String id;
    String status="NOT REGISTERED";
    int dose;
    int nod;
    int due;
    String vac;
    Citizen(String n,int a,String i) {
        if(a>18) citizens.add(this);
        name=n;
        age=a;
        id=i;
        status="REGISTERED";
        dose=0;
        due=1;
        vac="";
    }
    void display_citizen() {
        System.out.println("Citizen Name:"+name+", Age:"+age+", Unique ID:"+id);
    }
}

class Slot {
    public static ArrayList<Slot> slot=new ArrayList<Slot>();
    int hospital;
    int day;
    int quantity;
    String vac;
    int gap;
    int nod;
    String h_name;
    int id;
    Slot(int h,int d,int q,String v,int g,int n,String hn,int i) {
        slot.add(this);
        hospital=h;
        day=d;
        quantity=q;
        vac=v;
        gap=g;
        nod=n;
        h_name=hn;
        id=i;
    }
    void slot_display() {
        System.out.println("Slot added by Hospital "+hospital+" for day "+day+", Available quantity: "+quantity+" of Vaccine "+vac);
    }
}

class Assignment {
    public static void main(String[] args) throws IOException {
        Reader.init(System.in);
        System.out.println("CoWin Portal initialised...");
        int o=0;
        do {
            System.out.println("---------------------------------");
            System.out.println("1. Add Vaccine");
            System.out.println("2. Register Hospital");
            System.out.println("3. Register Citizen");
            System.out.println("4. Add Slot for Vaccination");
            System.out.println("5. Book Slot for Vaccination");
            System.out.println("6. List all slots for a hospital");
            System.out.println("7. Check Vaccination Status");
            System.out.println("8. Exit");
            System.out.println("---------------------------------");

            o=Reader.nextInt();
            String s,i;
            int n;
            int p;

            if(o==1) {
                System.out.print("Vaccine Name:");
                s=Reader.nextLine();
                System.out.print("Number of Doses:");
                n=Reader.nextInt();
                int g;
                if(n>1) {
                    System.out.print("Gap Between Doses:");
                    g=Reader.nextInt();
                }
                else g=0;

                Vaccine v=new Vaccine(s,n,g);
                v.display_vaccine();
            }
            else if(o==2) {
                System.out.print("Hospital Name:");
                String ho=Reader.nextLine();
                System.out.print("Pincode:");
                p=Reader.nextInt();
                Hospital h=new Hospital(ho, p);
                h.display_hospital();
            }
            else if(o==3) {
                System.out.print("Citizen Name:");
                s=Reader.nextLine();
                System.out.print("Age:");
                n=Reader.nextInt();
                System.out.print("Unique ID:");
                i=Reader.nextLine();
                int flag=0;
                for(Citizen y:Citizen.citizens) {
                    if(y.id.equals(i)) {
                        flag=1;
                        System.out.println("Entered ID already registered!");
                    }
                }
                if(flag==0) {
                    if(n<18) System.out.println("Only above 18 are allowed");
                    else {
                        Citizen c=new Citizen(s,n,i);
                        c.display_citizen();
                    }
                }
            }
            else if(o==4) {
                System.out.print("Enter Hospital ID:");
                n=Reader.nextInt();
                int flag=0;
                String hn="";
                int id=-1;
                for(Hospital y:Hospital.hospital) {
                    if(y.id==n) {
                        flag=1;
                        id=y.id;
                        hn=y.name;
                    }
                }
                if(flag==0) System.out.println("Hospital not registered!");
                else {
                    System.out.print("Enter number of slots to be added:");
                    p=Reader.nextInt();
                    for (int t=0;t<p;t++) {
                        System.out.print("Enter day number:");
                        int d=Reader.nextInt();
                        System.out.print("Enter Quantity:");
                        int q=Reader.nextInt();
                        System.out.println("Select vaccine:");
                        int n2=0;
                        for(Vaccine y:Vaccine.vaccines) {
                            System.out.println(n2+". "+y.name);
                            n2++;
                        }
                        int c=Reader.nextInt();
                        String v=Vaccine.vaccines.get(c).name;
                        int n3=Vaccine.vaccines.get(c).no_of_doses;
                        int g=Vaccine.vaccines.get(c).gap;
                        Slot slot=new Slot(n,d,q,v,g,n3,hn,id);
                        slot.slot_display();
                    }
                }
            }
            else if(o==5) {
                System.out.print("Enter patient Unique ID:");
                i=Reader.nextLine();
                int flag1=0;
                String c_name="";
                int ind=0;
                for(Citizen y: Citizen.citizens) {
                    if(i.equals(y.id)) {
                        flag1=1;
                        c_name=y.name;
                        break;
                    }
                    ind++;
                }
                if (flag1==0) System.out.println("No such citizen registered!");
                else{
                    System.out.println("1. Search by area");
                    System.out.println("2. Search by vaccine");
                    System.out.println("3. Exit");
                    System.out.print("Enter option:");
                    int c=Reader.nextInt();
                    int flag=0;
                    if(c==1) {
                        System.out.print("Enter pincode:");
                        p=Reader.nextInt();
                        for (Hospital y:Hospital.hospital) {
                            if(y.pincode==p) {
                                flag=1;
                                System.out.println(y.id+" "+y.name);
                            }
                        }
                        if(flag==0) System.out.println("No hospital available!");
                        else {
                            System.out.print("Enter hospital id:");
                            n=Reader.nextInt();
                            int n1=0,flag_=0;
                            if(Citizen.citizens.get(ind).status.equals("REGISTERED")) {
                                for(Slot y:Slot.slot) {
                                    if(y.hospital==n && y.quantity!=0) {
                                        flag_=1;
                                        System.out.println(n1+"-> Day"+y.day+" Available Qty:"+y.quantity+" Vaccine:"+y.vac);
                                    }
                                    n1++;
                                }
                                if(flag_==0) System.out.println("No slots available!");
                                else {
                                    System.out.print("Choose slot:");
                                    n=Reader.nextInt();
                                    System.out.println(c_name+" vaccinated with "+Slot.slot.get(n).vac);
                                    if(Slot.slot.get(n).nod==1) {
                                        Citizen.citizens.get(ind).status="FULLY VACCINATED";
                                        Citizen.citizens.get(ind).dose=1;
                                        Citizen.citizens.get(ind).nod=Slot.slot.get(n).nod;
                                        Citizen.citizens.get(ind).due=1+Slot.slot.get(n).gap;
                                        Citizen.citizens.get(ind).vac=Slot.slot.get(n).vac;
                                    }
                                    else {
                                        Citizen.citizens.get(ind).status="PARTIALLY VACCINATED";
                                        Citizen.citizens.get(ind).dose=1;
                                        Citizen.citizens.get(ind).nod=Slot.slot.get(n).nod;
                                        Citizen.citizens.get(ind).due=1+Slot.slot.get(n).gap;
                                        Citizen.citizens.get(ind).vac=Slot.slot.get(n).vac;
                                        Slot.slot.get(n).quantity--;
                                    }
                                }
                            }
                            else if(Citizen.citizens.get(ind).status.equals("FULLY VACCINATED")) {
                                System.out.println("Citizen already vaccinated!");
                            }
                            else if(Citizen.citizens.get(ind).status.equals("PARTIALLY VACCINATED")) {
                                for(Slot y:Slot.slot) {
                                    if(y.hospital==n && y.vac.equals(Citizen.citizens.get(ind).vac) && y.quantity!=0 && y.day>=Citizen.citizens.get(ind).due) {
                                        flag_=1;
                                        System.out.println(n1+"-> Day"+y.day+" Available Qty:"+y.quantity+" Vaccine:"+y.vac);
                                    }
                                    n1++;
                                }
                                if(flag_==0) System.out.println("No slots available!");
                                else {
                                    System.out.print("Choose slot:");
                                    n=Reader.nextInt();
                                    System.out.println(c_name+" vaccinated with "+Slot.slot.get(n).vac);
                                    Citizen.citizens.get(ind).dose=Citizen.citizens.get(ind).dose+1;
                                    if(Citizen.citizens.get(ind).dose==Citizen.citizens.get(ind).nod) {
                                        Citizen.citizens.get(ind).status="FULLY VACCINATED";
                                    }
                                    else Citizen.citizens.get(ind).status="PARTIALLY VACCINATED";
                                    Citizen.citizens.get(ind).due=Citizen.citizens.get(ind).due+Slot.slot.get(n).gap;
                                    Citizen.citizens.get(ind).vac=Slot.slot.get(n).vac;
                                    Slot.slot.get(n).quantity--;
                                }
                            }
                        }
                    }
                    else if(c==2) {
                        System.out.print("Enter vaccine name:");
                        String v=Reader.nextLine();
                        for (Slot y:Slot.slot) {
                            if(y.vac.equals(v)) {
                                flag=1;
                                System.out.println(y.id+" "+y.h_name);
                            }
                        }
                        System.out.print("Enter Hospital ID:");
                        n=Reader.nextInt();
                        int n1=0,flag_=0;
                        if(Citizen.citizens.get(ind).status.equals("REGISTERED")) {
                            for(Slot y:Slot.slot) {
                                if(y.hospital==n && y.vac.equals(v) && y.quantity!=0) {
                                    flag_=1;
                                    System.out.println(n1+"-> Day"+y.day+" Available Qty:"+y.quantity+" Vaccine:"+y.vac);
                                }
                                n1++;
                            }
                            if(flag_==0) System.out.println("No slots available!");
                            else {
                                System.out.print("Choose slot:");
                                n=Reader.nextInt();
                                System.out.println(c_name+" vaccinated with "+Slot.slot.get(n).vac);
                                if(Slot.slot.get(n).nod==1) {
                                    Citizen.citizens.get(ind).status="FULLY VACCINATED";
                                    Citizen.citizens.get(ind).dose=1;
                                    Citizen.citizens.get(ind).nod=Slot.slot.get(n).nod;
                                    Citizen.citizens.get(ind).due=1+Slot.slot.get(n).gap;
                                    Citizen.citizens.get(ind).vac=Slot.slot.get(n).vac;
                                }
                                else Citizen.citizens.get(ind).status="PARTIALLY VACCINATED";
                                Slot.slot.get(n).quantity--;
                            }
                        }
                        else if(Citizen.citizens.get(ind).status.equals("FULLY VACCINATED")) {
                            System.out.println("Citizen already vaccinated!");
                        }
                        else if(Citizen.citizens.get(ind).status.equals("PARTIALLY VACCINATED")) {
                            for(Slot y:Slot.slot) {
                                if(y.hospital==n && y.vac.equals(v) && y.vac.equals(Citizen.citizens.get(ind).vac) && y.quantity!=0 && y.day>=Citizen.citizens.get(ind).due) {
                                    flag_=1;
                                    System.out.println(n1+"-> Day"+y.day+" Available Qty:"+y.quantity+" Vaccine:"+y.vac);
                                }
                                n1++;
                            }
                            if(flag_==0) System.out.println("No slots available!");
                            else {
                                System.out.print("Choose slot:");
                                n=Reader.nextInt();
                                System.out.println(c_name+" vaccinated with "+Slot.slot.get(n).vac);
                                Citizen.citizens.get(ind).dose=Citizen.citizens.get(ind).dose+1;
                                if(Citizen.citizens.get(ind).dose==Citizen.citizens.get(ind).nod) {
                                    Citizen.citizens.get(ind).status="FULLY VACCINATED";
                                }
                                else {
                                    Citizen.citizens.get(ind).status="PARTIALLY VACCINATED";
                                    Citizen.citizens.get(ind).due=Citizen.citizens.get(ind).due+Slot.slot.get(n).gap;
                                    Citizen.citizens.get(ind).vac=Slot.slot.get(n).vac;
                                }
                                Slot.slot.get(n).quantity--;
                            }
                        }
                    }
                }
            }
            else if(o==6) {
                System.out.print("Enter Hospital ID:");
                n=Reader.nextInt();
                int flag=0;
                for(Slot y:Slot.slot) {
                    if(y.id==n && y.quantity!=0) {
                        flag=1;
                        System.out.println("Day: "+y.day+" Vaccine: "+y.vac+" Available Quantity: "+y.quantity);
                    }
                }
                if(flag==0) System.out.println("No slots available on this hospital ID!");
            }
            else if(o==7) {
                System.out.print("Enter patient ID:");
                i=Reader.nextLine();
                int flag=0;
                for(Citizen y:Citizen.citizens) {
                    if(y.id.equals(i)) {
                        flag=1;
                        if(y.status.equals("REGISTERED")) System.out.println("Citizen REGISTERED");
                        else if(y.status.equals("FULLY VACCINATED")) {
                            System.out.println(y.status);
                            System.out.println("Vaccine Given: "+y.vac);
                            System.out.println("Number of doses given: "+y.dose);
                        }
                        else if(y.status.equals("PARTIALLY VACCINATED")) {
                            System.out.println(y.status);
                            System.out.println("Vaccine Given: "+y.vac);
                            System.out.println("Number of doses given: "+y.dose);
                            System.out.println("Next due date: "+y.due);
                        }
                    }
                }
                if(flag==0) System.out.println("No citizen is registered on this ID!");
            }
            else if(o>8) System.out.println("Invalid option!");
        } while(o!=8);
    }
}

class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }
    static String nextLine() throws IOException {
    return reader.readLine();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
	
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}
